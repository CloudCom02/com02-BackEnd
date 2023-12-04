package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.entities.User;
import jakarta.transaction.Transactional;
import lombok.*;


@NoArgsConstructor
@Getter
@Transactional
public class CapacityOfUserRes {

    Long userCapacityId;
    Long user;
    Double nowCapacity;
    Double averageDays;
    Long parentDevice;

    @Builder
    public CapacityOfUserRes(Long userCapacityId, User user,
                             Double nowCapacity, Double averageDays, Device parentDevice){
        this.userCapacityId = userCapacityId;
        this.user = user.getUserId();
        this.nowCapacity = nowCapacity;
        this.averageDays = averageDays;
        this.parentDevice = parentDevice.getParentId();
    }
}