package umc._th.spring.apiPayload.exception.handler;

import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
