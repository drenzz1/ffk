import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../models/authentication-request";
import {AuthenticationService} from "../../services/auth/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  ERRORMSG:string='';
  authenticationRequest:AuthenticationRequest = {};

  constructor(private authService:AuthenticationService,private router : Router) {
  }

  login() {
    console.log('asefasef')
    this.ERRORMSG='';
    this.authService.login(this.authenticationRequest).subscribe({
      next:(authenticationResponse)=>{
        localStorage.setItem('user',JSON.stringify(authenticationResponse))
        this.router.navigate(['customers']);
      },
      error:(err)=>{
        if (err.error.statusCode===401){
          this.ERRORMSG='  Email or password is incorrect';
        }

      }
    });
  }
}
