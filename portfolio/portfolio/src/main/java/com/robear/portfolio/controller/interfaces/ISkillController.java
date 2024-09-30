package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Skill;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ISkillController {
    ResponseEntity<Skill> createSkill(Skill skill);
    ResponseEntity<List<Skill>> getAllSkills();
    ResponseEntity<List<Skill>> getSkillsByType(int type);
    ResponseEntity<Skill> getSkillById(Long id);
    ResponseEntity<Void> deleteSkillById(Long id);
}