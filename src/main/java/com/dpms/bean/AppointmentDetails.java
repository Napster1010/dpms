package com.dpms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "AppointmentDetails")
@Table(name = "appointment_details")
@Data
public class AppointmentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    @Column(name = "date_of_appointment")
    private LocalDate dateOfAppointment;

    @Column(name = "status")
    private String status;

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", branch=" + branch +
                ", dateOfAppointment=" + dateOfAppointment +
                ", status='" + status + '\'' +
                '}';
    }

    public AppointmentDetails() {
    }

    public AppointmentDetails(Patient patient, Doctor doctor, Branch branch, LocalDate dateOfAppointment, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.branch = branch;
        this.dateOfAppointment = dateOfAppointment;
        this.status = status;
    }
}
