package com.dpms.controller;

import com.dpms.bean.Doctor;
import com.dpms.bean.Patient;
import com.dpms.bean.Prescription;
import com.dpms.dto.DoctorDto;
import com.dpms.dto.PatientDocumentDto;
import com.dpms.dto.PrescriptionDto;
import com.dpms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorDto doctorDto){
        Doctor insertedDoctor = doctorService.addDoctor(doctorDto);
        if(insertedDoctor==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(insertedDoctor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllPatients(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping(value = "/document/patient")
    public ResponseEntity<List<PatientDocumentDto>> getPatientDocumentByDoctorUsername(@RequestParam("doctorUsername") String doctorUsername){
        List<PatientDocumentDto> patientDocumentDtos = doctorService.getPatientDocumentByDoctorUsername(doctorUsername);
        if(patientDocumentDtos==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(patientDocumentDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/patient")
    public ResponseEntity<List<Patient>> getPatientsByDoctorUsername(@RequestParam("doctorUsername") String doctorUsername){
        List<Patient> patients = doctorService.getAllAssociatedPatients(doctorUsername);
        if(patients==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping(value = "/prescription")
    public ResponseEntity<Prescription> addPrescription(@RequestBody PrescriptionDto prescriptionDto){
        Prescription prescription = doctorService.addPrescription(prescriptionDto);
        if(prescription==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(prescription, HttpStatus.OK);
    }
}
