package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;

public interface PatientService {
    public PatientTO findById(Long id);
    public void deleteById(Long id);
}
