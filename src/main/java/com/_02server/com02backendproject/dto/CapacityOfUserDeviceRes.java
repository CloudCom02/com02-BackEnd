package com._02server.com02backendproject.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CapacityOfUserDeviceRes {
    String deviceName;
    String category;
    Double nowCapacity;
    Double averageDays;
    Double maximum_output;
    String usingTime;
    String contents;
    Integer mAh;
    Long capacityOfUserId;

    @Builder
    public CapacityOfUserDeviceRes(String deviceName, String category,Double nowCapacity, Long capacityOfUserId,
                    Double averageDays, Double maximum_output, String usingTime, String contents, Integer mAh){
        this.deviceName = deviceName;
        this.nowCapacity = nowCapacity;
        this.averageDays = averageDays;
        this.maximum_output = maximum_output;
        this.usingTime = usingTime;
        this.contents = contents;
        this.mAh = mAh;
        this.category = category;
        this.capacityOfUserId = capacityOfUserId;
    }
}