package hyundairoad.hyundairoad.place.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends HyundaiRoadException {
    public CategoryNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "카테고리를 찾지 못했습니다."));
    }
}
