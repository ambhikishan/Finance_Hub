package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    
    @Autowired
    private FeedbackRepo feedbackRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public Feedback addfeedback(Feedback feedback){
        return feedbackRepo.save(feedback);
    }
    @Override
    public Feedback getFeedbackById(Long id){
        return feedbackRepo.findById(id).get();
    }

    @Override
    public List<Feedback> getAllFeedbacks(){
        return feedbackRepo.findAll();
    }

    @Override
    public Feedback deleteFeedbackById(Long id){
        if(feedbackRepo.existsById(id)){
            Feedback existingFeedback=feedbackRepo.findById(id).get();
            feedbackRepo.deleteById(id);
            return existingFeedback;
        }else{
            throw new EntityNotFoundException();
        }
    }

    
    public List<Feedback> getFeedbackByUserId(int userId){
        return feedbackRepo.findFeedbackByUserUserId(userId);

    }
    
   

}