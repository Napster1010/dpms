package com.dpms.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private String name;

    private String specialization;

    private String designation;

    private int consultationFee;

    private String username;

    private String password;
}
