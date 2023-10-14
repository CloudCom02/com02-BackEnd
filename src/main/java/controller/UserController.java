package controller;

import dto.UserReq;
import global.BaseException;
import global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.io.IOException;

import static global.BaseResponseStatus.SUCCESS;

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
}
