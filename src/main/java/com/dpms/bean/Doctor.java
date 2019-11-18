package com.dpms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;

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
    private int consultationFee;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", designation='" + designation + '\'' +
                ", consultationFee=" + consultationFee +
                ", user=" + user +
                '}';
    }

    public Doctor(String name, String specialization, String designation, int consultationFee, User user) {
        this.name = name;
        this.specialization = specialization;
        this.designation = designation;
        this.consultationFee = consultationFee;
        this.user = user;
    }
}
