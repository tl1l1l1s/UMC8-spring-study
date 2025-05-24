package umc._th.spring.service.StoreService;

import umc._th.spring.domain.Store;
import umc._th.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store addStore(StoreRequestDTO.AddStoreDTO request);
}
