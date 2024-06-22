package hyundairoad.hyundairoad.global.error;

import lombok.Getter;

@Getter
public class HyundaiRoadException extends RuntimeException {
    private final ErrorCode errorCode;

    public HyundaiRoadException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }
}
