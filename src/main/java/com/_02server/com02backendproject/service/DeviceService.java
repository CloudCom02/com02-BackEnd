package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.DeviceDetailRes;
import com._02server.com02backendproject.dto.DeviceReq;
import com._02server.com02backendproject.dto.DeviceRes;
import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.repository.DeviceRepository;
import com._02server.com02backendproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com._02server.com02backendproject.global.BaseResponseStatus.CANNOT_FIND_INFORMATION;
import static com._02server.com02backendproject.global.BaseResponseStatus.EMPTY_INFORMATION;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Transactional
    public void deviceAdd(DeviceReq.DeviceAllReq deviceAddReq)
            throws BaseException, IOException {

        if (deviceAddReq == null) {
            throw new BaseException(EMPTY_INFORMATION);
        } else {
            Device device = Device.builder()
                    .parentId(deviceAddReq.getParentId())
                    .entireCapacity(deviceAddReq.getEntireCapacity())
                    .usingTime(deviceAddReq.getUsingTime())
                    .chargingTime(deviceAddReq.getChargingTime())
                    .wattageW(deviceAddReq.getWattageW())
                    .category(deviceAddReq.getCategory())
                    .imageURL(deviceAddReq.getImageURL())
                    .isRegistered(deviceAddReq.getIsRegistered())
                    .user(deviceAddReq.getUser())
                    .contents(deviceAddReq.getContents())
                    .mAh(deviceAddReq.getMAh())
                    .maximumOutput(deviceAddReq.getMaximumOutput())
                    .deviceName(deviceAddReq.getDeviceName())
                    .volt(deviceAddReq.getVolt())
                    .wattPerhour(deviceAddReq.getWattPerhour())
                    .build();

            deviceRepository.save(device);
        }
    }

    @Transactional
    public void deviceDelete(DeviceReq.DeviceIdReq deviceDeleteReq)
            throws BaseException, IOException {
        if (deviceDeleteReq == null) throw new BaseException(EMPTY_INFORMATION);

        String deviceName = deviceDeleteReq.getDeviceName();

        Optional<Device> deviceOptional = deviceRepository.getByDeviceName(deviceName);

        if (deviceOptional.isEmpty()) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            deviceRepository.deleteByDeviceName(deviceName);
        }
    }

    @Transactional
    public void deviceEdit(DeviceReq.DeviceAllReq deviceEditReq)
            throws BaseException, IOException {
        if (deviceEditReq == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<Device> deviceOptional = deviceRepository.getByDeviceName(deviceEditReq.getDeviceName());

        if (deviceOptional.isEmpty()) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            deviceOptional.get().setCategory(deviceEditReq.getCategory());
            deviceOptional.get().setChargingTime(deviceEditReq.getChargingTime());
            deviceOptional.get().setWattageW(deviceEditReq.getWattageW());
            deviceOptional.get().setEntireCapacity(deviceEditReq.getEntireCapacity());
            deviceOptional.get().setIsRegistered(deviceEditReq.getIsRegistered());
            deviceOptional.get().setUsingTime(deviceEditReq.getUsingTime());
            deviceOptional.get().setParentId(deviceEditReq.getParentId());
            deviceOptional.get().setContents(deviceEditReq.getContents());
            deviceOptional.get().setMAh(deviceEditReq.getMAh());
            deviceOptional.get().setMaximumOutput(deviceEditReq.getMaximumOutput());
            deviceOptional.get().setDeviceName(deviceEditReq.getDeviceName());
            deviceOptional.get().setWattPerhour(deviceEditReq.getWattPerhour());
        }
    }


    @Transactional
    public List<DeviceRes> deviceRead(DeviceReq.DeviceIdReq deviceIdReq)
            throws BaseException, IOException {
        if (deviceIdReq == null) throw new BaseException(EMPTY_INFORMATION);

        Device device;
        List<DeviceRes> deviceRes = new ArrayList<>();
        DeviceRes deviceDTO;

        device = deviceRepository.findByDeviceName(deviceIdReq.getDeviceName());

        deviceDTO = DeviceRes.builder()
                .entireCapacity(device.getEntireCapacity())
                .isRegistered(device.getIsRegistered())
                .user(device.getUser())
                .category(device.getCategory())
                .chargingTime(device.getChargingTime())
                .usingTime(device.getUsingTime())
                .wattage(device.getWattageW())
                .contents(device.getContents())
                .mAh(device.getMAh())
                .maximumOutput(device.getMaximumOutput())
                .deviceName(device.getDeviceName())
                .volt(device.getVolt())
                .wattPerhour(device.getWattPerhour())
                .build();

        deviceRes.add(deviceDTO);

        return deviceRes;
    }

    @Transactional
    public DeviceDetailRes deviceDetailRead(String deviceName)
            throws BaseException, IOException {
        if (deviceName == null) throw new BaseException(EMPTY_INFORMATION);

        DeviceDetailRes deviceDetailRes;
        Device device = deviceRepository.findByDeviceName(deviceName);

        deviceDetailRes = DeviceDetailRes.builder()
                .deviceName(device.getDeviceName())
                .wattageW(device.getWattageW())
                .contents(device.getDeviceName())
                .volt(device.getVolt())
                .category(device.getCategory())
                .build();

        return deviceDetailRes;
    }
}
