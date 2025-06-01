package umc._th.spring.apiPayload.exception.handler;

import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.exception.GeneralException;

public class AuthHandler extends GeneralException {
	public AuthHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
