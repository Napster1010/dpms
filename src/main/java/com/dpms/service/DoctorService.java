package com.dpms.service;

import com.dpms.bean.Doctor;
import com.dpms.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor){
        Doctor insertedDoctor = doctorRepository.save(doctor);
        return insertedDoctor;
    }
}
