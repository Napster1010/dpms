package com.dpms.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PatientDocumentDto {
    private String patientUsername;

    private String uploaderUsername;
}
