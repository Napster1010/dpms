package com.dpms.repository;

import com.dpms.bean.Doctor;
import com.dpms.bean.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {
    List<PatientDocument> findByDoctor(Doctor doctor);
}
