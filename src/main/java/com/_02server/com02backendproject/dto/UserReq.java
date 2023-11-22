package com._02server.com02backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserJoinReq { // 회원가입 요청
        String email;
        String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserLoginReq { // 로그인 요청
        String email;
        String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserSendEmailReq { // 이메일 전송 요청
        String email;
        Boolean isForJoin;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserEmailCodeCheckReq { // 인증 코드 검증 요청
        String email;
        String code;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserEmailDupCheckReq { // 이메일 중복 확인
        String email;
    }
}