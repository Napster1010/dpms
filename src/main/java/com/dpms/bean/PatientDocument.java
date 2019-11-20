package com.dpms.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity(name = "PatientDocument")
@Table(name = "patient_document")
@Data
public class PatientDocument implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_username", referencedColumnName = "username")
    private Patient patient;

    @Column(name = "document")
    private byte[] document;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "document_type")
    private String documentType;

    public PatientDocument() {
    }

    @Override
    public String toString() {
        return "PatientDocument{" +
                "id=" + id +
                ", patient=" + patient +
                ", document=" + Arrays.toString(document) +
                ", timestamp=" + timestamp +
                ", documentType='" + documentType + '\'' +
                '}';
    }

    public PatientDocument(Patient patient, byte[] document, LocalDateTime timestamp, String documentType) {
        this.patient = patient;
        this.document = document;
        this.timestamp = timestamp;
        this.documentType = documentType;
    }
}
