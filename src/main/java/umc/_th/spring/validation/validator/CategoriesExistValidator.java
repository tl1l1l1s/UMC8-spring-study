package umc._th.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.service.FoodTypeService.FoodTypeQueryService;
import umc._th.spring.validation.annotation.ExistCategories;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodTypeQueryService foodTypeQueryService;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = values.stream()
                .allMatch(foodTypeQueryService::existsById);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
