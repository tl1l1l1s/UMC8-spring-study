package umc._th.spring.apiPayload.exception.handler;

import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
