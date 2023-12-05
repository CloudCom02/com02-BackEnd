package com._02server.com02backendproject.controller;

import com._02server.com02backendproject.dto.DeviceRes;
import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com._02server.com02backendproject.global.BaseResponseStatus.DEVICE_NOT_EXISTS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/device")
public class DeviceController {
    private final DeviceRepository deviceRepository;
    @GetMapping("/search")
    public List<DeviceRes.SearchDeviceRes> handleSearchRequest(@RequestParam String searchKeyword) throws BaseException {

        List<Device> deviceList = deviceRepository.findByDeviceNameIgnoreCaseContaining(searchKeyword);
        List<DeviceRes.SearchDeviceRes> response = new ArrayList<>();

        if (deviceList.isEmpty()) {
            throw new BaseException(DEVICE_NOT_EXISTS);
        } else {
            for(Device device : deviceList) {
                DeviceRes.SearchDeviceRes searchDeviceRes = new DeviceRes.SearchDeviceRes(device.getDeviceName(),device.getCategory(),device.getImageURL());
                response.add(searchDeviceRes);
            }
        }
        return response;

    }
}
