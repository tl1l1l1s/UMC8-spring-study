package umc._th.spring.web.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDTO {
        @Min(0)
        Integer point;
        @Min(0)
        Integer reward;
    }
}
