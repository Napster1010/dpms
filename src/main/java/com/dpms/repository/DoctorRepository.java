package com.dpms.repository;

import com.dpms.bean.Doctor;
import com.dpms.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Doctor findByUser(User user);
}
