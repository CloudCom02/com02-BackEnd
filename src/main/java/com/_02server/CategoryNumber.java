package com._02server;

public enum CategoryNumber {
    보조배터리(20322920),
    무선이어폰(12237349),
    무선헤드폰(12237350),
    미러리스카메라(12237505),
    DSLR카메라(12237506),
    하이엔드카메라(12237507),
    액션캠코더(12237508);

    int cate;
    CategoryNumber(int i) {
        this.cate = i;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
