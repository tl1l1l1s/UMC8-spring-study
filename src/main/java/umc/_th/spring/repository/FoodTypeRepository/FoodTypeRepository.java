package umc._th.spring.repository.FoodTypeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.FoodType;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
}