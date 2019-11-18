package com.dpms.service;

import com.dpms.bean.Patient;
import com.dpms.bean.User;
import com.dpms.dto.PatientDto;
import com.dpms.repository.AppointmentDetailsRepository;
import com.dpms.repository.PatientRepository;
import com.dpms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public Patient addPatient(PatientDto patientDto){
        User user = new User(patientDto.getUsername(), patientDto.getPassword(), "patient");
        User insertedUser = userRepository.save(user);
        Patient patient = new Patient(patientDto.getName(), patientDto.getContactNo(), patientDto.getAddress(), insertedUser);
        Patient insertedPatient = patientRepository.save(patient);
        return insertedPatient;
    }

    public List<Patient> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }
}
