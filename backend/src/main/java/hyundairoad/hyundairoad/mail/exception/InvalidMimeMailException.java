package hyundairoad.hyundairoad.mail.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class InvalidMimeMailException extends HyundaiRoadException {
    public InvalidMimeMailException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "MIME 이메일 인증에 실패했습니다."));
    }
}
