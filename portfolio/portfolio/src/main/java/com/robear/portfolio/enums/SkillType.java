package com.robear.portfolio.enums;

public enum SkillType
{
    BACKEND (1),
    FRONTEND (2),
    OTHER (0);

    private final int value;

    SkillType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SkillType fromValue(int value) {
        for (SkillType type : SkillType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid SkillType Value: " + value);
    }

    public static int toInt(SkillType type)
    {
        return type.getValue();
    }
}