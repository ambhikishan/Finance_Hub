import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SavingsPlan } from 'src/app/models/savingsplan.model';
import { SavingsPlanService } from 'src/app/services/savingsplan.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-managereditsavingsplan',
  templateUrl: './managereditsavingsplan.component.html',
  styleUrls: ['./managereditsavingsplan.component.css']
})
export class ManagereditsavingsplanComponent implements OnInit {

  savingsPlanId:number;
  savingsPlan:SavingsPlan={};
  form:FormGroup;
  showMessage:Boolean=false;
  constructor(private service:SavingsPlanService,private activatedRoute:ActivatedRoute,private formBuilder:FormBuilder,private router:Router) { }

  ngOnInit(): void {
    this.savingsPlanId=parseInt(this.activatedRoute.snapshot.paramMap.get("id"));
    this.service.getSavingsPlanById(this.savingsPlanId).subscribe((data)=>{
    this.savingsPlan=data;
    this.form.patchValue(data);
  });

    this.form=this.formBuilder.group(
      {
        name:this.formBuilder.control("",Validators.required),
        goalAmount:this.formBuilder.control("",Validators.required),
        timeFrame:this.formBuilder.control("",Validators.required),
        riskLevel:this.formBuilder.control("",Validators.required),
        description:this.formBuilder.control("",Validators.required),
        status:this.formBuilder.control("",Validators.required)
      }
    )
  }

  public updateSavingsPlan()
  {
    this.savingsPlan=this.form.value;
    this.service.updateSavingsPlan(this.savingsPlanId,this.savingsPlan).subscribe((data)=>{

      Swal.fire({
        title: 'Success!',
        text: 'Savings plan successfully edited.',
        icon: 'success',
        confirmButtonText: 'OK'
      });
      this.router.navigate(['/viewsavingplan'])

    });
    

  }

 

  public get name()
  {
    return this.form.get('name');
  }

  
  public get goalAmount()
  {
    return this.form.get('goalAmount');
  }

  public get timeFrame()
  {
    return this.form.get('timeFrame');
  }

  public get riskLevel()
  {
    return this.form.get('riskLevel');
  }

  public get description()
  {
    return this.form.get('description');
  }

  public get status()
  {
    return this.form.get('status');
  }
}