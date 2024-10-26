package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Enquiry;
import com.examly.springapp.service.EnquiryService;

@RestController

@CrossOrigin(origins="https://8081-adebeedbfcacffaddfcbfefde.premiumproject.examly.io/")

public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @PostMapping("/api/enquiries") 
    public ResponseEntity<?> addEnquiry(@RequestBody Enquiry enquiry) {
        Enquiry enquiries = enquiryService.addEnquiries(enquiry);
        return ResponseEntity.status(201).body(enquiries.getUser());
    }

    @GetMapping("/api/enquiries/{id}")
    public ResponseEntity<?> getEnquiryById(@PathVariable Long id) {
        Enquiry enquiries = enquiryService.getEnquiryId(id);
        return ResponseEntity.status(200).body(enquiries);
    }

    @GetMapping("/api/enquiries") 
    public ResponseEntity<?> getAllEnquiries() {
        List<Enquiry> list = enquiryService.getAllEnquiries();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/api/enquiries/user/{userId}") 
    public ResponseEntity<?> getEnquiriesByUserId(@PathVariable int userId) {
        List<Enquiry> enquiries = enquiryService.getEnquiriesByUserId(userId);
        return ResponseEntity.status(201).body(enquiries);
    }

    @DeleteMapping("/api/enquiries/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        return ResponseEntity.status(200).body(true);
    }

    @PutMapping("/api/enquiries/{id}")
    public ResponseEntity<?> updateEnquiry(@PathVariable Long id,@RequestBody Enquiry enquiry) {
        Enquiry enquiry2 = enquiryService.updateEnquiry(id, enquiry);
        return ResponseEntity.status(200).body(enquiry2);
    }
}
