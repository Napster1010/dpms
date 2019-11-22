package com.dpms.service;

import com.dpms.bean.*;
import com.dpms.dto.PatientDto;
import com.dpms.repository.AppointmentDetailsRepository;
import com.dpms.repository.PatientRepository;
import com.dpms.repository.PrescriptionRespository;
import com.dpms.repository.UserRepository;
import com.dpms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrescriptionRespository prescriptionRespository;

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

    public Patient getByPatientUsername(String patientUsername){
        User patientUser = userRepository.findByUsername(patientUsername);
        Patient patient = patientRepository.findByUser(patientUser);
        return patient;
    }

    public List<Doctor> getAllAssociatedDoctors(String patientUsername){
        Patient patient = getByPatientUsername(patientUsername);
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByPatientAndStatus(patient, Constants.APPOINTMENT_BOOKED);
        List<Doctor> doctors = new ArrayList<>();
        for(AppointmentDetails appointmentDetails1: appointmentDetails)
            doctors.add(appointmentDetails1.getDoctor());

        return doctors;
    }

    public List<Prescription> getPrescriptionByPatientUsername(String patientUsername){
        Patient patient = getByPatientUsername(patientUsername);
        List<Prescription> prescriptions = prescriptionRespository.findByPatient(patient);
        return prescriptions;
    }
}
