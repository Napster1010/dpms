package com.dpms.service;

import com.dpms.bean.User;
import com.dpms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        User insertedUser = userRepository.save(user);
        return insertedUser;
    }

    public User authenticateUser(String username, String password, String userType){
        User user = userRepository.findByUsernameAndPasswordAndUserType(username, password, userType);
        return user;
    }
}