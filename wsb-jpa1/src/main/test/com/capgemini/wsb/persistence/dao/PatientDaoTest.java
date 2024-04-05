package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.ConditionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void shouldFindPatientsByLastname() {
        // given
        // when
        List<PatientEntity> patientEntities = patientDao.findAllByLastname("nazwisko_test");

        // then
        assertThat(patientEntities).isNotNull();
        assertThat(patientEntities.size()).isEqualTo(1);
    }

    @Transactional
    @Test
    public void shouldFindPatientsWithVisitsGreaterThanOne() {
        // given
        // when
        List<PatientEntity> patientEntities = patientDao.findPatientsWithVisitsGreaterThan(1);

        // then
        assertThat(patientEntities).isNotNull();
        assertThat(patientEntities.size()).isEqualTo(1);
    }

    @Transactional
    @Test
    public void shouldFindPatientsAbove30YearsOld() {
        // given
        // when
        List<PatientEntity> patientEntities = patientDao.findPatientsByFieldCondition("age", 30, ConditionType.GREATER_THAN);

        // then
        assertThat(patientEntities).isNotNull();
        assertThat(patientEntities.size()).isEqualTo(1);
    }

    @Transactional
    @Test
    public void shouldFindPatientsBelow30YearsOld() {
        // given
        // when
        List<PatientEntity> patientEntities = patientDao.findPatientsByFieldCondition("age", 30, ConditionType.LESS_THAN);

        // then
        assertThat(patientEntities).isNotNull();
        assertThat(patientEntities.size()).isEqualTo(0);
    }
}