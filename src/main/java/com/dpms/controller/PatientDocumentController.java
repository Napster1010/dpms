package com.dpms.controller;

import com.dpms.bean.PatientDocument;
import com.dpms.dto.PatientDocumentDto;
import com.dpms.service.PatientDocumentService;
import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = "*")
public class PatientDocumentController {

    @Autowired
    private PatientDocumentService patientDocumentService;

    @PostMapping
    public ResponseEntity<PatientDocument> uploadMedicalRecord(@RequestParam("document") MultipartFile document, @RequestParam("documentType") String documentType, @RequestParam("patientUsername") String patientUsername){
        PatientDocument patientDocument = null;
        try{
            patientDocument = patientDocumentService.uploadPatientDocument(document, documentType, patientUsername);
        }catch (Exception e){}

        if(patientDocument==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(patientDocument, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<?> downloadMedicalRecordById(@RequestParam("id") Long id, HttpServletResponse httpServletResponse){
        try{
            PatientDocument patientDocument = patientDocumentService.getMedicalRecordById(id);
            byte[] document = patientDocument.getDocument();
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(document));
            String fileName = patientDocument.getPatient().getUser().getUsername() + "_" + patientDocument.getTimestamp().getYear() + "_" + patientDocument.getTimestamp().getMonth() + "_" + patientDocument.getTimestamp().getDayOfMonth();
            
            httpServletResponse.setContentType(new Tika().detect(document));
            httpServletResponse.setHeader("Content-disposition", "attachment; filename="+ fileName);
            IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
            httpServletResponse.flushBuffer();
            inputStream.close();

            return new ResponseEntity<>("File Downloaded Successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
