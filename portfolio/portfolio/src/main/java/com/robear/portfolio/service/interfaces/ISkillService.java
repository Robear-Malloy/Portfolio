package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import java.util.List;

public interface ISkillService {
    Skill addSkill(Skill skill);
    List<Skill> getAllSkills();
    Skill getSkillById(Long id);
    List<Skill> getSkillsOfType(SkillType type);
    void deleteSkill(Long id);
}