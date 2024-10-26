package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry,Long> {

    public List<Enquiry> findEnquiryByUserUserId(int userId);

}
