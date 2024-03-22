package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.Set;
import java.util.stream.Collectors;

public final class DoctorMapper {

    public static DoctorTO mapToTO(final DoctorEntity doctorEntity) {
        if (doctorEntity == null) {
            return null;
        }
        final DoctorTO doctorTO = new DoctorTO();
        final AddressTO addressTO = AddressMapper.mapToTO(doctorEntity.getAddress());
        final Set<VisitTO> visitTOSet = doctorEntity.getVisitEntities().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toSet());
        doctorTO.setId(doctorEntity.getId());
        doctorTO.setFirstName(doctorEntity.getFirstName());
        doctorTO.setLastName(doctorEntity.getLastName());
        doctorTO.setTelephoneNumber(doctorEntity.getTelephoneNumber());
        doctorTO.setEmail(doctorEntity.getEmail());
        doctorTO.setDoctorNumber(doctorEntity.getDoctorNumber());
        doctorTO.setSpecialization(doctorEntity.getSpecialization());
        doctorTO.setAddress(addressTO);
        doctorTO.setVisitEntities(visitTOSet);
        return doctorTO;
    }

    public static DoctorEntity mapToEntity(final DoctorTO doctorTO) {
        if (doctorTO == null) {
            return null;
        }
        final DoctorEntity doctorEntity = new DoctorEntity();
        final AddressEntity addressEntity = AddressMapper.mapToEntity(doctorTO.getAddress());
        final Set<VisitEntity> visitEntities = doctorTO.getVisitEntities().stream()
                .map(VisitMapper::mapToEntity)
                .collect(Collectors.toSet());
        doctorEntity.setId(doctorTO.getId());
        doctorEntity.setFirstName(doctorTO.getFirstName());
        doctorEntity.setLastName(doctorTO.getLastName());
        doctorEntity.setTelephoneNumber(doctorTO.getTelephoneNumber());
        doctorEntity.setEmail(doctorTO.getEmail());
        doctorEntity.setDoctorNumber(doctorTO.getDoctorNumber());
        doctorEntity.setSpecialization(doctorTO.getSpecialization());
        doctorEntity.setAddress(addressEntity);
        doctorEntity.setVisitEntities(visitEntities);
        return doctorEntity;
    }
}
