package com.dpms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Branch")
@Table(name = "branch")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", branchCode='" + branchCode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Branch() {
    }

    public Branch(String branchCode, String address) {
        this.branchCode = branchCode;
        this.address = address;
    }
}
