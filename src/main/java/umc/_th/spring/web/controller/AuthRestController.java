package umc._th.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.service.AuthService.AuthService;
import umc._th.spring.web.dto.AuthResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthRestController {
	
	private final AuthService authService;
	
	@PostMapping("/refresh")
	@Operation(summary = "액세스 토큰 갱신 API",
			description = "리프레시 토큰으로 액세스 토큰을 갱신하는 API입니다.",
			security = { @SecurityRequirement(name = "REFRESH TOKEN") }
	)
	public ApiResponse<AuthResponseDTO.RefreshResultDTO> updateToken(@CookieValue(name = "refreshToken") String refreshToken) {
		return ApiResponse.onSuccess(authService.updateToken(refreshToken));
	}
}
