package com.ryo.springbootsimplerestapi.entity;

public enum GenderEnum {
    male,
    female;

    public static GenderEnum fromString(String value) {
        if (value != null) {
            for (GenderEnum gender : GenderEnum.values()) {
                if (gender.name().equalsIgnoreCase(value)) {
                    return gender;
                }
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
