package com.dpms.bean;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity(name = "PatientDocument")
@Table(name = "patient_document")
@Data
public class PatientDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column(name = "document")
    private byte[] document;

    @OneToOne
    @JoinColumn(name = "uploader_username", referencedColumnName = "username")
    private User user;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "PatientDocument{" +
                "id=" + id +
                ", patient=" + patient +
                ", document=" + Arrays.toString(document) +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }

    public PatientDocument() {
    }

    public PatientDocument(Patient patient, byte[] document, User user, LocalDateTime timestamp) {
        this.patient = patient;
        this.document = document;
        this.user = user;
        this.timestamp = timestamp;
    }
}
