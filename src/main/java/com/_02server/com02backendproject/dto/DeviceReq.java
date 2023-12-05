package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public static class CapacityOfUserDeleteReq{
        Long userCapacityId;
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class BatteryLevelReq{
        Long userCapacityId;
        Double nowCapacity;
    }

}
