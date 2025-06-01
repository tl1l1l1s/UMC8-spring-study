package umc._th.spring.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.AuthHandler;
import umc._th.spring.apiPayload.exception.handler.MemberHandler;
import umc._th.spring.config.properties.JwtProperties;
import umc._th.spring.config.security.jwt.JwtTokenProvider;
import umc._th.spring.converter.AuthConverter;
import umc._th.spring.domain.Member;
import umc._th.spring.repository.MemberRepository.MemberRepository;
import umc._th.spring.web.dto.AuthResponseDTO;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public AuthResponseDTO.RefreshResultDTO updateToken(String refreshToken) {
		Authentication auth = jwtTokenProvider.getAuthentication(refreshToken);
		
		if(!jwtTokenProvider.validateToken(refreshToken) || !auth.isAuthenticated()) {
			throw new AuthHandler(ErrorStatus.INVALID_TOKEN);
		}
		
		String email = auth.getName();
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
		if(member.getInactiveDate() != null) throw new MemberHandler(ErrorStatus.INACTIVE_MEMBER);
		
		return AuthConverter.toRefreshResultDTO(jwtTokenProvider.generateToken(auth, JwtProperties.TokenType.ACCESS), refreshToken);
	}
}
