package umc._th.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.RegionHandler;
import umc._th.spring.converter.StoreConverter;
import umc._th.spring.domain.Region;
import umc._th.spring.domain.Store;
import umc._th.spring.repository.RegionRepository.RegionRepository;
import umc._th.spring.repository.StoreRepository.StoreRepository;
import umc._th.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Store addStore(StoreRequestDTO.AddStoreDTO request) {

        Region region = regionRepository.findById(request.getRegionId())
                    .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        return storeRepository.save(
                StoreConverter.toStoreByRegionId(region, request)
        );
    }
}
