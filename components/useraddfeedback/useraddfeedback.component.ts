import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { PlanApplication } from 'src/app/models/planApplication.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { PlanApplicationformService } from 'src/app/services/planapplicationform.service';


@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
  addform : FormGroup;
  feedback:Feedback={};
  userId:number;
  planApplications:PlanApplication[]=[];
  planName:string
  
  


  constructor(private service : FeedbackService,
              private builder : FormBuilder,
              private router:Router,
              private authService:AuthService,
              private planApplicationformService:PlanApplicationformService) { }

  ngOnInit(): void {
    this.userId=Number(this.authService.userId)
    this.addform = this.builder.group({
      feedbackText : this.builder.control("",Validators.required),
      planName:this.builder.control("",Validators.required)
    })

   this.getAllPlanApplicationsByUserId(this.userId);
  }

  getAllPlanApplicationsByUserId(userId:number){
    this.planApplicationformService.getAppliedPlans(userId).subscribe((data:any)=>{
      this.planApplications=data;
      console.log("get all " , this.planApplications)

    })
  }

  public addFeedback() {
      this.feedback=this.addform.value;
      this.feedback.date=new Date();
      
     
    
       this.feedback.user={userId:this.userId}
        console.log(this.feedback);
        
      
      this.service.sendFeedback(this.feedback).subscribe((data:any) => {
        alert("Successfully added");

        this.router.navigate(['/userviewfeedback'])
      });
    }
        
    get feedbackText(){
      return this.addform.get('feedbackText');
    }
  }
    