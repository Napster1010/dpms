package com.dpms.controller;

import com.dpms.bean.AppointmentDetails;
import com.dpms.bean.Patient;
import com.dpms.dto.AppointmentDetailsDto;
import com.dpms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient insertedPatient = patientService.addPatient(patient);
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

}
