//package com.robear.portfolio.unit.service;
//
//import com.robear.portfolio.service.SkillService;
//import com.robear.portfolio.enums.SkillType;
//import com.robear.portfolio.exception.SkillNotFoundException;
//import com.robear.portfolio.model.Skill;
//import com.robear.portfolio.repository.SkillRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class SkillServiceTest {
//    @InjectMocks
//    private SkillService skillService;
//
//    @Mock
//    private SkillRepository skillRepository;
//
//    @BeforeEach
//    public void SkillServiceTest() { MockitoAnnotations.openMocks(this); }
//
//    @Test
//    public void testAddSkillWhenSuccessful() {
//        Skill skill = createTestSkill();
//        when(skillRepository.save(skill)).thenReturn(skill);
//
//        Skill result = skillService.addSkill(skill);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getName())
//                .isEqualTo(skill.getName());
//        verify(skillRepository).save(skill);
//    }
//
//    @Test
//    public void testAddSkillWhenThrowsException() {
//        Skill skill = createTestSkill();
//        when(skillRepository.save(skill))
//                .thenThrow(new RuntimeException("Database Error"));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            skillService.addSkill(skill);
//        });
//
//        assertThat(exception.getMessage())
//                .isEqualTo("Unable to add skill");
//        verify(skillRepository).save(skill);
//    }
//
//    @Test
//    public void testGetAllSkillsWhenSuccessful() {
//        Skill skill = createTestSkill();
//        List<Skill> skills = Collections.singletonList(skill);
//        when(skillRepository.findAll()).thenReturn(skills);
//
//        List<Skill> result = skillRepository.findAll();
//
//        assertThat(result).isNotNull();
//        assertThat(result.getFirst().getName())
//                .isEqualTo(skills.getFirst().getName());
//        verify(skillRepository).findAll();
//    }
//
//    @Test
//    public void testGetAllSkillsWhenThrowsException() {
//        when(skillRepository.findAll())
//                .thenThrow(new RuntimeException("Database Error"));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            skillService.getAllSkills();
//        });
//
//        assertThat(exception.getMessage())
//                .isEqualTo("Unable to retrieve skills");
//        verify(skillRepository).findAll();
//    }
//
//    @Test
//    public void testGetSkillByIdWhenSuccessful() {
//        Skill skill = createTestSkill();
//        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
//
//        Skill result = skillService.getSkillById(1L);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getName())
//                .isEqualTo(skill.getName());
//        verify(skillRepository).findById(1L);
//    }
//
//    @Test
//    public void testGetSkillByIdWhenThrowsException() {
//        Skill skill = createTestSkill();
//        when(skillRepository.findById(skill.getId()))
//                .thenReturn(Optional.empty());
//
//        SkillNotFoundException exception = assertThrows(SkillNotFoundException.class, () -> {
//            skillService.getSkillById(skill.getId());
//        });
//
//        assertThat(exception.getMessage()).contains("Skill not found with ID: " + skill.getId());
//        verify(skillRepository).findById(skill.getId());
//    }
//
//    @Test
//    public void testGetSkillOfTypeWhenSuccessful() {
//        Skill skill = createTestSkill();
//        List<Skill> skills = Collections.singletonList(skill);
//        when(skillRepository.findByType(skill.getType()))
//                .thenReturn(skills);
//
//        List<Skill> result = skillRepository.findByType(skill.getType());
//
//        assertThat(result).isNotNull();
//        assertThat(result.getFirst().getType())
//                .isEqualTo(skills.getFirst().getType());
//        verify(skillRepository).findByType(skills.getFirst().getType());
//    }
//
//    @Test
//    public void testGetSkillOfTypeWhenNoSkills() {
//        Skill skill = createTestSkill();
//        when(skillRepository.findByType(skill.getType()))
//                .thenReturn(Collections.emptyList());
//
//        SkillNotFoundException exception = assertThrows(SkillNotFoundException.class, () -> {
//            skillService.getSkillsOfType(skill.getType());
//        });
//
//        assertThat(exception.getMessage())
//                .isEqualTo("Skill not found with Type: " + skill.getType());
//        verify(skillRepository).findByType(skill.getType());
//    }
//
//    @Test
//    public void testDeleteSkillWhenSuccessful() {
//        Skill skill = createTestSkill();
//        when(skillRepository.findById(skill.getId()))
//                .thenReturn(Optional.of(skill));
//
//        skillService.deleteSkill(skill.getId());
//
//        verify(skillRepository).deleteById(skill.getId());
//    }
//
//    @Test
//    public void testDeleteSkillWhenRecordDoesNotExist() {
//        when(skillRepository.findById(1L))
//                .thenReturn(Optional.empty());
//
//        SkillNotFoundException exception = assertThrows(SkillNotFoundException.class, () -> {
//                skillService.deleteSkill(1L);
//        });
//
//        assertThat(exception.getMessage()).isEqualTo("Skill not found with ID: 1");
//        verify(skillRepository, never()).deleteById(1L);
//    }
//
//    private Skill createTestSkill() {
//        Skill skill = new Skill();
//        skill.setId(1L);
//        skill.setName("Test Skill");
//        skill.setType(SkillType.BACKEND);
//        return skill;
//    }
//}
