package com.dpms.controller;

import com.dpms.bean.User;
import com.dpms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User insertedUser = userService.addUser(user);
        if(insertedUser==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(insertedUser, HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<User> authenticateUser(@RequestParam String username, @RequestParam String password, @RequestParam String userType){
        User user = userService.authenticateUser(username, password, userType);
        if(user==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
