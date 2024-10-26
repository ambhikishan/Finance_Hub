
package com.examly.springapp.model;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
 
@Entity
public class PlanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long planApplicationId;
    private double appliedAmount;
    private String status;
    private String applicationDate;
    private String remarks;
 
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] proofOfDocument;
 
    @ManyToOne
    private SavingsPlan savingsPlan;

    @ManyToOne
    private User user;
 
    public PlanApplication() {
    }
 
    public PlanApplication(long planApplicationId, double appliedAmount, String status, String applicationDate,
            String remarks, byte[] proofOfDocument, SavingsPlan savingsPlan, User user) {
        this.planApplicationId = planApplicationId;
        this.appliedAmount = appliedAmount;
        this.status = status;
        this.applicationDate = applicationDate;
        this.remarks = remarks;
        this.proofOfDocument = proofOfDocument;
        this.savingsPlan = savingsPlan;
        this.user = user;
    }
 
    public long getPlanApplicationId() {
        return planApplicationId;
    }
 
    public void setPlanApplicationId(long planApplicationId) {
        this.planApplicationId = planApplicationId;
    }
 
    public double getAppliedAmount() {
        return appliedAmount;
    }
 
    public void setAppliedAmount(double appliedAmount) {
        this.appliedAmount = appliedAmount;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public String getApplicationDate() {
        return applicationDate;
    }
 
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
 
    public String getRemarks() {
        return remarks;
    }
 
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
 
    public byte[] getProofOfDocument() {
        return proofOfDocument;
    }
 
    public void setProofOfDocument(byte[] proofOfDocument) {
        this.proofOfDocument = proofOfDocument;
    }
 
   
    public SavingsPlan getSavingsPlan() {
        return savingsPlan;
    }
 
    public void setSavingsPlan(SavingsPlan savingsPlan) {
        this.savingsPlan = savingsPlan;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
}

// package com.examly.springapp.model;
 
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
// import jakarta.persistence.ManyToOne;
 
// @Entity
// public class PlanApplication {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private long planApplicationId;
//     private double appliedAmount;
//     private String status;
//     private String applicationDate;
//     private String remarks;
 
//     @Lob
//     private byte[] proofOfDocument;
 
//     @ManyToOne
//     private User user;
 
//     // @ManyToOne
//     // private SavingsPlan savingsPlan;
 
 
//     public PlanApplication() {
//     }
 
 
//     public PlanApplication(long planApplicationId, double appliedAmount, String status, String applicationDate,
//             String remarks, byte[] proofOfDocument) {
//         this.planApplicationId = planApplicationId;
//         this.appliedAmount = appliedAmount;
//         this.status = status;
//         this.applicationDate = applicationDate;
//         this.remarks = remarks;
//         this.proofOfDocument = proofOfDocument;
//         // this.user = user;
//         // this.savingsPlan = savingsPlan;
//     }
 
 
//     public long getPlanApplicationId() {
//         return planApplicationId;
//     }
 
 
//     public void setPlanApplicationId(long planApplicationId) {
//         this.planApplicationId = planApplicationId;
//     }
 
 
//     public double getAppliedAmount() {
//         return appliedAmount;
//     }
 
 
//     public void setAppliedAmount(double appliedAmount) {
//         this.appliedAmount = appliedAmount;
//     }
 
 
//     public String getStatus() {
//         return status;
//     }
 
 
//     public void setStatus(String status) {
//         this.status = status;
//     }
 
 
//     public String getApplicationDate() {
//         return applicationDate;
//     }
 
 
//     public void setApplicationDate(String applicationDate) {
//         this.applicationDate = applicationDate;
//     }
 
 
//     public String getRemarks() {
//         return remarks;
//     }
 
 
//     public void setRemarks(String remarks) {
//         this.remarks = remarks;
//     }
 
 
//     public byte[] getProofOfDocument() {
//         return proofOfDocument;
//     }
 
 
//     public void setProofOfDocument(byte[] proofOfDocument) {
//         this.proofOfDocument = proofOfDocument;
//     }
 
 
   
   
 
// }




// // package com.examly.springapp.model;

// // import java.time.LocalDate;
// // import jakarta.persistence.Entity;
// // import jakarta.persistence.GeneratedValue;
// // import jakarta.persistence.GenerationType;
// // import jakarta.persistence.Id;
// // import jakarta.persistence.ManyToOne;


// // @Entity
// // public class PlanApplication {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     Long planApplicationId;
// //     double appliedAmount;
// //     String status;
// //     LocalDate applicationDate;
// //     String remarks;
// //     String proofOfDocument;
// //     @ManyToOne
// //     User user;
// //     @ManyToOne
// //     SavingsPlan savingsPlan;

// //     public PlanApplication(Long planApplicationId, double appliedAmount, String status, LocalDate applicationDate,
// //         String remarks, User user, SavingsPlan savingsPlan) {
// //         this.planApplicationId = planApplicationId;
// //         this.appliedAmount = appliedAmount;
// //         this.status = status;
// //         this.applicationDate = applicationDate;
// //         this.remarks = remarks;
// //         this.user = user;
// //         this.savingsPlan = savingsPlan;
        
// //     }
// //     public PlanApplication() {
// //     }
// //     public Long getPlanApplicationId() {
// //         return planApplicationId;
// //     }
// //     public void setPlanApplicationId(Long planApplicationId) {
// //         this.planApplicationId = planApplicationId;
// //     }
// //     public double getAppliedAmount() {
// //         return appliedAmount;
// //     }
// //     public void setAppliedAmount(double appliedAmount) {
// //         this.appliedAmount = appliedAmount;
// //     }
// //     public String getStatus() {
// //         return status;
// //     }
// //     public void setStatus(String status) {
// //         this.status = status;
// //     }
// //     public LocalDate getApplicationDate() {
// //         return applicationDate;
// //     }
// //     public void setApplicationDate(LocalDate applicationDate) {
// //         this.applicationDate = applicationDate;
// //     }
// //     public String getRemarks() {
// //         return remarks;
// //     }
// //     public void setRemarks(String remarks) {
// //         this.remarks = remarks;
// //     }
// //     public User getUser() {
// //         return user;
// //     }
// //     public void setUser(User user) {
// //         this.user = user;
// //     }
// //     public SavingsPlan getSavingsPlan() {
// //         return savingsPlan;
// //     }
// //     public void setSavingsPlan(SavingsPlan savingsPlan) {
// //         this.savingsPlan = savingsPlan;
// //     }
// //     public String getProofOfDocument() {
// //         return proofOfDocument;
// //     }
// //     public void setProofOfDocument(String proofOfDocument) {
// //         this.proofOfDocument = proofOfDocument;
// //     }



// // }
