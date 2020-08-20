package com.oooeng.constants;

public enum PlatformEnum {
    ANDROID(1),
    IOS(2);

    private int value;

    PlatformEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
