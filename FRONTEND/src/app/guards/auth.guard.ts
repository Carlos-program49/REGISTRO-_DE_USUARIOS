import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { SessionStorageService } from '../services/SessionStorageService';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements  CanActivate {

  constructor(private authService: SessionStorageService, private router: Router) {

  }
 
  canActivate(): boolean {
    return this.checkAuth();
  }

  private checkAuth(): boolean {
    console.log("Guard Auth Key::"+ this.authService.get('auth-key'));

    if (this.authService.get('auth-key')) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

};
