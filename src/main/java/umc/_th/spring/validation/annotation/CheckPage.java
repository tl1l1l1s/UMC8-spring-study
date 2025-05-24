package umc._th.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc._th.spring.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "페이지의 범위가 잘못되었습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
