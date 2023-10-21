package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.DeviceReq;
import com._02server.com02backendproject.entities.CapacityOfUser;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.repository.CapacityOfUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com._02server.com02backendproject.global.BaseResponseStatus.EMPTY_INFORMATION;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapacityOfUserService {

    private final CapacityOfUserRepository capacityOfUserRepository;


    public void deviceOfUserAdd(DeviceReq.DeviceOfUserAddReq deviceOfUserAddReq)
        throws BaseException, IOException{

        if(deviceOfUserAddReq == null) {
            throw new BaseException(EMPTY_INFORMATION);
        } else{
            CapacityOfUser capacityOfUser = CapacityOfUser.builder()
                    .userCapacityId(deviceOfUserAddReq.getUserCapacityId())
                    .user(deviceOfUserAddReq.getUser())
                    .nowCapacity(deviceOfUserAddReq.getNowCapacity())
                    .averageDays(deviceOfUserAddReq.getAverageDays())
                    .parentDevice(deviceOfUserAddReq.getParentDevice())
                    .build();

            capacityOfUserRepository.save(capacityOfUser);
        }
    }
}
