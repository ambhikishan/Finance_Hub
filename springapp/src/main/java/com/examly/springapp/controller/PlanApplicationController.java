package com.examly.springapp.controller;
 
// import java.time.LocalDate;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import com.examly.springapp.model.PlanApplication;
import com.examly.springapp.model.SavingsPlan;
import com.examly.springapp.model.User;
import com.examly.springapp.service.PlanApplicationService;
import com.examly.springapp.service.PlanApplicationServiceImpl;
 
@RestController
public class PlanApplicationController {
 
    @Autowired
    PlanApplicationServiceImpl planApplicationService;
 
    @PostMapping("/api/planapplicationss")
    public ResponseEntity<?> addPlanApplication(@RequestBody PlanApplication planApplication) {
        try {
            planApplicationService.addPlanApplication(planApplication);
            return new ResponseEntity<>(planApplicationService.addPlanApplication(planApplication),
                    HttpStatusCode.valueOf(201));
        } catch (Exception e) {
            return new ResponseEntity<>("Not added", HttpStatusCode.valueOf(500));
        }
    }
 
    @PostMapping("/api/planapplications/{planId}/{userId}")
    public ResponseEntity<?> savePlanApplication(@RequestParam("appliedAmount") double appliedAmount,
            // @RequestParam("status") String status,
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("remarks") String remarks,
            @RequestParam("proofOfDocument") MultipartFile proofOfDocument,
            @PathVariable long planId,
            @PathVariable int userId)
 
