package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.DeviceDetailRes;
import com._02server.com02backendproject.dto.DeviceReq;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com._02server.com02backendproject.dto.DeviceRes;
import com._02server.com02backendproject.entities.Category;
import com._02server.com02backendproject.global.BaseResponse;
import com._02server.com02backendproject.service.SearchService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import static com._02server.com02backendproject.global.BaseResponseStatus.SUCCESS;
import static com._02server.com02backendproject.service.CrawlerService.crawlInformation;

@RequiredArgsConstructor
@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/init")
    public BaseResponse<Void> init(@RequestParam String category ) throws IOException {
        Category cate = null;
        for(Category c : Category.values()) {
            if(c.getEng().equals(category)) {
                cate = c;
            }
        }
        if(cate==null) {
            cate = Category.보조배터리;
            System.out.println("파라미터로 입력한 것은 없는 카테고리이므로, 기본 값을 업데이트합니다.");
        }
        crawlInformation(cate);
        return new BaseResponse<>(SUCCESS);
    }
    @GetMapping("/search")
    public DeviceRes.SearchDeviceRes[] handleSearchRequest(@RequestParam String searchKeyword) {
        List<DeviceRes.SearchDeviceRes> response = SearchService.getDataFromDatabase(searchKeyword);

        DeviceRes.SearchDeviceRes[] deviceRes = new DeviceRes.SearchDeviceRes[response.size()];
        response.toArray(deviceRes);

        // 원하는 동작 수행 후 결과 반환
        return deviceRes;
    }


    // 한 기기의 정보 읽어오기
    @GetMapping("/list/{deviceName}")
    public BaseResponse<DeviceDetailRes> deviceDetailRead(@PathVariable("deviceName") String deviceName)
    throws BaseException, IOException{
        DeviceDetailRes deviceDetailRes = deviceService.deviceDetailRead(deviceName);
        return new BaseResponse<>(deviceDetailRes);
    }

    // 한 기기 삭제
    @PutMapping("/list/delete")
    public BaseResponse<Void> deviceDetailDelete(@RequestBody DeviceReq.DeviceIdReq deviceIdReq)
        throws BaseException, IOException{
        deviceService.deviceDelete(deviceIdReq);
        return new BaseResponse<>(SUCCESS);
    }
}

