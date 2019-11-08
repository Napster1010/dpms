package com.dpms.service;

import com.dpms.bean.Branch;
import com.dpms.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    private BranchRepository branchRepository;

    public Branch addBranch(Branch branch){
        Branch insertedBranch = branchRepository.save(branch);
        return insertedBranch;
    }

    public List<Branch> getAllBranches(){
        List<Branch> branches = branchRepository.findAll();
        return branches;
    }
}
