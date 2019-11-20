package com.dpms.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Branch")
@Table(name = "branch")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Branch(String code, String name, String address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public Branch() {
    }

}
