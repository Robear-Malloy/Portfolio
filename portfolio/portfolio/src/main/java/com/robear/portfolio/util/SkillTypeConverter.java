package com.robear.portfolio.util;

import com.robear.portfolio.enums.SkillType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SkillTypeConverter implements Converter<Integer, SkillType> {
    @Override
    public SkillType convert(Integer source) {
        return SkillType.fromValue(source);
    }
}
