package umc._th.spring.service.AuthService;

import umc._th.spring.web.dto.AuthResponseDTO;

public interface AuthService {
	AuthResponseDTO.RefreshResultDTO updateToken(String refreshToken);
}
