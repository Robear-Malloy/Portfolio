package com.robear.portfolio.util;

import com.robear.portfolio.enums.SkillType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SkillTypeConverter implements AttributeConverter<SkillType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SkillType skillType) {
        if (skillType == null) {
            return null;
        }
        return skillType.getValue(); // Maps enum to its integer value
    }

    @Override
    public SkillType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return SkillType.fromValue(dbData); // Maps integer to enum
    }
}
