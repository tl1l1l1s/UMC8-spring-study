package umc._th.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc._th.spring.apiPayload.exception.handler.MemberHandler;
import umc._th.spring.config.security.jwt.JwtTokenProvider;
import umc._th.spring.converter.MemberConverter;
import umc._th.spring.converter.MemberPreferConverter;
import umc._th.spring.domain.FoodType;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.mapping.MemberPrefer;
import umc._th.spring.repository.FoodTypeRepository.FoodTypeRepository;
import umc._th.spring.repository.MemberRepository.MemberRepository;
import umc._th.spring.web.dto.MemberRequestDTO;
import umc._th.spring.web.dto.MemberResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        List<FoodType> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodTypeRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        System.out.println("=== 회원가입 요청 데이터 ===");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Name: " + request.getName());
        System.out.println("PreferCategory: " + request.getPreferCategory());
        System.out.println("============================");

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name()));

        String accessToken = jwtTokenProvider.generateToken(authentication);
        return MemberConverter.toLoginResultDTO(
                member.getId(),
                accessToken
        );
    }
}