package com.dpms.repository;

import com.dpms.bean.Patient;
import com.dpms.bean.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRespository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatient(Patient patient);
}
