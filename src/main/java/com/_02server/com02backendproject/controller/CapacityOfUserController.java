package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.DeviceReq;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.CapacityOfUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com._02server.com02backendproject.global.BaseResponseStatus.SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/capacity")
public class CapacityOfUserController {

    private final CapacityOfUserService capacityOfUserService;

    // 사용자의 기기 추가
    @PostMapping(value = "/add")
    public BaseResponse<Void> capacityOfUserAdd(
            @Validated @RequestBody DeviceReq.CapacityOfUserAddReq capacityOfUserAddReq)
            throws BaseException, IOException{
        capacityOfUserService.capacityOfUserAdd(capacityOfUserAddReq);
        return new BaseResponse<>(SUCCESS);
    }

    // 사용자의 기기에서 삭제
    @PutMapping(value = "/delete")
    public BaseResponse<Void> capacityOfUserDelete(
            @RequestBody DeviceReq.CapacityOfUserDeleteReq capacityOfUserDeleteReq)
        throws BaseException, IOException{
        capacityOfUserService.capacityOfUserDelete(capacityOfUserDeleteReq);
        return new BaseResponse<>(SUCCESS);
    }
}
