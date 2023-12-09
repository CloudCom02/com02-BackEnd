package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Time;

public class DeviceReq {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeviceAddReq { //init할 때 param값 주기
        String category;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeviceAllReq{
        Long parentId;
        Integer entireCapacity;
        Time usingTime;
        Time chargingTime;
        Double wattageW;
        String category;
        String imageURL;
        String isRegistered;
        User user;
        String contents;
        Integer mAh;
        Double maximum_output;
        String deviceName;
        Double volt;
        Integer wattPerhour;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeviceIdReq{
        String deviceName;
    }
}
