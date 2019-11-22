package com.dpms.bean;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Prescription")
@Table(name = "prescription")
@Data
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prescription")
    private String prescription;

    @OneToOne
    @JoinColumn(name = "doctor_username", referencedColumnName = "username")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_username", referencedColumnName = "username")
    private Patient patient;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", prescription='" + prescription + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", timestamp=" + timestamp +
                '}';
    }

    public Prescription() {
    }

    public Prescription(String prescription, Doctor doctor, Patient patient, LocalDateTime timestamp) {
        this.prescription = prescription;
        this.doctor = doctor;
        this.patient = patient;
        this.timestamp = timestamp;
    }
}
