package com.dpms.controller;

import com.dpms.bean.AppointmentDetails;
import com.dpms.dto.AppointmentDetailsDto;
import com.dpms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentDetailsController {
    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/appointment")
    public ResponseEntity<AppointmentDetails> bookAppointment(@RequestBody AppointmentDetailsDto appointmentDetailsDto){
        AppointmentDetails appointmentDetails = patientService.bookAppointment(appointmentDetailsDto.getPatientId(), appointmentDetailsDto.getDoctorId(), appointmentDetailsDto.getBranchId(), appointmentDetailsDto.getDateOfAppointment());
        if(appointmentDetails==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
    }
}
