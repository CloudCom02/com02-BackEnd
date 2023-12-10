package com._02server.com02backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}

