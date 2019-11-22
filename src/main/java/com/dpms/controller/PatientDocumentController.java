package com.dpms.controller;

import com.dpms.bean.PatientDocument;
import com.dpms.service.PatientDocumentService;
import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = "*")
public class PatientDocumentController {

    @Autowired
    private PatientDocumentService patientDocumentService;

    @PostMapping
    public ResponseEntity<PatientDocument> uploadMedicalRecord(@RequestParam("document") MultipartFile document, @RequestParam("documentType") String documentType, @RequestParam("patientUsername") String patientUsername, @RequestParam("doctorUsername") String doctorUsername){
        PatientDocument patientDocument = null;
        try{
            patientDocument = patientDocumentService.uploadPatientDocument(document, documentType, patientUsername, doctorUsername);
        }catch (Exception e){}

        if(patientDocument==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(patientDocument, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<?> downloadMedicalRecordById(@RequestParam("id") String id, HttpServletResponse httpServletResponse){
        try{
            PatientDocument patientDocument = patientDocumentService.getMedicalRecordById(Long.parseLong(id));
            byte[] document = patientDocument.getDocument();
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(document));
            String fileName = patientDocument.getPatient().getUser().getUsername() + "_" + patientDocument.getTimestamp().getYear() + "_" + patientDocument.getTimestamp().getMonth() + "_" + patientDocument.getTimestamp().getDayOfMonth();

            String mediaType = new Tika().detect(document);
            if(mediaType.equals("application/pdf"))
                fileName = fileName.concat(".pdf");
            else if(mediaType.equals("image/bmp"))
                fileName = fileName.concat(".bmp");
            else if(mediaType.equals("image/jpeg"))
                fileName = fileName.concat(".jpeg");

            httpServletResponse.setContentType(mediaType);
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
