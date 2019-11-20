package com.dpms.repository;

import com.dpms.bean.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    public Branch findByCode(String code);
}
