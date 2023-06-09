import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  apiHost: string = 'https://localhost:8443/';
  headers: HttpHeaders = new HttpHeaders({
    Accept: 'application/json',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + this.authService.getToken(),
    'Access-Control-Allow-Origin': 'https://localhost:4200/',
    'X-Content-Type-Options': 'nosniff',
  });

  constructor(private authService: AuthService, private http: HttpClient) {}

  editProjectDescription(editInfo: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'project', editInfo, {});
  }
}
