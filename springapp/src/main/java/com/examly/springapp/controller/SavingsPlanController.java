package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.SavingsPlan;
import com.examly.springapp.service.SavingsPlanService;

@RestController
@CrossOrigin(origins = "https://8081-adebeedbfcacffaddfcbfefde.premiumproject.examly.io/")
public class SavingsPlanController {
    @Autowired
    private SavingsPlanService savingsPlanService;

    @GetMapping("/api/savingsplans")
    public ResponseEntity<?> getAllSavingsPlan()
    {
        return ResponseEntity.status(200).body(savingsPlanService.getAllSavingsPlan());

    }

    @GetMapping("/api/savingsplans/{id}")
    public ResponseEntity<?> getSavingsPlanById(@PathVariable long id)
    {
        SavingsPlan savingsPlan=savingsPlanService.getSavingsPlanById(id);
        return ResponseEntity.status(200).body(savingsPlan);

    }

    @PostMapping("/api/savingsplans")
    public ResponseEntity<?> addSavingsPlan(@RequestBody SavingsPlan savingsPlan)
    {
        SavingsPlan s=savingsPlanService.addSavingsPlan(savingsPlan);
        return ResponseEntity.status(201).body(s);

    }


    @DeleteMapping("/api/savingsplans/{id}")
    public ResponseEntity<?> deleteSavingsPlanById(@PathVariable long id)
    {
        savingsPlanService.deleteSavingsPlanById(id);
        return ResponseEntity.status(200).body(true);
    }

    @PutMapping("/api/savingsplans/{id}")
    public ResponseEntity<?> updateSavingsPlan(@PathVariable long id,@RequestBody SavingsPlan savingsPlan)
    {
        SavingsPlan s=savingsPlanService.updateSavingsPlan(id, savingsPlan);
        return ResponseEntity.status(200).body(s);
        
    }  
    
   
}