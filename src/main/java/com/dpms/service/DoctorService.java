package com.dpms.service;

import com.dpms.bean.*;
import com.dpms.dto.DoctorDto;
import com.dpms.dto.PatientDocumentDto;
import com.dpms.dto.PrescriptionDto;
import com.dpms.repository.*;
import com.dpms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientDocumentRepository patientDocumentRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PrescriptionRespository prescriptionRespository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    public Doctor addDoctor(DoctorDto doctorDto){
        User user = new User(doctorDto.getUsername(), doctorDto.getPassword(), "doctor");
        User insertedUser = userRepository.save(user);
        Doctor doctor = new Doctor(doctorDto.getName(), doctorDto.getSpecialization(), doctorDto.getDesignation(), doctorDto.getConsultationFee(), insertedUser);
        Doctor insertedDoctor = doctorRepository.save(doctor);
        return insertedDoctor;
    }

    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }

    public Doctor getDoctorByUsername(String doctorUsername){
        User doctorUser = userRepository.findByUsername(doctorUsername);
        Doctor doctor = doctorRepository.findByUser(doctorUser);
        return doctor;
    }

    public List<PatientDocumentDto> getPatientDocumentByDoctorUsername(String doctorUsername){
        Doctor doctor = getDoctorByUsername(doctorUsername);
        List<PatientDocument> patientDocuments = patientDocumentRepository.findByDoctor(doctor);
        List<PatientDocumentDto> documentDtos = new ArrayList<>();
        for(PatientDocument patientDocument: patientDocuments){
            String timestamp = patientDocument.getTimestamp().getDayOfMonth() + "-" + patientDocument.getTimestamp().getMonth()  + "-" + patientDocument.getTimestamp().getYear() + " " + patientDocument.getTimestamp().getHour() + ":" + patientDocument.getTimestamp().getMinute() + ":" + patientDocument.getTimestamp().getSecond();
            PatientDocumentDto patientDocumentDto = new PatientDocumentDto(patientDocument.getId(), patientDocument.getPatient(), patientDocument.getDocumentType(), timestamp);
            documentDtos.add(patientDocumentDto);
        }

        return documentDtos;
    }

    public List<Patient> getAllAssociatedPatients(String doctorUsername){
        Doctor doctor = getDoctorByUsername(doctorUsername);
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByDoctorAndStatus(doctor, Constants.APPOINTMENT_BOOKED);
        List<Patient> patients = new ArrayList<>();
        for(AppointmentDetails appointmentDetails1: appointmentDetails)
            patients.add(appointmentDetails1.getPatient());

        return patients;
    }

    public Prescription addPrescription(PrescriptionDto prescriptionDto){
        Doctor doctor = getDoctorByUsername(prescriptionDto.getDoctorUsername());
        Patient patient = patientService.getByPatientUsername(prescriptionDto.getPatientUsername());
        Prescription prescription = new Prescription(prescriptionDto.getPrescription(), doctor, patient, LocalDateTime.now());

        Prescription insertedPrescription = prescriptionRespository.save(prescription);
        return insertedPrescription;
    }
}
