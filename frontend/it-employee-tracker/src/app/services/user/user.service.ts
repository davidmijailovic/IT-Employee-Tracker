import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  apiHost: string = 'https://localhost:8443/';
  headers: HttpHeaders = new HttpHeaders({
    Accept: 'application/json',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + this.authService.getToken(),
    'Access-Control-Allow-Origin': this.apiHost,
  });

  constructor(private authService: AuthService, private http: HttpClient) {}

  createUser(registerParams: any): Observable<string> {
    return this.http.post<any>(
      this.apiHost + 'auth/signup',
      registerParams,
      {}
    );
  }

  loginUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'auth/login', user, {});
  }

  fetchUser(id: string): Observable<any> {
    return this.http.get<any>(this.apiHost + 'user' + '/' + id, {});
  }

  editUser(id: any, editInfo: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'user/' + id, editInfo, {});
  }

  getByID(id: string): Observable<any[]> {
    return this.http.get<any[]>(this.apiHost + '/' + id, {});
  }
}
