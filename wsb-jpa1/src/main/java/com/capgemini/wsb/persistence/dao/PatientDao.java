package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.ConditionType;

import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    List<PatientEntity> findAllByLastname(String lastName);

    List<PatientEntity> findPatientsWithVisitsGreaterThan(int numberOfVisits);

    List<PatientEntity> findPatientsByFieldCondition(String fieldName, Object value, ConditionType condition);
}
