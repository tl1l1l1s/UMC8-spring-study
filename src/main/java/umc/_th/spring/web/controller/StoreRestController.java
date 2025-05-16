package umc._th.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.converter.StoreConverter;
import umc._th.spring.service.StoreService.StoreCommandService;
import umc._th.spring.web.dto.StoreRequestDTO;
import umc._th.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.AddStoreDTO request) {
        return ApiResponse.onSuccess(StoreConverter.toAddStoreResultDTO(storeCommandService.addStore(request)));
    }
}
