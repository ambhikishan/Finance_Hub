package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.SavingsPlan;

public interface SavingsPlanService
{
  public List<SavingsPlan> getAllSavingsPlan();
  public SavingsPlan getSavingsPlanById(long id);
  public SavingsPlan addSavingsPlan(SavingsPlan savingsPlan);
  public void deleteSavingsPlanById(long id);
  public SavingsPlan updateSavingsPlan(long id,SavingsPlan savingsPlan);

}