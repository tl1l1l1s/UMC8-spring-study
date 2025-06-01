package umc._th.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthResponseDTO {
	
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RefreshResultDTO {
		String accessToken;
		String refreshToken;
	}
}
