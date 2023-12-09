package com._02server.com02backendproject.service;

import com._02server.com02backendproject.entities.Category;
import com._02server.com02backendproject.entities.Device;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterNMapper {
    public Map<String,String> datamap;
    public Category category;
    static Pattern wattPattern = Pattern.compile("\\b(\\d+)W\\b");
    static Pattern whPattern = Pattern.compile("\\b(\\d+)wh\\b");
    static Pattern capacityPattern = Pattern.compile("\\b(\\d+)mah\\b");


    public FilterNMapper(Map<String, String> newDataMap,Category category){

        this.datamap = newDataMap;
        this.category = category;

    }
    public Device mapping(String name, String contents) {
        Device result = new Device();
        result.setDeviceName(name);
        result.setCategory(category.toString());

        String[] conArray = contents.split("/");
        for(String s : conArray) {

            // 배터리 용량 mah 찾기
            if(s.toLowerCase().contains("mah")) {
                Matcher matcher = capacityPattern.matcher(s.toLowerCase());
                // 찾은 패턴에서 숫자 추출
                while (matcher.find()) {
                    String match = matcher.group(1);
                    result.setMAh(Integer.parseInt(match));
                }
            }


            //가정이 들어감
            //출력량 W 찾기 -> 일단 모든 기기를 최대 출력으로 사용할 것을 가정하는중....
            if(s.contains("최대출력")) {
                Matcher matcher = wattPattern.matcher(s);
                // 찾은 패턴에서 숫자 추출
                while (matcher.find()) {
                    String match = matcher.group(1);
                    result.setMaximum_output(Double.parseDouble(match));
                }
            }

            //배터리 파워 용량 찾기
            if(s.toLowerCase().contains("wh")) {
                Matcher matcher = whPattern.matcher(s.toLowerCase());
                // 찾은 패턴에서 숫자 추출
                while (matcher.find()) {
                    String match = matcher.group(1);
                    result.setWattPerhour(Integer.parseInt(match));
                }
            }

            if(s.contains("http")) {
                result.setImageURL(s.trim());
            }



        }
        result.setContents(contents);

        return result;
    }
}
