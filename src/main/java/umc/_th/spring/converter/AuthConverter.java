package umc._th.spring.converter;

import umc._th.spring.web.dto.AuthResponseDTO;

public class AuthConverter {
	
	
	public static AuthResponseDTO.RefreshResultDTO toRefreshResultDTO( String accessToken, String refreshToken){
		return AuthResponseDTO.RefreshResultDTO.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}
}
