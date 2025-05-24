package umc._th.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc._th.spring.apiPayload.code.BaseErrorCode;
import umc._th.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),

    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 에러"),

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOODTYPE4001" , "존재하지 않는 음식 종류입니다."),

    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4001" , "존재하지 않는 지역입니다."),

    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001" , "존재하지 않는 상점입니다."),

    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "존재하지 않는 미션입니다."),
    ALREADY_CHALLENGING_MISSION(HttpStatus.BAD_REQUEST, "MISSION4002" , "이미 도전 중인 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
