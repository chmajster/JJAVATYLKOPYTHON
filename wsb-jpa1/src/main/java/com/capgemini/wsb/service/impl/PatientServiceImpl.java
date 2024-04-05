package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.mapper.VisitMapper;
import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.dao.VisitDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;
    private final VisitDao visitDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao, VisitDao visitDao) {
        this.patientDao = patientDao;
        this.visitDao = visitDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity patient = patientDao.findOne(id);
        return PatientMapper.mapToTO(patient);
    }

    @Override
    public void deleteById(Long id) {
        patientDao.delete(id);
    }

    @Override
    public List<PatientTO> findAllByLastname(String lastname) {
        return patientDao.findAllByLastname(lastname).stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitTO> findVisitsByPatientId(Long patientId) {
        return visitDao.findAllByPatientId(patientId).stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
