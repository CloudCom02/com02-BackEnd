package com._02server.com02backendproject.dto;
import com._02server.com02backendproject.entities.User;
import jakarta.transaction.Transactional;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Time;

public class DeviceRes {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SearchDeviceRes {
        String name;
        String contents;
        String imageURI;
    }


    @NoArgsConstructor
    @Getter
    @Setter
    @Transactional
    public static class DeviceAllRes {
        Integer entireCapacity;
        Time usingTime;
        Time chargingTime;
        Double wattage;
        String category;
        String isRegistered;
        User user;
        String contents;
        Integer mAh;
        Double maximum_output;
        String deviceName;
        Double volt;
        Integer wattPerhour;


        @Builder
        public DeviceAllRes (Integer entireCapacity, Time usingTime, Time chargingTime, Double wattage,
                          String category, String isRegistered, User user, String contents,
                          Integer mAh, Double maximum_output, String deviceName, Double volt, Integer wattPerhour) {
            this.entireCapacity = entireCapacity;
            this.usingTime = usingTime;
            this.chargingTime = chargingTime;
            this.wattage = wattage;
            this.isRegistered = isRegistered;
            this.user = user;
            this.mAh = mAh;
            this.maximum_output = maximum_output;
            this.deviceName = deviceName;
            this.volt = volt;
            this.wattPerhour = wattPerhour;
            this.category = category;
            this.contents = contents;
        }
    }
}
