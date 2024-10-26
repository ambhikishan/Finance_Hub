package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Enquiry;
import com.examly.springapp.repository.EnquiryRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnquiryServiceImpl implements EnquiryService{

    @Autowired
    private EnquiryRepo enquiryRepo;
    
    @Override
    public Enquiry addEnquiries(Enquiry enquiry) {
        Enquiry enquiries = enquiryRepo.save(enquiry);
        return enquiries;
    }

    @Override
    public Enquiry getEnquiryId(Long id) {
        Optional<Enquiry> opt = enquiryRepo.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepo.findAll();
    }

    @Override
    public List<Enquiry> getEnquiriesByUserId(int userId) {
        return enquiryRepo.findEnquiryByUserUserId(userId);
    }

    @Override
    public void deleteEnquiry(Long id) {
        if(enquiryRepo.existsById(id)){
            enquiryRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Enquiry updateEnquiry(Long id, Enquiry enquiry) {
        if(enquiryRepo.existsById(id)) {
            enquiry.setEnquiryId(id);
            return enquiryRepo.save(enquiry);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
