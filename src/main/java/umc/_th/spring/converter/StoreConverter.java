package umc._th.spring.converter;

import umc._th.spring.domain.Region;
import umc._th.spring.domain.Store;
import umc._th.spring.web.dto.StoreRequestDTO;
import umc._th.spring.web.dto.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Store store) {
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static Store toStoreByRegionId(Region region, StoreRequestDTO.AddStoreDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }
}
