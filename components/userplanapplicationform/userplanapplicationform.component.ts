import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SavingsPlan } from 'src/app/models/savingsplan.model';
import { AuthService } from 'src/app/services/auth.service';
import { PlanApplicationformService } from 'src/app/services/planapplicationform.service';
import { SavingsPlanService } from 'src/app/services/savingsplan.service';
 
@Component({
  selector: 'app-userplanapplicationform',
  templateUrl: './userplanapplicationform.component.html',
  styleUrls: ['./userplanapplicationform.component.css']
})
export class UserplanapplicationformComponent implements OnInit {
 
  savingsPlanID;
  savingsPlanObj:SavingsPlan={
    name: '',
    goalAmount: 0,
    timeFrame: 0,
    riskLevel: '',
    description: '',
    status: '',
  }
  constructor(private planApplicationformService: PlanApplicationformService, private formBuilder: FormBuilder , private activatedRoute:ActivatedRoute,private savingsPlanService:SavingsPlanService , private router:Router,private authService:AuthService) {
  }
 
  userId:number;
  amount:number;
 
 
 
  ngOnInit(): void {
    console.log("User Id" + this.authService.userId);
    this.userId=this.authService.userId;
       
    this.planForm = this.formBuilder.group({
      appliedAmount: ["", Validators.required],
      // applicationDate: ["", Validators.required],
      remarks: [""],
      proofOfDocument: ["", Validators.required],
      savingsPlan: ["", Validators.required]
    })
   
 
    console.log("savingsPlanObj before " ,this.savingsPlanObj)
 
    this.savingsPlanID = +(this.activatedRoute.snapshot.paramMap.get("id"));
    console.log("id" , this.savingsPlanID);
 
    this.savingsPlanService.getSavingsPlanById(this.savingsPlanID).subscribe((data)=>{
      this.savingsPlanObj = data;
      this.amount = Math.round(this.savingsPlanObj.goalAmount / (this.savingsPlanObj.timeFrame * 12));
      //new addition
      console.log("savingsPlanObj in userplan " ,this.savingsPlanObj)
      // console.log("savingsPlanObj name in userplan " ,this.savingsPlanObj.name);
    });
 
 
  }
 
  get appliedAmount() {
    return this.planForm.get("appliedAmount");
  }
  get applicationDate() {
    return this.planForm.get("applicationDate");
  }
  get remarks() {
    return this.planForm.get("remarks");
  }
  get proofOfDocument() {
    return this.planForm.get("proofOfDocument");
  }
  get savingsPlan() {
    return this.planForm.get("savingsPlan");
  }
  planForm: FormGroup;
  fileError: string | null = null;
 
 
 
 
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile && this.selectedFile.type !== 'application/pdf') {
      this.fileError = 'Only PDF files are allowed!';
      event.target.value = ''; // Clear the input
    } else {
      this.fileError = null;
      // Process the PDF file
      console.log('Selected file:',this.selectedFile);
      this.planForm.patchValue({ proofOfDocument: this.selectedFile });
    }
  }
 
  selectedFile: File | null = null;
  addPlanApplicationForm() {
    console.log("add plan before ");
    if (this.planForm.valid && this.selectedFile) {
      const formData = new FormData();
      formData.append('appliedAmount', this.planForm.get('appliedAmount')?.value);
      formData.append('applicationDate', new Date().toLocaleString('en-IN', { timeZone: 'Asia/Kolkata' }).split(',')[0]);
      console.log(new Date().toISOString().split('T')[0])
      formData.append('remarks', this.planForm.get('remarks')?.value);
      formData.append('proofOfDocument', this.selectedFile);
      // formData.append('savingsPlan', this.planForm.get('savingsPlan')?.value);
 
      console.log("add plan after ", formData);
      formData.forEach((value, key) => {
        console.log(key, value);
      });
     
      console.log("ID in add " , this.savingsPlanObj.savingsPlanId);
     
      this.planApplicationformService.addPlanApplication(formData , this.savingsPlanObj.savingsPlanId , this.userId).subscribe(// ,  this.savingsPlanObj.savingsPlanId
        data => {
          console.log('Form submitted successfully', data);
        },
        (error: HttpErrorResponse) => {
          if (error.error instanceof ErrorEvent) {
            // Client-side error
            console.error('Client-side error:', error.error.message);
          } else {
            // Server-side error
            console.error(`Server-side error: ${error.status} - ${error.message}`);
            // Additional logging
            console.error('Error details:', error);
          }
        }
      );
 
    }
   
 
    this.router.navigate(['/userviewsavingsplan'])
 
  }
}