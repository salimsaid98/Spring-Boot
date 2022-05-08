package com.example.staffregistration.repository;

import com.example.staffregistration.models.Staff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    
}
