package com.dpms.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDetailsDto {
    private Long patientId;

    private Long doctorId;

    private Long branchId;

    private LocalDate dateOfAppointment;
}
