package com.dpms.controller;

import com.dpms.bean.Doctor;
import com.dpms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        Doctor insertedDoctor = doctorService.addDoctor(doctor);
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
}
