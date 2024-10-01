package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.SkillController;
import com.robear.portfolio.enums.SkillType;
import com.robear.portfolio.exception.SkillNotFoundException;
import com.robear.portfolio.model.Skill;
import com.robear.portfolio.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SkillControllerTest {

    @InjectMocks
    private SkillController skillController;

    @Mock
    private SkillService skillService;

    @BeforeEach
    public void setup() { MockitoAnnotations.openMocks(this); }

    @Test
    public void testWhenNewSkillCreated() {
        Skill skill = createTestSkill();

        when(skillService.addSkill(any(Skill.class))).thenReturn(skill);

        ResponseEntity<Skill> response = skillController.createSkill(skill);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName())
                .isEqualTo(skill.getName());
    }

    @Test
    public void testNewSkillCreatedWhenThrowsException() {
        Skill skill = createTestSkill();

        when(skillService.addSkill(any(Skill.class))).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<Skill> response = skillController.createSkill(skill);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllSkillsWhenSkillsPresent() {
        Skill skill = createTestSkill();
        List<Skill> skills = Collections.singletonList(skill);

        when(skillService.getAllSkills()).thenReturn(skills);

        ResponseEntity<List<Skill>> response = skillController.getAllSkills();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(skills.size());
        assertThat(response.getBody().getFirst().getName())
                .isEqualTo(skills.getFirst().getName());
    }

    @Test
    public void testGetAllSkillsWhenNoSkills() {
        when(skillService.getAllSkills()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Skill>> response = skillController.getAllSkills();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    public void testGetAllSkillsWhenThrowsException() {
        when(skillService.getAllSkills()).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<List<Skill>> response = skillController.getAllSkills();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetSkillsByTypeWhenRecordExists () {
        Skill skill = createTestSkill();
        List<Skill> skills = Collections.singletonList(skill);

        when(skillService.getSkillsOfType(SkillType.BACKEND)).thenReturn(skills);

        ResponseEntity<List<Skill>> response =
                skillController.getSkillsByType(SkillType.toInt(SkillType.BACKEND));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(skills.size());
        assertThat(response.getBody().getFirst().getName())
                .isEqualTo(skills.getFirst().getName());
        assertThat(response.getBody().getFirst().getType())
                .isEqualTo(skills.getFirst().getType());
    }

    @Test
    public void testGetSkillsByTypeWhenRecordDoesNotExists () {
        when(skillService.getSkillsOfType(SkillType.BACKEND))
                .thenThrow(new SkillNotFoundException((SkillType.BACKEND)));

        ResponseEntity<List<Skill>> response =
                skillController.getSkillsByType(SkillType.toInt(SkillType.BACKEND));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetSkillsByTypeWhenThrowsException () {
        when(skillService.getSkillById(1L))
                .thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<Skill> response = skillController.getSkillById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetSkillByIdWhenRecordExists() {
        Skill skill = createTestSkill();

        when(skillService.getSkillById(1L)).thenReturn(skill);

        ResponseEntity<Skill> response = skillController.getSkillById(skill.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(skill.getName());
        assertThat(response.getBody().getId()).isEqualTo(skill.getId());
    }

    @Test
    public void testGetSkillByIdWhenRecordDoesNotExist() {
        when(skillService.getSkillById(1L)).thenThrow(new SkillNotFoundException(1L));

        ResponseEntity<Skill> response = skillController.getSkillById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetSkillByIdWhenThrowsException () {
        when(skillService.getSkillById(1L)).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<Skill> response =
                skillController.getSkillById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteSkillByIdWhenRecordExists() {
        doNothing().when(skillService).deleteSkill(1L);

        ResponseEntity<Void> response =
                skillController.deleteSkillById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        verify(skillService).deleteSkill(1L);
    }

    @Test
    public void testDeleteSkillByIdWhenSkillNotFound() {
        doThrow(new SkillNotFoundException("Skill Not Found"))
                .when(skillService).deleteSkill(1L);

        ResponseEntity<Void> response = skillController.deleteSkillById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteSkillByIdWhenThrowsException() {
        doThrow(new RuntimeException("Server Error"))
                .when(skillService).deleteSkill(1L);

        ResponseEntity<Void> response = skillController.deleteSkillById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Skill createTestSkill() {
        Skill skill = new Skill();
        skill.setId(1L);
        skill.setName("Test Skill");
        skill.setType(SkillType.BACKEND);
        return skill;
    }
}
