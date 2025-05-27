package umc._th.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.StoreHandler;
import umc._th.spring.converter.MissionConverter;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.Store;
import umc._th.spring.repository.MissionRepository.MissionRepository;
import umc._th.spring.repository.ReviewRepository.ReviewRepository;
import umc._th.spring.repository.StoreRepository.StoreRepository;
import umc._th.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoreByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store ->
                System.out.println("Store : " + store));

        return filteredStores;
    }

    @Override
    public MissionResponseDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        
        return MissionConverter.toMissionPreViewListDTO(missionRepository.findAllByStore(store, PageRequest.of(page-1, 10)), store);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        return reviewRepository.findAllByStore(store, PageRequest.of(page-1, 10));
    }
}
