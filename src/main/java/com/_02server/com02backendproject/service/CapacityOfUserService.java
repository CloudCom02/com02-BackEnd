package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.DeviceReq;
import com._02server.com02backendproject.entities.CapacityOfUser;
import com._02server.com02backendproject.global.BaseException;
import com._02server.com02backendproject.repository.CapacityOfUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static com._02server.com02backendproject.global.BaseResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapacityOfUserService {

    private final CapacityOfUserRepository capacityOfUserRepository;


    public void capacityOfUserAdd(DeviceReq.CapacityOfUserAddReq capacityOfUserAddReq)
        throws BaseException, IOException{

        if(capacityOfUserAddReq == null) {
            throw new BaseException(EMPTY_INFORMATION);
        } else{
            CapacityOfUser capacityOfUser = CapacityOfUser.builder()
                    .userCapacityId(capacityOfUserAddReq.getUserCapacityId())
                    .user(capacityOfUserAddReq.getUser())
                    .nowCapacity(capacityOfUserAddReq.getNowCapacity())
                    .averageDays(capacityOfUserAddReq.getAverageDays())
                    .parentDevice(capacityOfUserAddReq.getParentDevice())
                    .build();

            capacityOfUserRepository.save(capacityOfUser);
        }
    }

    @Transactional
    public void capacityOfUserDelete(DeviceReq.CapacityOfUserDeleteReq capacityOfUserDeleteReq)
        throws BaseException, IOException{
        if(capacityOfUserDeleteReq == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<CapacityOfUser> capacityOfUserOptional
                = capacityOfUserRepository.findByUserCapacityId(capacityOfUserDeleteReq.getUserCapacityId());

        if (capacityOfUserOptional.isEmpty()){
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserRepository.deleteByUserCapacityId(capacityOfUserDeleteReq.getUserCapacityId());
        }
    }
}
