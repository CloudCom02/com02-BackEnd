package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.CapacityOfUserRes;
import com._02server.com02backendproject.entities.CapacityOfUser;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.repository.CapacityOfUserRepository;
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

    public void capacityOfUserAdd(DeviceReq.CapacityOfUserReq capacityOfUserReq)
            throws BaseException, IOException {

        if (capacityOfUserReq == null) {
            throw new BaseException(EMPTY_INFORMATION);
        } else if (userRepository.existsByUserId(capacityOfUserReq.getUser().getUserId())) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            CapacityOfUser capacityOfUser = CapacityOfUser.builder()
                    .userCapacityId(capacityOfUserReq.getUserCapacityId())
                    .user(capacityOfUserReq.getUser())
                    .nowCapacity(capacityOfUserReq.getNowCapacity())
                    .averageDays(capacityOfUserReq.getAverageDays())
                    .parentDevice(capacityOfUserReq.getParentDevice())
                    .build();

            capacityOfUserRepository.save(capacityOfUser);
        }
    }

    @Transactional
    public void capacityOfUserDelete(DeviceReq.CapacityOfUserDeleteReq capacityOfUserDeleteReq)
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
    public void capacityOfUserEdit(DeviceReq.CapacityOfUserUpdateReq capacityOfUserReq)
            throws BaseException, IOException {
        if (capacityOfUserReq == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<CapacityOfUser> capacityOfUserOptional
                = capacityOfUserRepository.findByUserCapacityId(capacityOfUserReq.getUserCapacityId());

        if (capacityOfUserOptional.isEmpty()) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else if (userRepository.existsByUserId(capacityOfUserReq.getUserCapacityId())) {
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserOptional.get().setNowCapacity(capacityOfUserReq.getNowCapacity());
            capacityOfUserOptional.get().setAverageDays(capacityOfUserReq.getAverageDays());
        }
    }


    public void BatteryLevelUpdate(DeviceReq.BatteryLevelReq batteryLevelReq)
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
                        .userCapacityId(capacityOfUsers.get(i).getUserCapacityId())
                        .user(capacityOfUsers.get(i).getUser())
                        .nowCapacity(capacityOfUsers.get(i).getNowCapacity())
                        .averageDays(capacityOfUsers.get(i).getAverageDays())
                        .parentDevice(capacityOfUsers.get(i).getParentDevice())
                        .build();

                capacityOfUserRes.add(deviceDTO);
            }

            return capacityOfUserRes;
        }
    }
}
