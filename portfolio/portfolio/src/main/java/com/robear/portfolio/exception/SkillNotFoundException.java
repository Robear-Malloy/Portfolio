package com.robear.portfolio.exception;

import com.robear.portfolio.enums.SkillType;

public class SkillNotFoundException extends RuntimeException {

    public SkillNotFoundException(Long id) {
        super("Skill not found with ID: " + id);
    }

    public SkillNotFoundException(SkillType type) {
        super("Skill not found with Type: " + type);
    }

    public SkillNotFoundException(String message) {
        super(message);
    }
}
