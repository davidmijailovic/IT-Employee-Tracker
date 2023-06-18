import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SkillService {
  apiHost: string = 'https://localhost:8443/';
  headers: HttpHeaders = new HttpHeaders({
    Accept: 'application/json',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + this.authService.getToken(),
    'Access-Control-Allow-Origin': 'https://localhost:4200/',
    'X-Content-Type-Options': 'nosniff',
  });

  constructor(private authService: AuthService, private http: HttpClient) {}

  fetchSkills(): Observable<any> {
    return this.http.get<any>(this.apiHost + 'skill', {});
  }

  addSkill(skill: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'skill/add', skill, {});
  }
}
