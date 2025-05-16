package umc._th.spring.web.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDTO {
        @NonNull
        Long regionId;
        @NonNull
        String name;
        @Size(min = 10, max = 24)
        String address;
    }
}
