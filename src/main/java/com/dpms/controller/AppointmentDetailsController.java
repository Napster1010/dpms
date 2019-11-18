package com.dpms.controller;

import com.dpms.bean.AppointmentDetails;
import com.dpms.dto.AppointmentDetailsDto;
import com.dpms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/appointment")
@CrossOrigin(origins = "*")
public class AppointmentDetailsController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDetails> bookAppointment(@RequestBody AppointmentDetailsDto appointmentDetailsDto){
        AppointmentDetails appointmentDetails = appointmentService.bookAppointment(appointmentDetailsDto.getPatientId(), appointmentDetailsDto.getDoctorId(), appointmentDetailsDto.getBranchId(), appointmentDetailsDto.getDateOfAppointment());
        if(appointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDetails>> getAppointmentsByDoctorIdAndDate(@RequestParam("doctorId") Long doctorId, @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate){
        List<AppointmentDetails> appointmentDetails = appointmentService.getAppointmentsByDoctorIdAndDate(doctorId, appointmentDate);
        if(appointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
    }

}
