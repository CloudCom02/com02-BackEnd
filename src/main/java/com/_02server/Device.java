package com._02server;

public class Device {
    public String name;
    public String[] contents = new String[50];
    public String category;
    public double maximum_output; //최대 출력
    public int wattPerhour; // Wh 있다면 여기 채우기
    public int mAh; // mAh 있다면 여기 채우기
    public double volt = 3.7; // 기본 전압량 설정함.
    public String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getVolt() {
        return volt;
    }

    public void setVolt(double volt) {
        this.volt = volt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getContents() {
        return contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMaximum_output() {
        return maximum_output;
    }

    public void setMaximum_output(double maximum_output) {
        this.maximum_output = maximum_output;
    }

    public int getWattPerhour() {
        return wattPerhour;
    }

    public void setWattPerhour(int wattPerhour) {
        this.wattPerhour = wattPerhour;
    }

    public int getmAh() {
        return mAh;
    }

    public void setmAh(int mAh) {
        this.mAh = mAh;
    }
}
//완전 쌩 기본
//배터리만 고려한 객체