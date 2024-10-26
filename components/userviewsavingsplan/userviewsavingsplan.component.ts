import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SavingsPlan } from 'src/app/models/savingsplan.model';
import { SavingsPlanService } from 'src/app/services/savingsplan.service';


@Component({
  selector: 'app-userviewsavingsplan',
  templateUrl: './userviewsavingsplan.component.html',
  styleUrls: ['./userviewsavingsplan.component.css']
})
export class UserviewsavingsplanComponent implements OnInit {

  // savingsPlanId:number;
  // savingsPlan:SavingsPlan={};
  savingsPlans:SavingsPlan[]=[];
  p:number=1;

  constructor(private service:SavingsPlanService,private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
  //   this.savingsPlanId=parseInt(this.activatedRoute.snapshot.params['id']);
  //   this.service.getSavingsPlanById(this.savingsPlanId).subscribe((data)=>{this.savingsPlan=data;
    
  // });
  this.getAllSavingsPlans();
  }
  public getAllSavingsPlans()
  {
    this.service.getAllSavingsPlans().subscribe((data)=>{this.savingsPlans=data})
  }

  public filterByName(fname)
  {
    if(fname=="")
    {
      this.getAllSavingsPlans();
    }
    else
    {
      this.service.getAllSavingsPlans().subscribe((data)=>{this.savingsPlans=data;
      this.savingsPlans=this.savingsPlans.filter(p=>{return p.name.startsWith(fname)})})
    }
  }

}