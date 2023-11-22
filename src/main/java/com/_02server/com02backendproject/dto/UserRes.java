package com._02server.com02backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRes {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserLoginRes { // 로그인 response
        Long userIdx;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserEmailCodeCheckRes { // 이메일 인증 코드 검증 response
        Boolean isCorrected;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserEmailDupCheckRes { // 이메일 중복 확인 response
        Boolean emailExists;
    }
}
