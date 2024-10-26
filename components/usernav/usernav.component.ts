import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {

  constructor(private auth:AuthService, private router : Router, private main : AppComponent) { }

  ngOnInit(): void {
  }

  logout()
  {
    this.auth.userId=-1;
    this.auth.setRole(null);
    localStorage.removeItem('userId');
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');

    
    this.auth.logOut();
    this.main.isLoggedIn=false;
    this.main.role=null;
    this.router.navigate(['/login']);
  }

}
