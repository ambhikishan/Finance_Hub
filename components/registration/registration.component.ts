import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  message:boolean=false;
  constructor(private builder : FormBuilder,private router:Router,private authService:AuthService) 
  {
    
   }
  regForm:FormGroup;

  ngOnInit(): void {
  this.regForm = this.builder.group({
      email:this.builder.control("",[Validators.required, Validators.email]),
      password:this.builder.control("",[Validators.required, Validators.pattern("^[a-zA-Z0-9!@#$%^&*()_]+$"),Validators.minLength(8),Validators.maxLength(16)]),
      username:this.builder.control("",Validators.required),
      mobileNumber:this.builder.control("",[Validators.required,Validators.pattern("[0-9]{10}")]),
      userRole:this.builder.control("CUSTOMER",[Validators.required,Validators.pattern("^(MANAGER|CUSTOMER)$")])
  });

  }

  

  onSubmit()
  {
    if(this.regForm.valid)
    {
      this.authService.register(this.regForm.value).subscribe((data:any)=>{
        console.log(data);
        this.router.navigate(['/login'])
      },(error)=>{this.message=true});
    }
  }

}
