package umc._th.spring.service.FoodTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.repository.FoodTypeRepository.FoodTypeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodTypeQueryServiceImpl implements FoodTypeQueryService {

    private final FoodTypeRepository foodTypeRepository;

    @Override
    public boolean existsById(Long id) {
        return foodTypeRepository.existsById(id);
    }
}
