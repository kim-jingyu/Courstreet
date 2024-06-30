package com.hyundairoad.global.constants;

/**
 * MailConstants
 *
 * 작성자: 김진규
 */
public class MailConstants {
    public static final String PASSWORD_FIND_TITLE = "비밀번호 찾기 인증 이메일입니다.";
    public static final String PASSWORD_FIND_CONTENT_PREV = "<!DOCTYPE html>" +
                                                            "<html>" +
                                                            "<head>" +
                                                            "<style>" +
                                                            "  .container {" +
                                                            "    width: 100%;" +
                                                            "    max-width: 600px;" +
                                                            "    margin: auto;" +
                                                            "    padding: 20px;" +
                                                            "    font-family: Arial, sans-serif;" +
                                                            "    background-color: #f9f9f9;" +
                                                            "    border: 1px solid #ddd;" +
                                                            "    border-radius: 5px;" +
                                                            "  }" +
                                                            "  .header {" +
                                                            "    text-align: center;" +
                                                            "    padding: 10px;" +
                                                            "    background-color: #007bff;" +
                                                            "    color: white;" +
                                                            "    border-radius: 5px 5px 0 0;" +
                                                            "  }" +
                                                            "  .content {" +
                                                            "    margin-top: 20px;" +
                                                            "  }" +
                                                            "  .content p {" +
                                                            "    margin: 10px 0;" +
                                                            "  }" +
                                                            "  .code {" +
                                                            "    font-size: 1.5em;" +
                                                            "    font-weight: bold;" +
                                                            "    text-align: center;" +
                                                            "    margin: 20px 0;" +
                                                            "    color: #007bff;" +
                                                            "  }" +
                                                            "</style>" +
                                                            "</head>" +
                                                            "<body>" +
                                                            "  <div class='container'>" +
                                                            "    <div class='header'>" +
                                                            "      <h1>Hyundai-Road 비밀번호 찾기</h1>" +
                                                            "    </div>" +
                                                            "    <div class='content'>" +
                                                            "      <p>Hyundai-Road에 방문해주셔서 감사합니다.</p>" +
                                                            "      <p>인증 번호는 아래와 같습니다:</p>" +
                                                            "      <div class='code'>";

    public static final String PASSWORD_FIND_CONTENT_POST = "</div>" +
                                                            "      <p>이 번호를 사용하여 인증 절차를 완료해 주세요.</p>" +
                                                            "    </div>" +
                                                            "  </div>" +
                                                            "</body>" +
                                                            "</html>";
}
