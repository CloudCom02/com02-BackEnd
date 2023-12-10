package com._02server.com02backendproject.dto;

import com._02server.com02backendproject.entities.Device;
import com._02server.com02backendproject.entities.User;
import jakarta.transaction.Transactional;
import lombok.*;


@NoArgsConstructor
@Getter
@Transactional
public class CapacityOfUserRes {

    String deviceName;
    String category;
    Double batteryLevel;
    @Builder
    public CapacityOfUserRes(String deviceName, String category, Double batteryLevel){
        this.deviceName = deviceName;
        this.category = category;
        this.batteryLevel = batteryLevel;
    }
}