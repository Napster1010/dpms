package com.dpms.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDetailsDto {
    private String patientUsername;

    private String doctorUsername;

    private String branchCode;

    private LocalDate dateOfAppointment;
}
