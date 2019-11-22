package com.dpms.repository;

import com.dpms.bean.AppointmentDetails;
import com.dpms.bean.Doctor;
import com.dpms.bean.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Long> {
    List<AppointmentDetails> findByDoctorAndStatus(Doctor doctor, String status);

    List<AppointmentDetails> findByPatient(Patient patient);

    List<AppointmentDetails> findByStatus(String status);

    List<AppointmentDetails> findByPatientAndStatus(Patient patient, String status);
}
