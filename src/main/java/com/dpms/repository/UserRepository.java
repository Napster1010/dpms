package com.dpms.repository;

import com.dpms.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsernameAndPasswordAndUserType(String username, String password, String userType);
}
