package com.dpms.service;

import com.dpms.bean.Patient;
import com.dpms.bean.PatientDocument;
import com.dpms.bean.User;
import com.dpms.dto.PatientDocumentDto;
import com.dpms.repository.PatientDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class PatientDocumentService {
    @Autowired
    private PatientDocumentRepository patientDocumentRepository;

    @Autowired
    private PatientService patientService;

    public PatientDocument uploadPatientDocument(MultipartFile document, String documentType, String patientUsername) throws Exception{
        Patient patient = patientService.getByPatientUsername(patientUsername);
        PatientDocument patientDocument = new PatientDocument(patient, document.getBytes(), LocalDateTime.now(), documentType);
        PatientDocument insertedPatientDocument = patientDocumentRepository.save(patientDocument);
        return insertedPatientDocument;
    }

    public PatientDocument getMedicalRecordById(Long id){
        PatientDocument patientDocument = patientDocumentRepository.getOne(id);
        return patientDocument;
    }
}
