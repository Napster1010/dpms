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
        AppointmentDetails appointmentDetails = appointmentService.bookAppointment(appointmentDetailsDto.getPatientUsername(), appointmentDetailsDto.getDoctorUsername(), appointmentDetailsDto.getBranchCode(), appointmentDetailsDto.getDateOfAppointment());
        if(appointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/requested")
    public ResponseEntity<List<AppointmentDetails>> getRequestedAppointments(){
        List<AppointmentDetails> appointmentDetails = appointmentService.getRequestedAppointments();
        if(appointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
    }

    @GetMapping(params = {"doctorUsername"})
    public ResponseEntity<List<AppointmentDetails>> getAppointmentsByDoctorUsername(@RequestParam("doctorUsername") String doctorUsername){
        List<AppointmentDetails> appointmentDetails = appointmentService.getAppointmentsByDoctorUsername(doctorUsername);
        if(appointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AppointmentDetails> updateAppointmentDetails(@RequestBody AppointmentDetails appointmentDetails){
        AppointmentDetails updatedAppointmentDetails = appointmentService.updateAppointmentDetails(appointmentDetails);
        if(updatedAppointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(updatedAppointmentDetails, HttpStatus.OK);
    }
}
