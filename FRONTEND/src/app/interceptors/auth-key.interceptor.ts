import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SessionStorageService } from '../services/SessionStorageService';

@Injectable()
export class authKeyInterceptor implements HttpInterceptor {

  constructor(private storageService: SessionStorageService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.storageService.get('auth-key');

    console.log("Authentication key:"+token);

    if (token) {
      req = req.clone({
        url:  req.url,
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(req);
  }
}
