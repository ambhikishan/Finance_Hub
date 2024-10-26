package com.examly.springapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enquiryId;

    private String message;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    public Enquiry() {
    }
    public Enquiry(Long enquiryId, String message, User user) {
        this.enquiryId = enquiryId;
        this.message = message;
        this.user = user;
    }
    public Long getEnquiryId() {
        return enquiryId;
    }
    public void setEnquiryId(Long enquiryId) {
        this.enquiryId = enquiryId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
