package com.dpms.repository;

import com.dpms.bean.AppointmentDetails;
import com.dpms.bean.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Long> {
    List<AppointmentDetails> findByDoctor(Doctor doctor);

    List<AppointmentDetails> findByStatus(String status);
}
