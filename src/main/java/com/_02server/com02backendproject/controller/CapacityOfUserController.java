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
    @PostMapping(value = "/{userId}/add")
    public BaseResponse<Void> capacityOfUserAdd(
            @PathVariable("userId") Long userId, @Validated @RequestBody DeviceReq.CapacityOfUserReq capacityOfUserReq)
            throws BaseException, IOException{
        capacityOfUserService.capacityOfUserAdd(capacityOfUserReq);
        return new BaseResponse<>(SUCCESS);
    }

    // 사용자의 기기에서 삭제
    @PutMapping(value = "/{userId}/delete")
    public BaseResponse<Void> capacityOfUserDelete(
            @PathVariable("userId") Long userId, @RequestParam Long capacityOfUserId)
        throws BaseException, IOException{
        capacityOfUserService.capacityOfUserDelete(capacityOfUserId);
        return new BaseResponse<>(SUCCESS);
    }

    @PutMapping(value = "/update")
    public BaseResponse<Void> capacityOfUserEdit(
            @RequestBody DeviceReq.CapacityOfUserUpdateReq capacityOfUserEditReq)
        throws BaseException, IOException {
        capacityOfUserService.capacityOfUserEdit(capacityOfUserEditReq);
        return new BaseResponse<>(SUCCESS);
    }
}
