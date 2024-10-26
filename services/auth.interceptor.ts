import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let token :string = localStorage.getItem('token');
    console.log(token+"Token Called");
    if(token !=null)
    {
      request =request.clone({setHeaders:{Authorization:`Bearer ${token}`}});
    }

    
    console.log(request);
    return next.handle(request);
    // if(localStorage.getItem('token')){
    //   let jwtToken : string = localStorage.getItem('token')
    //   request = request.clone({
    //     setHeaders : {
    //       Authorization : `Bearer ${jwtToken}`
    //     }
    //   })
    // }
    // console.log(request);
    // return next.handle(request);
  }

    
  }

