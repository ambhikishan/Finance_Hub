package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.Feedback;

public interface FeedbackService {
    public Feedback addfeedback(Feedback feedback);
    public Feedback getFeedbackById(Long id);
    public List<Feedback> getAllFeedbacks();
    public Feedback deleteFeedbackById(Long id);
    


    
}