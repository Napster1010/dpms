package com.dpms.service;

import com.dpms.bean.AppointmentDetails;
import com.dpms.bean.Branch;
import com.dpms.bean.Doctor;
import com.dpms.bean.Patient;
import com.dpms.repository.AppointmentDetailsRepository;
import com.dpms.repository.BranchRepository;
import com.dpms.repository.DoctorRepository;
import com.dpms.repository.PatientRepository;
import com.dpms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    @Autowired
    private DoctorService doctorService;

    public AppointmentDetails bookAppointment(Long patientId, Long doctorId, Long branchId, LocalDate dateOfAppointment){
        Patient patient = patientRepository.getOne(patientId);
        Doctor doctor = doctorRepository.getOne(doctorId);
        Branch branch = branchRepository.getOne(branchId);

        AppointmentDetails appointmentDetails = new AppointmentDetails(patient, doctor, branch, dateOfAppointment, Constants.APPOINTMENT_SCHEDULED);
        AppointmentDetails insertedAppointmentDetails = appointmentDetailsRepository.save(appointmentDetails);

        return insertedAppointmentDetails;
    }

    public List<AppointmentDetails> getAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate dateOfAppointment){
        Doctor doctor = doctorService.getDocotrById(doctorId);
        if(doctor==null)
            return null;

        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByDoctorAndDateOfAppointment(doctor, dateOfAppointment);
        return appointmentDetails;
    }

}
