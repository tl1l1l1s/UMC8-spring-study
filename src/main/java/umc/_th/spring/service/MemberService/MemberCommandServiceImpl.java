package umc._th.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc._th.spring.converter.MemberConverter;
import umc._th.spring.converter.MemberPreferConverter;
import umc._th.spring.domain.FoodType;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.mapping.MemberPrefer;
import umc._th.spring.repository.FoodTypeRepository.FoodTypeRepository;
import umc._th.spring.repository.MemberRepository.MemberRepository;
import umc._th.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodTypeRepository foodTypeRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodType> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodTypeRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}