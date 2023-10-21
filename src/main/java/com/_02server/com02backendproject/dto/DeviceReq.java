package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.entities.User;
import lombok.*;

public class DeviceReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeviceOfUserAddReq{
        Long userCapacityId;
        User user;
        Double nowCapacity;
        Double averageDays;
        Device parentDevice;
    }
}
