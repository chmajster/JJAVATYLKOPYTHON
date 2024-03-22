package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.Set;
import java.util.stream.Collectors;

public final class PatientMapper {

    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        final AddressTO addressTO = AddressMapper.mapToTO(patientEntity.getAddress());
        final Set<VisitTO> visitTOList= patientEntity.getVisitEntities().stream()
                        .map(visitEntity -> VisitMapper.mapToTO(visitEntity))
                        .collect(Collectors.toSet());
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setAge(patientEntity.getAge());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setAddress(addressTO);
        patientTO.setVisitTOS(visitTOList);
        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO) {
        if (patientTO == null) {
            return null;
        }
        final PatientEntity patientEntity = new PatientEntity();
        final AddressEntity addressEntity = AddressMapper.mapToEntity(patientTO.getAddress());
        final Set<VisitEntity> visitEntities = patientTO.getVisitTOS().stream()
                .map(VisitMapper::mapToEntity)
                .collect(Collectors.toSet());
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setAge(patientTO.getAge());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setAddress(addressEntity);
        patientEntity.setVisitEntities(visitEntities);
        return patientEntity;
    }
}
