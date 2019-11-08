package com.dpms.controller;

import com.dpms.bean.Branch;
import com.dpms.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/branch")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch){
        Branch insertedBranch = hospitalService.addBranch(branch);
        if(insertedBranch==null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(insertedBranch, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches(){
        List<Branch> branches = hospitalService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }
}
