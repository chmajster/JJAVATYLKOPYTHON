package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.dto.MedicalTreatmentTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.Set;
import java.util.stream.Collectors;

public final class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        final Set<MedicalTreatmentTO> medicalTreatmentTOSet = visitEntity.getMedicalTreatmentEntities().stream()
                        .map(MedicalTreatmentMapper::mapToTO)
                        .collect(Collectors.toSet());
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setPatientId(visitEntity.getPatient().getId());
        visitTO.setDoctorId(visitEntity.getDoctor().getId());
        visitTO.setMedicalTreatmentTO(medicalTreatmentTOSet);
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        final VisitEntity visitEntity = new VisitEntity();
        final Set<MedicalTreatmentEntity> medicalTreatmentEntitySet = visitTO.getMedicalTreatmentTO().stream()
                .map(MedicalTreatmentMapper::mapToEntity)
                .collect(Collectors.toSet());
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setPatientId(visitTO.getPatientId());
        visitEntity.setDoctorId(visitTO.getDoctorId());
        visitEntity.setMedicalTreatmentEntities(medicalTreatmentEntitySet);
        return visitEntity;
    }
}
