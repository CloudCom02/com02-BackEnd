package com._02server;

public class BatteryTimeCalculator {
    //10퍼센트단위로 계산 후 10퍼센트 감소 예상 시 마다 데이터베이스 업데이트
    //Wh의 단위가 있는 경우, 시간 구하기
    public static void calclulateWithWWh(String[] args) {
        int initialChargePercentage = 80; // 현재 충전 상태 (예: 80%) - 사용자 기기에서 받음
        int lowerThreshold = 30; // 원하는 충전 상태의 하한값 (예: 20%)  - 기본 셋팅 값

        double batteryEnergy = 37; // 보조배터리의 에너지 (Wh)
        double phoneConsumption = 10.8; // 휴대폰에 의한 감소 (W)
        double decreaseRate = (phoneConsumption / batteryEnergy) * 100;

        // 시간 = (초기 충전 상태 - 목표 충전 상태) / 감소 비율
        double timeRequired = (initialChargePercentage - lowerThreshold) / decreaseRate;


        System.out.println("충전 상태가 " + lowerThreshold + "% 이하로 떨어지는데 " + timeRequired + " 시간이 소요됩니다.");
    }

    //mAh 단위로 용량 표시된 경우 시간 구하기
    public static void calculateWithWmAh(String[] args) {
        double batteryVoltage = 3.7; // 보조배터리 전압 (V)
        double batteryCapacity = 10000*0.8; // 보조배터리 용량 (mAh)
        double phoneConsumption = 10.8; // 휴대폰에 의한 감소 (W)

        int initialChargePercentage = 80; // 현재 충전 상태 (예: 80%) - 사용자 기기에서 받음
        int lowerThreshold = 30; // 원하는 충전 상태의 하한값 (예: 20%)  - 기본 셋팅 값

        double batteryEnergy = (batteryVoltage * batteryCapacity) / 1000; // mAh를 Wh로 변환
        // 시간당 감소 비율 계산
        double decreaseRate = (phoneConsumption / batteryEnergy) * 100;

        double timeRequired = (initialChargePercentage - lowerThreshold) / decreaseRate;
        System.out.println("감소 비율: " + decreaseRate + " % per hour");
        System.out.println("10% 줄어드는데 "+ 10/decreaseRate +"시간 걸림");
    }

    public static void main(String[] args) {
        int initialChargePercentage = 80; // 현재 충전 상태 (예: 80%) - 사용자 기기에서 받음
    }

}