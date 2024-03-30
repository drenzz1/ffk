import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationRequest} from "../../models/authentication-request";
import {Observable} from "rxjs";
import {AuthenticationResponse} from "../../models/authentication-response";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private readonly auth:string = `${environment.api.baseUrl}/${environment.api.authUrl}`;
  constructor(
    private http:HttpClient
  ) { }

  login(authRequest:AuthenticationRequest): Observable<AuthenticationResponse>{
      return this.http.post<AuthenticationResponse>(this.auth,authRequest);
  }
}
