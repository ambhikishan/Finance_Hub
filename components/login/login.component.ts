import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AppComponent } from 'src/app/app.component';
import { UserplanapplicationformComponent } from '../userplanapplicationform/userplanapplicationform.component';
import { Login } from 'src/app/models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
loginForm:FormGroup;

invalid_cred:boolean=false;

dummyLogin:Login;

  constructor(private builder : FormBuilder, 
    private authService : AuthService,
     private router : Router ,
      private component:AppComponent) 
      { }

  ngOnInit(): void {
    this.loginForm = this.builder.group({
      username:this.builder.control("",[Validators.required]),
      password:this.builder.control("",[Validators.required,Validators.pattern("^[a-zA-Z0-9!@#$%^&*()_]+$")])

    });
  }

  login()
  { localStorage.removeItem('token');
  
    if(this.loginForm.valid)
    { 
      this.authService.login(this.loginForm.value).subscribe((data:any)=>{
        if(data.token!=null){
          this.authService.logIn();
          data.role = data.role.substring(1, data.role.length-1);

          this.authService.setRole(data.role);

          console.log(data.token+" token before store");

          this.authService.storeToken(data.token);


          console.log(data.id+"storing user id");
          localStorage.setItem('userId',data.id);
          localStorage.setItem('username',data.username);
          localStorage.setItem('role',data.role);
          console.log(data.token+" token after store");



        this.authService.userId=data.id;
        
          
          this.component.role=data.role;
          this.component.isLoggedIn=true;
      
          this.router.navigate(['/home']);
        }
          
        
          
        
      },(error)=>{
          console.log("invalid Cred");
          this.invalid_cred=true;

      });
    
   
          
      
    }
  }

  
  
}
