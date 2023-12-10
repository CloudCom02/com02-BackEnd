package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.CapacityOfUserDeviceRes;
import com._02server.com02backendproject.dto.CapacityOfUserReq;
import com._02server.com02backendproject.dto.CapacityOfUserRes;
import com._02server.com02backendproject.entities.CapacityOfUser;
import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.entities.User;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.repository.CapacityOfUserRepository;
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

import static com._02server.com02backendproject.global.BaseResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapacityOfUserService {

    private final CapacityOfUserRepository capacityOfUserRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public void capacityOfUserAdd(CapacityOfUserReq.CapacityOfUserAddReq capacityOfUserReq)
            throws BaseException, IOException {

        User user = userRepository.findByUserId(capacityOfUserReq.getUserId());

        if (capacityOfUserReq == null) {
            throw new BaseException(EMPTY_INFORMATION);
        } else if (user == null) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            CapacityOfUser capacityOfUser = CapacityOfUser.builder()
                    .user(user)
                    .deviceName(capacityOfUserReq.getDeviceName())
                    .averageDays(capacityOfUserReq.getAverageDays())
                    .category(capacityOfUserReq.getCategory())
                    .build();

            capacityOfUserRepository.save(capacityOfUser);
        }
    }

    @Transactional
    public void capacityOfUserDelete(CapacityOfUserReq.CapacityOfUserDeleteReq capacityOfUserDeleteReq)
            throws BaseException, IOException {
        if (capacityOfUserDeleteReq == null) throw new BaseException(EMPTY_INFORMATION);

        Long capacityOfUserId = capacityOfUserDeleteReq.getUserCapacityId();

        Optional<CapacityOfUser> capacityOfUserOptional
                = capacityOfUserRepository.findByUserCapacityId(capacityOfUserId);

        if (capacityOfUserOptional.isEmpty()) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else if (userRepository.existsByUserId(capacityOfUserId)) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserRepository.deleteByUserCapacityId(capacityOfUserId);
        }
    }

    // 더티 체킹
    @Transactional
    public void capacityOfUserEdit(CapacityOfUserReq.CapacityOfUserUpdateReq capacityOfUserReq)
            throws BaseException, IOException {
        if (capacityOfUserReq == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<CapacityOfUser> capacityOfUserOptional
                = capacityOfUserRepository.findByUserCapacityId(capacityOfUserReq.getUserCapacityId());

        if (capacityOfUserOptional.isEmpty()) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else if (userRepository.existsByUserId(capacityOfUserReq.getUserCapacityId())) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserOptional.get().setAverageDays(capacityOfUserReq.getAverageDays());
            capacityOfUserOptional.get().setDeviceName(capacityOfUserReq.getDeviceName());
            capacityOfUserOptional.get().setNowCapacity(capacityOfUserReq.getNowCapacity());
        }
    }


    public void BatteryLevelUpdate(CapacityOfUserReq.BatteryLevelReq batteryLevelReq)
            throws BaseException, IOException {
        if (batteryLevelReq == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<CapacityOfUser> capacityOfUserOptional
                = capacityOfUserRepository.findByUserCapacityId(batteryLevelReq.getUserCapacityId());

        if (capacityOfUserOptional.isEmpty()) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserOptional.get().setNowCapacity(batteryLevelReq.getNowCapacity());
        }
    }

    @Transactional
    public List<CapacityOfUserRes> capacityOfUserRead(Long userId)
            throws BaseException, IOException {
        if (userId == null) throw new BaseException(EMPTY_INFORMATION);

        List<CapacityOfUser> capacityOfUsers;
        List<CapacityOfUserRes> capacityOfUserRes = new ArrayList<>();
        CapacityOfUserRes deviceDTO;

        if (!userRepository.existsByUserId(userId)) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            //capacityOfUsers = capacityOfUserRepository.findAllByUser_UserId(userId);
            capacityOfUsers = capacityOfUserRepository.findByUser_UserId(userId);

            for (int i = 0; i < capacityOfUsers.size(); i++) {
                deviceDTO = CapacityOfUserRes.builder()
                        .deviceName(capacityOfUsers.get(i).getDeviceName())
                        .category(capacityOfUsers.get(i).getCategory())
                        .batteryLevel(capacityOfUsers.get(i).getNowCapacity())
                        .build();

                capacityOfUserRes.add(deviceDTO);
            }
            return capacityOfUserRes;
        }
    }

    @Transactional
    public CapacityOfUserDeviceRes deviceOfListRead(String deviceName)
        throws BaseException, IOException {
            if (deviceName == null) throw new BaseException(EMPTY_INFORMATION);

            CapacityOfUserDeviceRes capacityOfUserDeviceRes;

            CapacityOfUser capacityOfUser = capacityOfUserRepository.findByDeviceName(deviceName);
            Device device = deviceRepository.findByDeviceName(deviceName);

            capacityOfUserDeviceRes = CapacityOfUserDeviceRes.builder()
                    .mAh(device.getMAh())
                    .contents(device.getContents())
                    .usingTime(device.getUsingTime().toString())
                    .maximum_output(device.getMaximum_output())
                    .capacityOfUserId(capacityOfUser.getUserCapacityId())
                    .averageDays(capacityOfUser.getAverageDays())
                    .nowCapacity(capacityOfUser.getNowCapacity())
                    .deviceName(capacityOfUser.getDeviceName())
                    .category(device.getCategory()).build();

            return capacityOfUserDeviceRes;
    }
}
