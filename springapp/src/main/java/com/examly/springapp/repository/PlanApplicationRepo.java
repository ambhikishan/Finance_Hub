package com.examly.springapp.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.examly.springapp.model.PlanApplication;
 
@Repository
public interface PlanApplicationRepo extends JpaRepository<PlanApplication,Long>{
 
    public List<PlanApplication> findByUserUserId(int userId);
   
}