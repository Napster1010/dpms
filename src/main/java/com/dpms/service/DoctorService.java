package com.dpms.service;

import com.dpms.bean.Doctor;
import com.dpms.bean.User;
import com.dpms.dto.DoctorDto;
import com.dpms.repository.DoctorRepository;
import com.dpms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public Doctor addDoctor(DoctorDto doctorDto){
        User user = new User(doctorDto.getUsername(), doctorDto.getPassword(), "doctor");
        User insertedUser = userRepository.save(user);
        Doctor doctor = new Doctor(doctorDto.getName(), doctorDto.getSpecialization(), doctorDto.getDesignation(), doctorDto.getConsultationFee(), insertedUser);
        Doctor insertedDoctor = doctorRepository.save(doctor);
        return insertedDoctor;
    }

    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }

    public Doctor getDoctorByUsername(String doctorUsername){
        User doctorUser = userRepository.findByUsername(doctorUsername);
        Doctor doctor = doctorRepository.findByUser(doctorUser);
        return doctor;
    }
}
