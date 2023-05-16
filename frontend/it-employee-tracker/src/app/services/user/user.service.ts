import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  apiHost: string = 'http://localhost:8081/auth/';
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient) {}

  createUser(registerParams: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'signup', registerParams, {
      headers: this.headers,
    });
  }

  loginUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'login', user, {
      headers: this.headers,
    });
  }
}
