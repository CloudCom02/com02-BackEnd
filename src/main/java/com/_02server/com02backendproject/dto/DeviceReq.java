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
    public static class DeviceAllReq{
        Long parentId;
        Double entireCapacity;
        Time usingTime;
        Time chargingTime;
        Double wattageW;
        String category;
        String imageURL;
        String isRegistered;
        User user;
        String contents;
        Integer mAh;
        Double maximumOutput;
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
