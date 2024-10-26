import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Enquiry } from 'src/app/models/enquiry.model';
import { AuthService } from 'src/app/services/auth.service';
import { EnquiryService } from 'src/app/services/enquiry.service';
import Swal from 'sweetalert2';
 
@Component({
  selector: 'app-userviewenquiry',
  templateUrl: './userviewenquiry.component.html',
  styleUrls: ['./userviewenquiry.component.css']
})
export class UserviewenquiryComponent implements OnInit {
 
  enquiry:Enquiry={};
  userId : number
  enquiryId : number
 
  enquiries: Enquiry[]=[]
 
  constructor(private enquiryService:EnquiryService,private authService:AuthService) { }
 
  ngOnInit(): void {
    this.userId=Number(this.authService.userId);
    this.getUserEnquiries();
  }
 
  public getUserEnquiries() {
    this.enquiryService.getUserEnquiries(this.userId).subscribe((data:any) => {
      this.enquiries=data;
    })
  }
 
 
  public deleteEnquiry(enquiryId: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: "Do you want to delete this enquiry?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.enquiryService.deleteEnquiry(enquiryId).subscribe((data: any) => {
          Swal.fire(
            'Deleted!',
            'The enquiry has been deleted successfully.',
            'success'
          );
          this.getUserEnquiries();
        });
      }
    });
  }
 
 
  public filterByMessage(fMessage : string) {
    
    if(fMessage == "") {
      this.getUserEnquiries();
    }
    else {
      this.enquiryService.getUserEnquiries(this.userId).subscribe((data:any) => {
        this.enquiries=data;
        this.enquiries=this.enquiries.filter(e => e.message.includes(fMessage))
      })
    }
  }
}
 