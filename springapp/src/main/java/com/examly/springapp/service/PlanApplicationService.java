package com.examly.springapp.service;
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.web.multipart.MultipartFile;
 
import com.examly.springapp.model.PlanApplication;
import com.examly.springapp.model.SavingsPlan;
import com.examly.springapp.model.User;
 
public interface PlanApplicationService {
 
 
    public PlanApplication addPlanApplication(PlanApplication planApplication);
 
    public PlanApplication updatePlanApplication(long planId, PlanApplication updatePlanApplication);
 
    public boolean deletePlanApplication(long planId);
 
    public List<PlanApplication> getAllPlanApplications();
 
    public PlanApplication getPlanApplicationsById(long planId);
 
    public PlanApplication savePlanApplicationToPlan(double appliedAmount, String localDate, String remarks,
    MultipartFile proofOfDocument,long planId,int userId);  

    public List<PlanApplication> getPlanApplicationByuserId(int userId); 
    
    // public void savePlanApplication(double appliedAmount, String status, LocalDate localDate, String remarks,
    //         MultipartFile proofOfDocument, User user, SavingsPlan savingsPlan);  
     
 
}


// package com.examly.springapp.service;
 
// import java.time.LocalDate;
// import java.util.List;
 
// import org.springframework.web.multipart.MultipartFile;
 
// import com.examly.springapp.model.PlanApplication;
// import com.examly.springapp.model.SavingsPlan;
// import com.examly.springapp.model.User;
 
// public interface PlanApplicationService {
 
 
//     public PlanApplication addPlanApplication(PlanApplication planApplication);
 
//     public PlanApplication updatePlanApplication(long planId, PlanApplication updatePlanApplication);
 
//     public boolean deletePlanApplication(long planId);
 
//     public List<PlanApplication> getAllPlanApplications();
 
//     public PlanApplication getPlanApplicationsById(long planId);
 
//     public PlanApplication savePlanApplicationToPlan(double appliedAmount, String localDate, String remarks,
//             MultipartFile proofOfDocument,long planId,int userId);  
//     // public void savePlanApplication(double appliedAmount, String status, LocalDate localDate, String remarks,
//     //         MultipartFile proofOfDocument, User user, SavingsPlan savingsPlan);  
     
//     // public List<PlanApplication> getPlanApplicationsByUserId(long userId);    
 
// }
 

// // package com.examly.springapp.service;
 
// // import java.time.LocalDate;
// // import java.util.List;
 
// // import org.springframework.web.multipart.MultipartFile;
 
// // import com.examly.springapp.model.PlanApplication;
// // import com.examly.springapp.model.SavingsPlan;
// // import com.examly.springapp.model.User;
 
// // public interface PlanApplicationService {
 
 
// //     public PlanApplication addPlanApplication(PlanApplication planApplication);
 
// //     public PlanApplication updatePlanApplication(long planId, PlanApplication updatePlanApplication);
 
// //     public PlanApplication deletePlanApplication(long planId);
 
// //     public List<PlanApplication> getAllPlanApplications();
 
// //     public PlanApplication getPlanApplicationsById(long planId);
 
// //     public PlanApplication savePlanApplication(double appliedAmount, String localDate, String remarks,
// //             MultipartFile proofOfDocument);  
// //     // public void savePlanApplication(double appliedAmount, String status, LocalDate localDate, String remarks,
// //     //         MultipartFile proofOfDocument, User user, SavingsPlan savingsPlan);  
     
// //     // public List<PlanApplication> getPlanApplicationsByUserId(long userId);    
 
// // }
 