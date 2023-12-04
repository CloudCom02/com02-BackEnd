package com._02server.com02backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DeviceReq {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeviceAddReq { //init할 때 param값 주기
        String category;
    }
}
