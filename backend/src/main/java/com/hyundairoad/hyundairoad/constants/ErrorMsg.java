package com.hyundairoad.hyundairoad.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMsg {
    INVALID_LOGIN("잘못된 로그인 정보입니다."),
    INVALID_SIGNUP("잘못된 회원가입 정보입니다."),
    INVALID_PATH("잘못된 경로입니다."),
    INVALID_EMAIL("이메일 인증에 실패했습니다."),
    INVALID_SIMPLE_MAIL_SEND("간편 메시지 전송에 실패했습니다."),
    INVALID_MIME_MAIL_SEND("MIME 메시지 전송에 실패했습니다."),
    FAIL_TO_PAGE_LOAD("페이지를 불러오는데 실패했습니다."),
    INTERNAL_SERVER_ERROR("내부 서버 오류입니다.")
    ;

    private final String message;
}
