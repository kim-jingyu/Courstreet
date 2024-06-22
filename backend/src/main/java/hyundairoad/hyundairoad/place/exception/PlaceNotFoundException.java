package hyundairoad.hyundairoad.place.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class PlaceNotFoundException extends HyundaiRoadException {
    public PlaceNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "해당 장소를 찾지못했습니다."));
    }
}
