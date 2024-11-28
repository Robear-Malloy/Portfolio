package com.robear.portfolio.enums;

public enum TechType {
    PROJECT (1),
    EXPERIENCE (2);

    private final int value;

    TechType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TechType fromValue(int value) {
        for (TechType type : TechType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TechType Value: " + value);
    }

    public static int toInt(TechType type)
    {
        return type.getValue();
    }
}

