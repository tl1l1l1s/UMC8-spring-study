package umc._th.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.service.MemberMissionService.MemberMissionCommandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMissions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<String>startMission(@RequestParam Long missionId, @RequestParam Long memberId) {
        memberMissionCommandService.startMission(missionId, memberId);
        return ApiResponse.onSuccess("도전 중인 미션에 추가되었습니다.");
    }
}
