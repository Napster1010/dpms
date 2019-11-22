package com.dpms.dto;

import com.dpms.bean.Patient;
import lombok.Data;

@Data
public class PatientDocumentDto {

    Long id;

    Patient patient;

    String documentType;

    String timestamp;

    @Override
    public String toString() {
        return "PatientDocumentDto{" +
                "id=" + id +
                ", patient=" + patient +
                ", documentType='" + documentType + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public PatientDocumentDto() {
    }

    public PatientDocumentDto(Long id, Patient patient, String documentType, String timestamp) {
        this.id = id;
        this.patient = patient;
        this.documentType = documentType;
        this.timestamp = timestamp;
    }
}
