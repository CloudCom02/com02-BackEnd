package com._02server;

public enum Category {
    보조배터리(20322920,"subBattery"),
    무선이어폰(12237349,"bluetoothEar"),
    무선헤드폰(12237350,"bluetoothHead"),
    블루투스스피커(10224954,"bluetoothSpeaker"),
    미러리스카메라(12237505,"mirrorlessCam"),
    DSLR카메라(12237506,"dslrCam"),
    하이엔드카메라(12237507,"highendCam"),
    액션캠코더(12237508,"actionCam");

    int cate;
    String eng;
    Category(int i, String s) {
        this.cate = i;
        this.eng = s;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
