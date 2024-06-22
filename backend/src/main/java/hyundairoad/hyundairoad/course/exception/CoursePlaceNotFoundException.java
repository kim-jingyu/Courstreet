package hyundairoad.hyundairoad.course.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class CoursePlaceNotFoundException extends HyundaiRoadException {
    public CoursePlaceNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "코스별 장소 찾기에 실패했습니다!"));
    }
}
