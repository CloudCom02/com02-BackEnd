package com._02server.com02backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CapacityOfUserReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CapacityOfUserAddReq{
        Long userId;
        String deviceName;
        String category;
        Double averageDays;

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
        String deviceName;
        String category;
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CapacityOfUserDeviceReq {
        Long userId;
        String deviceName;
    }
}
