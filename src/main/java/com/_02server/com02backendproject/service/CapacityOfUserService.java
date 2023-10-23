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


    public void capacityOfUserAdd(DeviceReq.CapacityOfUserReq capacityOfUserReq)
        throws BaseException, IOException{

        if(capacityOfUserReq == null) {
            throw new BaseException(EMPTY_INFORMATION);
        } else{
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
    public void capacityOfUserDelete(Long capacityOfUserId)
        throws BaseException, IOException{
        if(capacityOfUserId == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<CapacityOfUser> capacityOfUserOptional
                = capacityOfUserRepository.findByUserCapacityId(capacityOfUserId);

        if (capacityOfUserOptional.isEmpty()){
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserRepository.deleteByUserCapacityId(capacityOfUserId);
        }
    }

    // 더티 체킹
    @Transactional
    public void capacityOfUserEdit(DeviceReq.CapacityOfUserUpdateReq capacityOfUserReq)
        throws BaseException, IOException{
        if(capacityOfUserReq == null) throw new BaseException(EMPTY_INFORMATION);

        Optional<CapacityOfUser> capacityOfUserOptional
                 = capacityOfUserRepository.findByUserCapacityId(capacityOfUserReq.getUserCapacityId());

        if(capacityOfUserOptional.isEmpty()){
            throw new BaseException(CANNOT_FIND_INFORMATION);
        } else {
            capacityOfUserOptional.get().setNowCapacity(capacityOfUserReq.getNowCapacity());
            capacityOfUserOptional.get().setAverageDays(capacityOfUserReq.getAverageDays());
        }
    }
}