    {
 
        // Fetch User and SavingsPlan entities based on IDs
        // User user = userService.findById(userId);
        // SavingsPlan savingsPlan = savingsPlanServiceImpl.findById(savingsPlanId);
 
        try {
            // return new planApplicationService.savePlanApplication(appliedAmount, status,
            // applicationDate, remarks, proofOfDocument);//, user, savingsPlan
            planApplicationService.savePlanApplicationToPlan(appliedAmount, applicationDate, remarks, proofOfDocument,
                    planId , userId);
            return new ResponseEntity<>("Plan application submitted successfully", HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>("Not submitted successfully", HttpStatusCode.valueOf(500));
        }
    }
 
    @GetMapping("/api/planapplications")
    public ResponseEntity<?> getAllPlanApplications() {
        try {
            return new ResponseEntity<>(planApplicationService.getAllPlanApplications(), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }
 
    @PutMapping("/api/planapplications/{id}")
    public ResponseEntity<?> updatePlanApplication(@PathVariable long id,
            @RequestBody PlanApplication updatePlanApplication) {
        try {
            return new ResponseEntity<>(planApplicationService.updatePlanApplication(id, updatePlanApplication),
                    HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }
 
    @DeleteMapping("/api/planapplications/{id}")
    public ResponseEntity<?> deletePlanApplication(@PathVariable long id) {
        try {
            boolean res = planApplicationService.deletePlanApplication(id);
            if (res) {
 
                return new ResponseEntity<>(res, HttpStatusCode.valueOf(200));
            } else {
                return new ResponseEntity<>(res, HttpStatusCode.valueOf(404));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }
 
    @GetMapping("/api/planapplications/{id}")
    public ResponseEntity<?> getPlanApplicationsById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(planApplicationService.getPlanApplicationsById(id),
                    HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
    }
 
    @GetMapping("/api/planapplications/user/{userId}")
    public ResponseEntity<?> getPlanApplicationsByUserId(@PathVariable int userId) {
        try {
            return new ResponseEntity<>(planApplicationService.getPlanApplicationByuserId(userId),
                    HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
    }
 
    // @GetMapping("/api/planapplications/{id}")
    // public ResponseEntity<?> getPlanApplicationsById(@PathVariable long id){
    // try {
    // return new
    // ResponseEntity<>(planApplicationService.getPlanApplicationsById(id) ,
    // HttpStatusCode.valueOf(200));
    // } catch (Exception e) {
    // return new ResponseEntity<>(null , HttpStatusCode.valueOf(500));
    // }
    // }
 
}
 


// package com.examly.springapp.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.bind.annotation.*; 

// import com.examly.springapp.model.PlanApplication;
// import com.examly.springapp.model.SavingsPlan;
// import com.examly.springapp.model.User;
// import com.examly.springapp.service.PlanApplicationService;
// import com.examly.springapp.service.PlanApplicationServiceImpl;

// @RestController
// @CrossOrigin(origins="https://8081-adebeedbfcacffaddfcbfefde.premiumproject.examly.io/")
// public class PlanApplicationController {

//     @Autowired
//     PlanApplicationServiceImpl planApplicationService;

//     @PostMapping("/api/planapplicationss")
//     public ResponseEntity<?> addPlanApplication(@RequestBody PlanApplication planApplication) {
//         try {
//             planApplicationService.addPlanApplication(planApplication);
//             return new ResponseEntity<>(planApplicationService.addPlanApplication(planApplication), HttpStatusCode.valueOf(201));
//         } catch (Exception e) {
//             return new ResponseEntity<>(e, HttpStatusCode.valueOf(500));
//         }
//     }

    
//      @PostMapping("/api/planapplications")
//      public ResponseEntity<?> savePlanApplication(@RequestParam("appliedAmount") double appliedAmount,
//              // @RequestParam("status") String status,
//              @RequestParam("applicationDate") String applicationDate,
//              @RequestParam("remarks") String remarks,
//              @RequestParam("proofOfDocument") MultipartFile proofOfDocument)
//     // @RequestParam("userId") Long userId,
//     // @RequestParam("savingsPlanId") Long savingsPlanId)
//     {

//         // Fetch User and SavingsPlan entities based on IDs
//         // User user = userService.findById(userId);
//         // SavingsPlan savingsPlan = savingsPlanServiceImpl.findById(savingsPlanId);

//         // System.out.println("Received appliedAmount: " + appliedAmount);

//         try {
//             // return new planApplicationService.savePlanApplication(appliedAmount, status,
//             // applicationDate, remarks, proofOfDocument);//, user, savingsPlan
    //         planApplicationService.savePlanApplication(appliedAmount, applicationDate, remarks, proofOfDocument);
    //         return new ResponseEntity<>("Done2", HttpStatusCode.valueOf(200));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
    //     }
    // }

    // @GetMapping("/api/planapplications")
    // public ResponseEntity<?> getAllPlanApplications() {
    //     try {
    //         return new ResponseEntity<>(planApplicationService.getAllPlanApplications(), HttpStatusCode.valueOf(200));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
    //     }
    // }

    // @PutMapping("/api/planapplications/{id}")
    // public ResponseEntity<?> updatePlanApplication(@PathVariable long id,
    //         @RequestBody PlanApplication updatePlanApplication) {
    //     try {
    //         return new ResponseEntity<>(planApplicationService.updatePlanApplication(id, updatePlanApplication),
    //                 HttpStatusCode.valueOf(200));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
    //     }
    // }

    // @DeleteMapping("/api/planapplications/{id}")
    // public ResponseEntity<?> getAllPlanApplications(@PathVariable long id) {
    //     try {
    //         return new ResponseEntity<>(planApplicationService.deletePlanApplication(id), HttpStatusCode.valueOf(200));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
    //     }
    // }

    // @GetMapping("/api/planapplications/{id}")
    // public ResponseEntity<?> getPlanApplicationsById(@PathVariable long id) {
    //     try {
    //         return new ResponseEntity<>(planApplicationService.getPlanApplicationsById(id),
    //                 HttpStatusCode.valueOf(200));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    //     }
    // }

    // @GetMapping("/api/planapplications/user/{userId}")
    // public ResponseEntity<?> getPlanApplicationsByUserId(@PathVariable long userId) {
    //     try {
    //         return new ResponseEntity<>(planApplicationService.getPlanApplicationsById(userId),
    //                 HttpStatusCode.valueOf(200));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    //     }
    // }

    // @GetMapping("/api/planapplications/{id}")
    // public ResponseEntity<?> getPlanApplicationsById(@PathVariable long id){
    // try {
    // return new
    // ResponseEntity<>(planApplicationService.getPlanApplicationsById(id) ,
    // HttpStatusCode.valueOf(200));
    // } catch (Exception e) {
    // return new ResponseEntity<>(null , HttpStatusCode.valueOf(500));
    // }
    // }

// }

// package com.examly.springapp.controller;

// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import com.examly.springapp.model.PlanApplication;
// import com.examly.springapp.model.PlanApplicationDTO;
// import com.examly.springapp.service.PlanApplicationService;

// @RestController
// @CrossOrigin(origins =
// "https://ide-edfdbaadccffcacffaddfcbfefde.premiumproject.examly.io/proxy/8081")
// public class PlanApplicationController {

// @Autowired
// private PlanApplicationService planApplicationService;

// @PostMapping("/api/planapplications")
// public ResponseEntity<?> addPlanApplication(@RequestBody PlanApplicationDTO
// planApplication){
// PlanApplication
// savedplanApplication=planApplicationService.addPlanApplication(planApplication);
// return ResponseEntity.status(201).body(savedplanApplication);
// }

// @GetMapping("/api/planapplications")
// public ResponseEntity<?> getPlanApplications(){
// List<PlanApplication>
// planApplications=planApplicationService.getPlanApplications();
// return ResponseEntity.status(200).body(planApplications);
// }

// @PutMapping("/api/planapplications/{id}")
// public ResponseEntity<?> editPlanApplicationById(@RequestBody PlanApplication
// planApplication, @PathVariable Long id){
// PlanApplication
// editedPlanApplication=planApplicationService.editPlanApplicationById(planApplication,id);
// return ResponseEntity.status(200).body(editedPlanApplication);
// }

// @DeleteMapping("/api/planapplications/{id}")
// public ResponseEntity<?> deletePlanApplicationById(@PathVariable Long id){
// PlanApplication
// deletedPlanApplication=planApplicationService.deletePlanApplicationById(id);
// return ResponseEntity.status(200).body(deletedPlanApplication);
// }

// @GetMapping("/user/{userId}")
// public ResponseEntity<?> getPlanApplicationByuserId(@PathVariable int
// userId){
// List<PlanApplication>
// planApplicationsByUser=planApplicationService.getPlanApplicationByuserId(userId);
// return ResponseEntity.status(200).body(planApplicationsByUser);
// }
// }

// To implement file upload and form submission using Reactive Forms in Angular
// with Spring Boot backend, you will create a reactive form that handles both
// file input and regular fields. Here’s how you can achieve this:
// 1. Frontend (Angular) – Reactive Form for File Upload and Form Data
// Step 1: Import ReactiveFormsModule in Your Angular Module In your Angular
// module,
// import ReactiveFormsModule to use reactive forms:
// import { ReactiveFormsModule } from '@angular/forms'; @NgModule({ imports:
// [ReactiveFormsModule],
// // other module configurations }) export class AppModule {} Step 2: Create
// the Reactive Form Create a reactive form using FormBuilder. You’ll use
// FormData to send both file and form fields in a single request.
// HTML Template:

// <form [formGroup]="planApplicationForm" (ngSubmit)="onSubmit()"
// enctype="multipart/form-data">
// <label>Applied Amount</label>
// <input type="text" formControlName="appliedAmount" placeholder="Applied
// Amount" />
// <label>Status</label>
// <input type="text" formControlName="status" placeholder="Status" />
// <label>Application Date</label>
// <input type="date" formControlName="applicationDate" placeholder="Application
// Date" />
// <label>Remarks</label>
// <textarea formControlName="remarks" placeholder="Remarks"></textarea>
// <!-- File Input for Proof Document -->
// <label>Proof Document</label>
// <input type="file" (change)="onFileSelected($event)"/>
// <!-- Submit Button --> <button type="submit"
// [disabled]="planApplicationForm.invalid">Submit</button>
// </form>

// Component (TypeScript):
// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { HttpClient } from '@angular/common/http';
// @Component({ selector: 'app-plan-application', templateUrl:
// './plan-application.component.html' })
// export class PlanApplicationComponent {
// planApplicationForm: FormGroup;
// selectedFile: File = null;
// constructor(private fb: FormBuilder, private http: HttpClient) {
// // Initialize the reactive form with form controls
// this.planApplicationForm = this.fb.group
// ({ appliedAmount: [null, [Validators.required, Validators.min(0)]],
// status: ['', Validators.required],
// applicationDate: ['', Validators.required], remarks: [''], });
// } // Capture file input
// onFileSelected(event: any): void {
// this.selectedFile = event.target.files[0];
// } // Handle form submission
// onSubmit(): void {
// if (this.planApplicationForm.invalid) {
// return; // Prevent submission if the form is invalid
// }
// // Create FormData object to send with HTTP POST request
// const formData = new FormData(); // Append form fields to the FormData object
// formData.append('appliedAmount',
// this.planApplicationForm.get('appliedAmount').value);
// formData.append('status', this.planApplicationForm.get('status').value);
// formData.append('applicationDate',
// this.planApplicationForm.get('applicationDate').value);
// formData.append('remarks', this.planApplicationForm.get('remarks').value);
// // Append the file to FormData if selected
// if (this.selectedFile) {
// formData.append('proofDocument', this.selectedFile, this.selectedFile.name);
// }
// // Send the form data to the backend
// this.http.post('https://your-backend-url/api/planapplications',
// formData).subscribe(
// response =>
// { console.log('Plan application submitted successfully', response); },
// error =>
// { console.error('Error submitting plan application', error); } );
// }
// }
// Explanation:
// • Reactive Form Initialization:
// FormGroup is created using FormBuilder with controls for appliedAmount,
// status, applicationDate, and remarks.
// • File Selection: The file is captured using the onFileSelected() method,
// which stores the selected file in this.selectedFile.
// • FormData Construction: A FormData object is created and populated with both
// form fields and the selected file.
// This allows sending a multipart/form-data request to the backend.
// • Submit Handling: The onSubmit() method is called when the form is
// submitted. If the form is valid, the data is sent using Angular’s HttpClient.

// 2. Backend (Spring Boot) – Handling Multipart Data On the backend, you can
// handle both form fields and file uploads using Spring Boot’s MultipartFile.
// Controller Code:
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpStatus;
// import java.time.LocalDate;
// @RestController
// @RequestMapping("/api")
// public class PlanApplicationController {
// @PostMapping("/planapplications")
// public ResponseEntity<String> submitPlanApplication(
// @RequestParam("appliedAmount") double appliedAmount, @RequestParam("status")
// String status, @RequestParam("applicationDate") String applicationDate, //
// Parsed later to LocalDate
// @RequestParam("remarks") String remarks, @RequestParam(value =
// "proofDocument", required = false) MultipartFile proofDocument )
// {
// // Parse the date
// LocalDate parsedDate = LocalDate.parse(applicationDate);
// // Handle file upload (saving to a directory)
// if (proofDocument != null && !proofDocument.isEmpty())
// { try
// {
// String filePath = "/path/to/save/" + proofDocument.getOriginalFilename();
// proofDocument.transferTo(new File(filePath)); // Save the file
// } catch (Exception e) {
// return new ResponseEntity<>("File upload failed",
// HttpStatus.INTERNAL_SERVER_ERROR);
// }
// }
// // Create and save the PlanApplication entity
// PlanApplication planApplication = new PlanApplication();
// planApplication.setAppliedAmount(appliedAmount);
// planApplication.setStatus(status);
// planApplication.setApplicationDate(parsedDate);
// planApplication.setRemarks(remarks);
// // Set other fields and save to the database
// return new ResponseEntity<>("Plan application submitted successfully",
// HttpStatus.OK);
// }
// }
// 3. Backend Entity (Optional if Saving to DB) If you’re saving the application
// data to a database, you’ll need an entity class like PlanApplication with the
// necessary fields. @Entity public class PlanApplication { @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private
// double appliedAmount; private String status; private LocalDate
// applicationDate; private String remarks; private String proofDocument; //
// File path for the uploaded document // Getters and setters... } In the
// service/repository layer, you would persist the PlanApplication object to the
// database, saving details like appliedAmount, status, applicationDate, and the
// file path (if a file is uploaded).
// 4. Summary • Frontend: You use a reactive form in Angular to collect both
// form fields and file data. The file is handled separately and combined with
// the form data using FormData. • Backend: You handle the multipart request in
// Spring Boot, processing both form fields and the uploaded file. This method
// ensures that both the form data and the file are sent together and handled
// appropriately on both the client and server sides. Let me know if you need
// further assistance or adjustments!