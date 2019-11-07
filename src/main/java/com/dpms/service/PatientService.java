package com.dpms.service;

import com.dpms.bean.Patient;
import com.dpms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient){
        Patient insertedPatient = patientRepository.save(patient);
        return insertedPatient;
    }
}
