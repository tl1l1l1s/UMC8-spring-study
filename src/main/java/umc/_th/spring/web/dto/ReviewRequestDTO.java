package umc._th.spring.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class AddReviewDTO {
        @DecimalMin("0.0") @DecimalMax("5.0")
        Float rate;
        @Size(min = 0, max = 40)
        String content;
    }
}
