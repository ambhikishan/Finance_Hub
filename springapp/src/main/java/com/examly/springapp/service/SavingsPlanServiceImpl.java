package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.PlanAlreadyExistException;
import com.examly.springapp.model.SavingsPlan;
import com.examly.springapp.repository.SavingsPlanRepo;

import jakarta.persistence.EntityNotFoundException;

@Service


public class SavingsPlanServiceImpl implements SavingsPlanService {

    @Autowired
    private SavingsPlanRepo savingsPlanRepo;

    public List<SavingsPlan> getAllSavingsPlan() {
        return savingsPlanRepo.findAll();
       
    }

    public SavingsPlan addSavingsPlan(SavingsPlan savingsPlan) {
        SavingsPlan planExist=savingsPlanRepo.findByName(savingsPlan.getName()).orElse(null);
        if(planExist!=null)
        {
            throw new PlanAlreadyExistException(null);
        }
        return savingsPlanRepo.save(savingsPlan);
       
    }


    public void deleteSavingsPlanById(long id) {

        if(savingsPlanRepo.existsById(id))
        {
            savingsPlanRepo.deleteById(id);
        }
       
    }

    public SavingsPlan updateSavingsPlan(long id, SavingsPlan savingsPlan) {
        if(savingsPlanRepo.existsById(id))
        {
            savingsPlan.setSavingsPlanId(id);
            savingsPlanRepo.save(savingsPlan);
            return savingsPlan;
        }
        return null;
   
    }

    public SavingsPlan getSavingsPlanById(long id) {
        Optional<SavingsPlan> op=savingsPlanRepo.findById(id);
        if(op.isPresent())
        {
            return op.get();
        }
        else{
            throw new EntityNotFoundException();
        }
       
    }

   
}