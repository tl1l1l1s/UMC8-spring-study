package umc._th.spring.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        if (flag == 500)
            throw new TempHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
    }
}