import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { AuthService } from 'src/app/services/auth.service';

import { FeedbackService } from 'src/app/services/feedback.service';
import { UseraddfeedbackComponent } from '../useraddfeedback/useraddfeedback.component';

 
@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {
 
  viewfeedback : Feedback[]=[];
  feedbackId:number;
  userId:number=this.authService.userId;
  delPopup : boolean = false;
  feedbackToDel : any = null;
  planName:string;

 
  constructor(private service: FeedbackService,
    private authService:AuthService,
    private route:ActivatedRoute) { }

 
  ngOnInit(): void {
    this.userId=Number(this.authService.userId);
    this.getFeebacksByUserId(this.userId);
    //console.log(this.viewfeedback[0].planName);
   
  

  }
  
 
  public getFeedbacks(){
    this.service.getFeedbacks().subscribe((data:any)=>{
      this.viewfeedback=data;
    })
  }

  public getFeebacksByUserId(userId:number){
    this.service.getAllFeedbacksByUserId(userId).subscribe((data:any)=>{
      this.viewfeedback=data;
    })
  }

  public deleteFeedback(){
    this.service.deleteFeedbackById(this.feedbackId).subscribe((data:any)=>{
      this.getFeebacksByUserId(this.userId)
      this.delPopup=false;
    })
  }

 
  public confirmDelete(id : number){
    this.feedbackId=id;
      this.delPopup=true;
    }
   

  public closeDel(){
    this.delPopup=false;
  }


   
 

}