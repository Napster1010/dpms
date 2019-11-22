package com.dpms.controller;

import com.dpms.bean.Doctor;
import com.dpms.bean.Patient;
import com.dpms.bean.Prescription;
import com.dpms.dto.PatientDto;
import com.dpms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDto patientDto){
        Patient insertedPatient = patientService.addPatient(patientDto);
        if(insertedPatient==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(insertedPatient, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "/doctor")
    public ResponseEntity<List<Doctor>> getAllAssociatedDoctors(@RequestParam("patientUsername") String patientUsername){
        List<Doctor> doctors = patientService.getAllAssociatedDoctors(patientUsername);
        if(doctors==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping(value = "/prescription")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatientUsername(@RequestParam("patientUsername") String patientUsername){
        List<Prescription> prescriptions = patientService.getPrescriptionByPatientUsername(patientUsername);
        if(prescriptions==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
}
