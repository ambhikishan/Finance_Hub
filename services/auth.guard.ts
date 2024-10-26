import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { LoginComponent } from '../components/login/login.component';
import { RegistrationComponent } from '../components/registration/registration.component';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private service:AuthService,private router :Router ){

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.service.isLoggedIn && this.service.getRole()=='CUSTOMER')
      {
        return true;
      }

      else if(this.service.isLoggedIn==false){
        this.router.navigate(['/login']);
        return false;
      }
      else if(this.service.role=='MANAGER'){
        this.router.navigate(['/home']);
        return false;
      }

      
    
  }
  
  
}
