import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { EnquiryService } from 'src/app/services/enquiry.service';
import Swal from 'sweetalert2';
 
@Component({
  selector: 'app-useraddenquiry',
  templateUrl: './useraddenquiry.component.html',
  styleUrls: ['./useraddenquiry.component.css']
})
export class UseraddenquiryComponent implements OnInit {
 
  enquiryForm: FormGroup;
  enquiry:any={};
 
  constructor(private formBuilder: FormBuilder,
                private enquiryService: EnquiryService,
                private authService:AuthService,
                private router:Router) { }
             
  userId:number;
 
  ngOnInit(): void {
    this.userId=Number(this.authService.userId);
    this.enquiryForm = this.formBuilder.group({
      message: this.formBuilder.control("", Validators.required)
    });
  }
 
  public addEnquiry() {
 
    this.enquiry=this.enquiryForm.value
    this.enquiry.user={userId:this.userId}
 
    console.log(this.enquiry);
 
    this.enquiryService.addEnquiry(this.enquiry).subscribe((data:any) => {
      Swal.fire({
        title: 'Success!',
        text: 'Message added successfully',
        icon: 'success',
        confirmButtonText: 'OK'
      }).then(() => {
        this.enquiryForm.reset();
        this.router.navigate(['/userviewEnquiry']);
      });
    });
  }  
 
  public get message() {
    return this.enquiryForm.get('message');
  }
}
 