package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.DoctorMapper;
import com.capgemini.wsb.mapper.VisitMapper;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.dao.VisitDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private VisitDao visitDao;

    @Transactional
    @Test
    void shouldFindPatientById() {
        // given
        // when
        final PatientTO patient = patientService.findById(1L);

        // then
        assertThat(patient).isNotNull();
        assertThat(patient.getId()).isEqualTo(1L);
        assertThat(patient.getFirstName()).isEqualTo("imie_test");
        assertThat(patient.getLastName()).isEqualTo("nazwisko_test");
        assertThat(patient.getAge()).isEqualTo(34);
        assertThat(patient.getTelephoneNumber()).isEqualTo("123556666");
        assertThat(patient.getPatientNumber()).isEqualTo("34");
        final AddressTO address = patient.getAddress();
        assertThat(address).isNotNull();
        assertThat(address.getId()).isEqualTo(2L);
        assertThat(address.getAddressLine1()).isEqualTo("nowy addres");
        assertThat(address.getAddressLine2()).isEqualTo("nowy addres2");
        assertThat(address.getCity()).isEqualTo("city123");
        assertThat(address.getPostalCode()).isEqualTo("34-312");
        final Set<VisitTO> visitTOSet = patient.getVisitTOS();
        assertThat(visitTOSet).isNotNull();
        assertThat(visitTOSet.size()).isEqualTo(2);
    }

    @Transactional
    @Test
    void shouldDeletePatientById() {
        // given
        // when
        patientService.deleteById(1L);

        // then
        final PatientTO patient = patientService.findById(1L);
        final List<VisitTO> visitTOList = visitDao.findAll().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
        final List<DoctorTO> doctorTOList = doctorDao.findAll().stream()
                .map(DoctorMapper::mapToTO)
                .collect(Collectors.toList());
        assertThat(patient).isNull();
        assertThat(visitTOList.size()).isEqualTo(0);
        assertThat(doctorTOList.size()).isEqualTo(2);
    }

    @Transactional
    @Test
    void shouldFindVisitsByPatientId() {
        // given
        // when
        final List<VisitTO> visitTOList = patientService.findVisitsByPatientId(1L);

        // then
        assertThat(visitTOList).isNotNull();
        assertThat(visitTOList.size()).isEqualTo(2);
    }

}