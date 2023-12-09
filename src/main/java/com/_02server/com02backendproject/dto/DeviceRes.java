package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.User;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@Getter
@Transactional
public class DeviceRes {
    Double entireCapacity;
    Time usingTime;
    Time chargingTime;
    Double wattage;
    String category;
    String isRegistered;
    User user;
    String contents;
    Integer mAh;
    Double maximumOutput;
    String deviceName;
    Double volt;
    Integer wattPerhour;


    @Builder
    public DeviceRes (Double entireCapacity, Time usingTime, Time chargingTime, Double wattage,
                            String category, String isRegistered, User user, String contents,
                            Integer mAh, Double maximumOutput, String deviceName, Double volt, Integer wattPerhour) {
        this.entireCapacity = entireCapacity;
        this.usingTime = usingTime;
        this.chargingTime = chargingTime;
        this.wattage = wattage;
        this.isRegistered = isRegistered;
        this.user = user;
        this.mAh = mAh;
        this.maximumOutput = maximumOutput;
        this.deviceName = deviceName;
        this.volt = volt;
        this.wattPerhour = wattPerhour;
        this.category = category;
        this.contents = contents;
    }
}
