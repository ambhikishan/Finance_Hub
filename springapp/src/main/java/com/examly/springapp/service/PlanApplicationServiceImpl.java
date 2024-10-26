package com.examly.springapp.service;
 
import java.io.IOException;
// import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import com.examly.springapp.model.PlanApplication;
import com.examly.springapp.model.SavingsPlan;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.PlanApplicationRepo;
import com.examly.springapp.repository.SavingsPlanRepo;
import com.examly.springapp.repository.UserRepo;
 
/**
 * PlanApplicationServiceImpl
 */
@Service
public class PlanApplicationServiceImpl implements PlanApplicationService {
 
    @Autowired
    PlanApplicationRepo planApplicationRepo;
 
    @Autowired
    SavingsPlanRepo savingsPlanRepo;
 
    @Autowired
    UserRepo userRepo;
 
    @Override
    public PlanApplication addPlanApplication(PlanApplication planApplication) {
        // set status to pending
        planApplication.setStatus("pending");
        // date??
        return planApplicationRepo.save(planApplication);
    }
 
    public PlanApplication savePlanApplicationToPlan(double appliedAmount, String applicationDate, String remarks,
        MultipartFile proofOfDocument, long savingsPlanId, int userId) { // , User user, SavingsPlan savingsPlan
        PlanApplication planApplication = new PlanApplication();
        planApplication.setAppliedAmount(appliedAmount);
        planApplication.setStatus("pending");
        planApplication.setApplicationDate(applicationDate);
        planApplication.setRemarks(remarks);
        SavingsPlan savingsPlan = savingsPlanRepo.findById(savingsPlanId).get();
        planApplication.setSavingsPlan(savingsPlan);
        
 
        User user = userRepo.findById(userId).get();
        planApplication.setUser(user);
       
       
        try {
            planApplication.setProofOfDocument(proofOfDocument.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planApplicationRepo.save(planApplication);
    }
 
    @Override
    public PlanApplication updatePlanApplication(long planId, PlanApplication updatePlanApplication) {
        PlanApplication planApplication = planApplicationRepo.findById(planId).get();
        if (planApplication != null) {
            planApplication.setAppliedAmount(updatePlanApplication.getAppliedAmount());
            planApplication.setStatus(updatePlanApplication.getStatus());
            planApplication.setApplicationDate(updatePlanApplication.getApplicationDate());
            planApplication.setRemarks(updatePlanApplication.getRemarks());
            // one more attribute to be update
            return planApplicationRepo.save(planApplication);
        } else {
            return null;
        }
 
    }
 
    @Override
    public boolean deletePlanApplication(long planId) {
        PlanApplication planApplication = planApplicationRepo.findById(planId).get();
        if (planApplication != null) {
            planApplicationRepo.deleteById(planId);
            return true;
        } else {
            return false;
        }
    }
 
    @Override
    public List<PlanApplication> getAllPlanApplications() {
        return planApplicationRepo.findAll();
    }

    @Override
    public  List<PlanApplication> getPlanApplicationByuserId(int userId){

        return planApplicationRepo.findByUserUserId(userId);
    }
 
    @Override
    public PlanApplication getPlanApplicationsById(long planId) {
        PlanApplication planApplication = planApplicationRepo.findById(planId).get();
        if (planApplication != null) {
            return planApplication;
        } else {
            return null;
        }
    }
}
 