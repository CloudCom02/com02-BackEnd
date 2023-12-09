package com._02server.com02backendproject.dto;

import lombok.Builder;

public class DeviceDetailRes {
    String deviceName;
    Double wattageW;
    Double nowCapacity;
    String contents;
    Double volt;
    String category;


    @Builder
    public DeviceDetailRes (String deviceName, Double wattageW, Double nowCapacity,
                      String contents, Double volt, String category) {
        this.deviceName = deviceName;
        this.wattageW = wattageW;
        this.nowCapacity = nowCapacity;
        this.contents = contents;
        this.volt = volt;
        this.category = category;
    }
}
