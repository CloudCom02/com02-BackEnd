package com._02server.com02backendproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceDetailRes {
    String deviceName;
    String category;
    Integer entireCapacity;
    Double maximum_output;
    String contents;
    String usingTime;
    Double volt;

    @Builder
    public DeviceDetailRes (String deviceName, Integer entireCapacity, Double maximum_output,
                      String contents, String usingTime, Double volt, String category) {
        this.deviceName = deviceName;
        this.entireCapacity = entireCapacity;
        this.maximum_output = maximum_output;
        this.contents = contents;
        this.volt = volt;
        this.category = category;
        this.usingTime = usingTime;
    }
}
