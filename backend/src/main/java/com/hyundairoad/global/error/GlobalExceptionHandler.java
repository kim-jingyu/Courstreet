package com.hyundairoad.global.error;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (HyundaiRoadException.class)
    public ResponseEntity<ErrorResponse> handlingApplicationException(HyundaiRoadException exception) {
        log.error("{}", exception.toString());
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.status())
                .body(ErrorResponse.builder()
                        .message(errorCode.message())
                        .status(errorCode.status().value())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handlingServerErrorException(Exception exception) {
        log.error("{}", exception.toString());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .message("서버 내부 오류 발생!")
                        .status(INTERNAL_SERVER_ERROR.value())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (isAlreadyCommitted(request)) {
            return null;
        }
        log.error("{}", exception.toString());
        return ResponseEntity.status(status)
                .body(ErrorResponse.builder()
                        .message(exception.getBindingResult().getAllErrors().stream()
                                .map(ObjectError::getDefaultMessage)
                                .collect(joining("; ")))
                        .status(status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (isAlreadyCommitted(request)) {
            return null;
        }
        log.error("{}", exception.toString());
        return ResponseEntity.status(statusCode)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(statusCode.value()));
    }

    private boolean isAlreadyCommitted(WebRequest request) {
        HttpServletResponse response = ((ServletWebRequest) request).getResponse();
        return response != null && response.isCommitted();
    }
}
