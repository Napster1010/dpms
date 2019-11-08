package com.dpms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Doctor")
@Table(name = "doctor")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "designation")
    private String designation;

    @Column(name = "consultation_fee")
    private long consultationFee;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", designation='" + designation + '\'' +
                ", consultationFee=" + consultationFee +
                '}';
    }

    public Doctor() {
    }

    public Doctor(String name, String specialization, String designation, int consultationFee) {
        this.name = name;
        this.specialization = specialization;
        this.designation = designation;
        this.consultationFee = consultationFee;
    }
}
