package umc._th.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.converter.MemberMissionConverter;
import umc._th.spring.service.MemberMissionService.MemberMissionCommandService;
import umc._th.spring.service.MemberMissionService.MemberMissionQueryService;
import umc._th.spring.validation.annotation.CheckPage;
import umc._th.spring.web.dto.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/memberMissions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/challenge")
    @Operation(summary = "특정 유저가 진행 중인 미션 목록 조회 API", description = "특정 유저가 진행 중인 미션 목록 조회 API입니다. 페이징을 포함하니 query string으로 page 번호를 함께 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> getChallengingMission(@RequestParam Long memberId, @CheckPage @RequestParam(name = "page") Integer page) {

        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionPreViewListDTO(memberMissionQueryService.findAllMemberMissionsByMemberId(memberId, page)));
    }

    @PostMapping("/challenge")
    public ApiResponse<String> startMission(@RequestParam Long missionId, @RequestParam Long memberId) {
        memberMissionCommandService.startMission(missionId, memberId);
        return ApiResponse.onSuccess("도전 중인 미션에 추가되었습니다.");
    }

    @PostMapping("/complete")
    @Operation(summary = "미션 완료 API", description = "유저가 진행 중인 미션을 완료 상태로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<String> completeMission(@RequestParam Long missionId, @RequestParam Long memberId) {
        memberMissionCommandService.completeMission(missionId, memberId);
        return ApiResponse.onSuccess("도전 완료한 미션으로 처리되었습니다.");
    }
}
