package com.example.staffregistration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.staffregistration.exception.ResourceNotFoundException;
import com.example.staffregistration.models.Staff;
import com.example.staffregistration.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StaffController {
    @Autowired
    private StaffRepository staffrepository;
    
    //getallstaff
    @GetMapping("/")
    public List<Staff> getAllStaff()
    {
        return staffrepository.findAll();
    }

    //post staff
    @PostMapping("/")
    public Staff addStaff(@RequestBody Staff staff)
    {
        return staffrepository.save(staff);
    }

    //update staff
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable long id, @RequestBody Staff staff)
    {
        Staff stf = staffrepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        stf.setFirstName(staff.getFirstName());
        stf.setLastName(staff.getLastName());
        stf.setEmailId(staff.getEmailId());
        Staff staf = staffrepository.save(stf);
        return ResponseEntity.ok(staf);
    }

    //get staff by id
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable long id)
    {
        Staff stf = staffrepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        return ResponseEntity.ok(stf);
    }

    //delete staff
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteStaff(@PathVariable long id)
    {
        Staff stf = staffrepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        staffrepository.delete(stf);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}