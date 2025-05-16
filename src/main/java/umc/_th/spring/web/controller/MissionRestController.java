package umc._th.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.converter.MissionConverter;
import umc._th.spring.service.MissionService.MissionCommandService;
import umc._th.spring.web.dto.MissionRequestDTO;
import umc._th.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(@RequestParam Long storeId, @RequestBody @Valid MissionRequestDTO.AddMissionDTO request) {
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(missionCommandService.createMission(storeId, request)));
    }

}
