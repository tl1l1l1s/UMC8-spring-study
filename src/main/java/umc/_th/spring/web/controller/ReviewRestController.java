package umc._th.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc._th.spring.apiPayload.ApiResponse;
import umc._th.spring.converter.ReviewConverter;
import umc._th.spring.service.ReviewService.ReviewCommandService;
import umc._th.spring.web.dto.ReviewRequestDTO;
import umc._th.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> addStore(@RequestParam Long memberId, @RequestParam Long storeId, @RequestBody @Valid ReviewRequestDTO.AddReviewDTO request) {
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(
                reviewCommandService.createReview(memberId, storeId, request)));
    }
}
