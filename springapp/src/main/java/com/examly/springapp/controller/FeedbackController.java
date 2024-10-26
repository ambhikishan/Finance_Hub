package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;

@RestController




@CrossOrigin(origins="https://8081-adebeedbfcacffaddfcbfefde.premiumproject.examly.io/")

public class FeedbackController {

    @Autowired private FeedbackServiceImpl feedbackService;

    @PostMapping("/api/feedback")
    public ResponseEntity<?> addFeeback(@RequestBody Feedback feedback){

        Feedback savedFeedback=feedbackService.addfeedback(feedback);
        return ResponseEntity.status(201).body(savedFeedback);
    
    }

    @GetMapping("/api/feedback/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable Long id){
        Feedback foundedFeedback=feedbackService.getFeedbackById(id);
        if(foundedFeedback!=null){
            return ResponseEntity.status(200).body(foundedFeedback);
        }else{
            
            return ResponseEntity.status(500).body("");
        }
    }

    @GetMapping("/api/feedback")
    public ResponseEntity<?> getAllFeedbacks(){
        List<Feedback> feedbacks=feedbackService.getAllFeedbacks();
        return ResponseEntity.status(200).body(feedbacks);
    }

    @GetMapping("/api/feedback/user/{userId}")
    public ResponseEntity<?> getFeedbackByUserId(@PathVariable int userId){
        List<Feedback> feedbacks=feedbackService.getFeedbackByUserId(userId);
        return ResponseEntity.status(200).body(feedbacks);
    }

    @DeleteMapping("/api/feedback/{id}")
    public ResponseEntity<?> deleteFeedbackById(@PathVariable long id){
        Feedback deletedFeedback=feedbackService.deleteFeedbackById(id);
        return ResponseEntity.status(200).body(deletedFeedback);
    }


   

}