package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.UserRes;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.UserService;
import com._02server.com02backendproject.dto.UserReq;
import com._02server.com02backendproject.global.BaseException;
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

    //회원 정보 조회
    @GetMapping(value = "/info")
    public BaseResponse<UserRes.UserInfoCheckRes> info(
            @RequestBody UserReq.UserInfoCheckReq userInfoCheckReq

    ) throws BaseException, IOException {
        UserRes.UserInfoCheckRes userInfoCheckRes = userService.info(userInfoCheckReq);
        return new BaseResponse<>(userInfoCheckRes);
    }
}
