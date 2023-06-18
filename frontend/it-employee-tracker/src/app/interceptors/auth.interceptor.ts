import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router, private authService: AuthService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const authToken = localStorage.getItem('jwt-access-token');

    if (authToken && req.url.startsWith('https://localhost:')) {
      const cloned = req.clone({
        headers: req.headers
          .set('Authorization', 'Bearer ' + authToken)
          .set('X-Content-Type-Options', 'nosniff'),
      });

      return next.handle(cloned).pipe(tap((err) => {}));
    } else {
      const cloned = req.clone({
        headers: req.headers.set('X-Content-Type-Options', 'nosniff'),
      });
      return next.handle(cloned);
    }
  }
}
