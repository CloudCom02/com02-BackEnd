package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.DeviceRes;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    public static List<DeviceRes.SearchDeviceRes> getDataFromDatabase(String searchKeyword) {
        List<DeviceRes.SearchDeviceRes> resList = new ArrayList<>();
        //test data
        resList.add(new DeviceRes.SearchDeviceRes("test","test","https://img.danawa.com/prod_img/500000/561/521/img/12521561_1.jpg?shrink=130:130&_v=20230908150122"));
        resList.add(new DeviceRes.SearchDeviceRes("test2","test2",""));

        return resList;
    }
}
