package com.dpms.service;

import com.dpms.bean.Patient;
import com.dpms.repository.AppointmentDetailsRepository;
import com.dpms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    public Patient addPatient(Patient patient){
        Patient insertedPatient = patientRepository.save(patient);
        return insertedPatient;
    }

    public List<Patient> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }
}
