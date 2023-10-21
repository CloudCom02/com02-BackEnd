package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.DeviceReq;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.CapacityOfUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com._02server.com02backendproject.global.BaseResponseStatus.SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/capacity")
public class CapacityOfUserController {

    private final CapacityOfUserService capacityOfUserService;

    // 사용자의 기기 추가
    @PostMapping(value = "/add")
    public BaseResponse<Void> deviceOfUserAdd(
            @Validated @RequestBody DeviceReq.DeviceOfUserAddReq deviceOfUserReq)
            throws BaseException, IOException{
        capacityOfUserService.deviceOfUserAdd(deviceOfUserReq);
        return new BaseResponse<>(SUCCESS);
    }
}
