package com.dpms.service;

import com.dpms.bean.*;
import com.dpms.repository.*;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientService patientService;

    public AppointmentDetails bookAppointment(String patientUsername, String doctorUsername, String branchCode, LocalDate dateOfAppointment){
        User patientUser = userRepository.findByUsername(patientUsername);
        Patient patient = patientRepository.findByUser(patientUser);
        User doctorUser = userRepository.findByUsername(doctorUsername);
        Doctor doctor = doctorRepository.findByUser(doctorUser);
        Branch branch = branchRepository.findByCode(branchCode);

        AppointmentDetails appointmentDetails = new AppointmentDetails(patient, doctor, branch, dateOfAppointment, Constants.APPOINTMENT_REQUESTED);
        AppointmentDetails insertedAppointmentDetails = appointmentDetailsRepository.save(appointmentDetails);

        return insertedAppointmentDetails;
    }

    public List<AppointmentDetails> getAppointmentsByDoctorUsername(String doctorUsername){
        Doctor doctor = doctorService.getDoctorByUsername(doctorUsername);
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByDoctorAndStatus(doctor, Constants.APPOINTMENT_BOOKED);
        return appointmentDetails;
    }

    public List<AppointmentDetails> getAppointmentsByPatientUsername(String patientUsername){
        Patient patient = patientService.getByPatientUsername(patientUsername);
        List<AppointmentDetails> appointmentDetails = appointmentDetailsRepository.findByPatient(patient);
        return appointmentDetails;
    }

    public List<AppointmentDetails> getRequestedAppointments(){
        List<AppointmentDetails> requestedAppointments = appointmentDetailsRepository.findByStatus(Constants.APPOINTMENT_REQUESTED);
        return requestedAppointments;
    }

    public AppointmentDetails updateAppointmentDetails(AppointmentDetails appointmentDetails){
        AppointmentDetails updatedAppointmentDetails = appointmentDetailsRepository.save(appointmentDetails);
        return updatedAppointmentDetails;
    }

}
