package com.dpms.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "scheduled_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date scheduledTime;

    @Column(name = "status")
    private String status;

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", branch=" + branch +
                ", scheduledTime=" + scheduledTime +
                ", status='" + status + '\'' +
                '}';
    }

    public AppointmentDetails() {
    }

    public AppointmentDetails(Patient patient, Doctor doctor, Branch branch, Date scheduledTime, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.branch = branch;
        this.scheduledTime = scheduledTime;
        this.status = status;
    }
}
