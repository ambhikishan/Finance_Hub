import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Enquiry } from 'src/app/models/enquiry.model';
import { AuthService } from 'src/app/services/auth.service';
import { EnquiryService } from 'src/app/services/enquiry.service';

@Component({
  selector: 'app-managereditenquiry',
  templateUrl: './managereditenquiry.component.html',
  styleUrls: ['./managereditenquiry.component.css']
})
export class ManagereditenquiryComponent implements OnInit {
  
  constructor(private enquiryService:EnquiryService,
                private router : Router,
                  private formBuilder : FormBuilder,
                    private activatedRoute : ActivatedRoute,private authService:AuthService) { }

  enquiryId : number ;
  enquiry : Enquiry={};
  enquiryForm : FormGroup 
  userId:number;
  

  ngOnInit(): void {
    this.userId=Number(this.authService.userId);
    console.log(this.userId);
    this.enquiryForm = this.formBuilder.group({
      message : this.formBuilder.control("",Validators.required)

    })
    this.enquiryId=parseInt(this.activatedRoute.snapshot.paramMap.get("id"));
    this.enquiryService.getEnquiryById(this.enquiryId).subscribe((data:any) => {
      this.enquiry=data;
      console.log(data);
      this.enquiryForm.patchValue(data);
    })
  }

 public updateEnquiry() {
  this.enquiry=this.enquiryForm.value
  this.enquiry.user={userId:this.userId}
  console.log(this.enquiry)
    this.enquiryService.updateEnquiry(this.enquiryId,this.enquiry).subscribe((data:any) => {
      this.router.navigate(['/managerview']);
    }
  )}
}