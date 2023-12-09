package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.CapacityOfUserReq;
import com._02server.com02backendproject.dto.CapacityOfUserRes;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.CapacityOfUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com._02server.com02backendproject.global.BaseResponseStatus.SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/capacity")
public class CapacityOfUserController {

    private final CapacityOfUserService capacityOfUserService;

    // 사용자의 기기 추가
    @PostMapping(value = "/add")
    public BaseResponse<Void> capacityOfUserAdd(
            @Validated @RequestBody CapacityOfUserReq.CapacityOfUserAddReq capacityOfUserReq)
            throws BaseException, IOException{
        capacityOfUserService.capacityOfUserAdd(capacityOfUserReq);
        return new BaseResponse<>(SUCCESS);
    }

    // 사용자의 기기에서 삭제
    @PutMapping(value = "/delete")
    public BaseResponse<Void> capacityOfUserDelete(
            @RequestBody CapacityOfUserReq.CapacityOfUserDeleteReq capacityOfUserDeleteReq)
        throws BaseException, IOException{
        capacityOfUserService.capacityOfUserDelete(capacityOfUserDeleteReq);
        return new BaseResponse<>(SUCCESS);
    }

    // 사용자의 기기 수정
    @PutMapping(value = "/update")
    public BaseResponse<Void> capacityOfUserEdit(
            @RequestBody CapacityOfUserReq.CapacityOfUserUpdateReq capacityOfUserEditReq)
        throws BaseException, IOException {
        capacityOfUserService.capacityOfUserEdit(capacityOfUserEditReq);
        return new BaseResponse<>(SUCCESS);
    }

    // 사용자 기기의 현재 배터리 정보만 수정
    @PutMapping(value = "/batteryLevel")
    public BaseResponse<Void> batteryLevelUpdate(
           @RequestBody CapacityOfUserReq.BatteryLevelReq batteryLevelReq)
        throws BaseException, IOException{
        capacityOfUserService.BatteryLevelUpdate(batteryLevelReq);
        return new BaseResponse<>(SUCCESS);
    }

    // 사용자의 기기 리스트 읽어오기
    @GetMapping(value = "/list/{userId}")
    public BaseResponse<List<CapacityOfUserRes>> capacityOfUserRead(
          @PathVariable("userId") Long userId)
        throws BaseException, IOException{
        List<CapacityOfUserRes> capacityOfUserRes = capacityOfUserService.capacityOfUserRead(userId);
        return new BaseResponse<>(capacityOfUserRes);
    }
}
