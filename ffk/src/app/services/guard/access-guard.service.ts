import {inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationResponse} from "../../models/authentication-response";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AccessGuardService {
  constructor(private router: Router) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user')
    if (storedUser){
      const authResponse : AuthenticationResponse = JSON.parse(storedUser)
      const token = authResponse.token
      if (token){
        const jwtHelper = new JwtHelperService()
        const isTokenValid= !jwtHelper.isTokenExpired(token)
        return isTokenValid;
      }
    }
    this.router.navigate(['login']);
    return false ;
  }

}
export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AccessGuardService).canActivate(next, state);
}
