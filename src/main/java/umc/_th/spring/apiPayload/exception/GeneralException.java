package umc._th.spring.apiPayload.exception;

import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.code.ErrorReasonDTO;

public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public GeneralException() {
        super();
    }

    public GeneralException(BaseErrorCode errorCode) {
        super(errorCode.getReason().getMessage());
        this.code = errorCode;
    }

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }

}
