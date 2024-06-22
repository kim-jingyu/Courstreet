package hyundairoad.hyundairoad.global.error;

import org.springframework.http.HttpStatus;

public record ErrorCode (HttpStatus status, String message) {
}
