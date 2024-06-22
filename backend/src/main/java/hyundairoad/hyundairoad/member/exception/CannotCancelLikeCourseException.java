package hyundairoad.hyundairoad.member.exception;

import hyundairoad.hyundairoad.global.error.ErrorCode;
import hyundairoad.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class CannotCancelLikeCourseException extends HyundaiRoadException {
    public CannotCancelLikeCourseException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "코스 좋아요 취소에 실패했습니다!"));
    }
}
