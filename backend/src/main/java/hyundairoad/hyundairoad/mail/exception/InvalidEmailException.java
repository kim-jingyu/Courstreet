package hyundairoad.hyundairoad.mail.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends HyundaiRoadException {
    public InvalidEmailException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이메일 인증에 실패했습니다."));
    }
}
