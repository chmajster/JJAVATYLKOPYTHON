package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    PatientTO getPatientById(@PathVariable("id") Long id) {
        final PatientTO patient = patientService.findById(id);
        if (patient != null) {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

    @DeleteMapping("/{id}")
    void deletePatientById(@PathVariable("id") Long id) {
        patientService.deleteById(id);
    }
}
