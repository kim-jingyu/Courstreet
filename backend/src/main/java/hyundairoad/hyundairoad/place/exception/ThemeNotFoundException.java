package hyundairoad.hyundairoad.place.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class ThemeNotFoundException extends HyundaiRoadException {
    public ThemeNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "테마를 찾지 못했습니다."));
    }
}
