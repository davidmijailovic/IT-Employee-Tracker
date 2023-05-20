import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({
    Accept: 'application/json',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + this.authService.getToken(),
  });

  constructor(private authService: AuthService, private http: HttpClient) {}

  createUser(registerParams: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'auth/signup', registerParams, {
      headers: this.headers,
    });
  }

  loginUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'auth/login', user, {
      headers: this.headers,
    });
  }

  fetchUser(id: string): Observable<any> {
    return this.http.get<any>(this.apiHost + 'user' + '/' + id, {
      headers: this.headers,
    });
  }

  editUser(id: any, editInfo: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'user/' + id, editInfo, {
      headers: this.headers,
    });
  }

  getByID(id: string): Observable<any[]> {
    return this.http.get<any[]>(this.apiHost + '/' + id, {
      headers: this.headers,
    });
  }
}
