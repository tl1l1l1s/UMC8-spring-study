package umc._th.spring.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc._th.spring.domain.enums.Role;
import umc._th.spring.validation.annotation.ExistCategories;

import java.util.ArrayList;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDTO{
        @Email
        String email;
        @NotBlank
        String password;
        @NotNull
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @NotNull
        Role role;
        @ExistCategories
        List<Long> preferCategory = new ArrayList<>();
    }
}
