package com.dpms.repository;

import com.dpms.bean.Patient;
import com.dpms.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Patient findByUser(User user);
}
