package umc._th.spring.apiPayload.exception.handler;

import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
