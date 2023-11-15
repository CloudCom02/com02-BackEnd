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
    public BaseResponse<Void> sendMessage(@RequestParam("email") String email, @RequestParam("isForJoin") Boolean isForJoin, HttpServletRequest request) throws BaseException {
        String code = userService.sendCodeToEmail(email, isForJoin);

        HttpSession session = request.getSession();
        session.setAttribute("code", code);

        return new BaseResponse<>(SUCCESS);
    }

    // 이메일 인증
    @GetMapping("/emails/verifications")
    public BaseResponse<String> verificationEmail(@RequestParam("email") String email,
                                            @RequestParam("code") String authCode, HttpServletRequest request) throws BaseException {
        String result = userService.verifiedCode(email, authCode, request);

        return new BaseResponse<>(result);
    }
}
