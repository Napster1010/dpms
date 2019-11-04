package com.dpms.bean;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Patient")
@Table(name = "patient")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Patient() {
    }

    public Patient(String name, String contactNo, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
    }
}
