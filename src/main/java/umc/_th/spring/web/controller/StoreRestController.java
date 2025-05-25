package umc._th.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.converter.ReviewConverter;
import umc._th.spring.converter.StoreConverter;
import umc._th.spring.service.StoreService.StoreCommandService;
import umc._th.spring.service.StoreService.StoreQueryService;
import umc._th.spring.validation.annotation.CheckPage;
import umc._th.spring.validation.annotation.ExistStore;
import umc._th.spring.web.dto.MissionResponseDTO;
import umc._th.spring.web.dto.ReviewResponseDTO;
import umc._th.spring.web.dto.StoreRequestDTO;
import umc._th.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.AddStoreDTO request) {
        return ApiResponse.onSuccess(StoreConverter.toAddStoreResultDTO(storeCommandService.addStore(request)));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "툭정 가게의 미션 목록 조회 API", description = "특졍 가게의 미션 목록을 조회하는 API입니다. 페이징을 포함하니 query string으로 page 번호를 함께 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디. path variable입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId, @CheckPage @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(storeQueryService.getMissionList(storeId,page));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "툭정 가게의 리뷰 목록 조회 API", description = "특졍 가게의 리뷰 목록을 조회하는 API입니다. 페이징을 포함하니 query string으로 page 번호를 함께 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디. path variable입니다.")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @CheckPage @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(storeQueryService.getReviewList(storeId,page)));
    }
}
