package com.dpms.repository;

import com.dpms.bean.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {
}
