package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.UserRes;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.UserService;
import com._02server.com02backendproject.dto.UserReq;
import com._02server.com02backendproject.global.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com._02server.com02backendproject.global.BaseResponseStatus.SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping(value = "/join")
    public BaseResponse<Void> join(
            @RequestBody UserReq.UserJoinReq userJoinReq
    ) throws BaseException, IOException {
        userService.join(userJoinReq);
        return new BaseResponse<>(SUCCESS);
    }

    //로그인
    @PostMapping(value = "/login")
    public BaseResponse<UserRes.UserLoginRes> login(
            @RequestBody UserReq.UserLoginReq userLoginReq
    ) throws BaseException, IOException {
        UserRes.UserLoginRes userLoginRes = userService.login(userLoginReq);
        return new BaseResponse<>(userLoginRes);
    }

    // 이메일 전송
    @PostMapping("/emails/verification-requests")
    public BaseResponse<Void> sendMessage(UserReq.UserSendEmailReq userSendEmailReq, HttpServletRequest request) throws BaseException {
        String code = userService.sendCodeToEmail(userSendEmailReq.getEmail(), userSendEmailReq.getIsForJoin());

        HttpSession session = request.getSession();
        session.setAttribute("email", userSendEmailReq.getEmail());
        session.setAttribute("code", code);

        return new BaseResponse<>(SUCCESS);
    }

    // 이메일 인증
    @GetMapping("/emails/verifications")
    public BaseResponse<UserRes.UserEmailCodeCheckRes> verificationEmail(UserReq.UserEmailCodeCheckReq userEmailCodeCheckReq, HttpServletRequest request) throws BaseException {
        Boolean isCorrected = userService.verifiedCode(userEmailCodeCheckReq.getEmail(), userEmailCodeCheckReq.getCode(), request);
        UserRes.UserEmailCodeCheckRes res = new UserRes.UserEmailCodeCheckRes(isCorrected);
        return new BaseResponse<>(res);
    }

    // 이메일 중복 확인
    @GetMapping("/check-email")
    public BaseResponse<UserRes.UserEmailDupCheckRes> checkDupEmail(UserReq.UserEmailDupCheckReq userEmailDupCheckReq) throws BaseException {
        Boolean emailExists = userService.checkDupEmail(userEmailDupCheckReq.getEmail());
        UserRes.UserEmailDupCheckRes res = new UserRes.UserEmailDupCheckRes(emailExists);
        return new BaseResponse<>(res);
    }

}
