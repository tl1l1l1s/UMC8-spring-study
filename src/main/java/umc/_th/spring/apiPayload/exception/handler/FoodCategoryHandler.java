package umc._th.spring.apiPayload.exception.handler;

import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }

}
