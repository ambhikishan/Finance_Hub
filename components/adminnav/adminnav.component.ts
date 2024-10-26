import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {

  constructor(private auth:AuthService, private router : Router, private main : AppComponent) { }

  ngOnInit(): void {
  }
  logout()
  {
    this.auth.userId=-1;
    this.auth.setRole('');
    localStorage.removeItem('userId');
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');

    this.auth.logOut();
    this.main.isLoggedIn=false;
    this.main.role='';
    this.router.navigate(['/login']);
  }

}
