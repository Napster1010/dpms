package com.dpms.dto;

import lombok.Data;

@Data
public class PrescriptionDto {
    private String prescription;

    private String doctorUsername;

    private String patientUsername;
}
