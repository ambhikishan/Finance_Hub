package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Enquiry;

public interface EnquiryService {

    public Enquiry addEnquiries(Enquiry enquiry);
    public Enquiry getEnquiryId(Long id);
    public List<Enquiry> getAllEnquiries();
    public void deleteEnquiry(Long id);
    public List<Enquiry> getEnquiriesByUserId(int userId);
    public Enquiry updateEnquiry(Long id,Enquiry enquiry);
} 