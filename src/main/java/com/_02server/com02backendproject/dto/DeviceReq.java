package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.entities.User;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

public class DeviceReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CapacityOfUserReq{
        Long userCapacityId;
        User user;
        Double nowCapacity;
        Double averageDays;
        Device parentDevice;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CapacityOfUserUpdateReq{
        Long userCapacityId;
        Double nowCapacity;
        Double averageDays;
    }
}
