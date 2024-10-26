import { Component, OnInit } from '@angular/core';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Feedback } from 'src/app/models/feedback.model';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks : Feedback[]=[];
  
  
  showPopup: boolean = false;
  showLogoutPopup: boolean = false;
  selectedUser: User = {};
  showProfile = false;
 
  constructor(private feedbackService : FeedbackService) { }
 
  ngOnInit(): void {  
    this.getFeedBacks();
  }
 
  public getFeedBacks(){
    this.feedbackService.getFeedbacks().subscribe((data:any)=>{
      this.feedbacks = data
    });
  }
     
     
 
 
  public showUserProfile(user:any) {
    this.selectedUser = user;
    this.showPopup = true;
    this.showProfile=true;
  }
 
 
  closePopup() {
    this.showPopup = false;
    this.selectedUser = null;
  }
 
 
}