import { EventEmitter, Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  private readonly accessTokenKey = 'jwt-access-token';
  private readonly refreshTokenKey = 'jwt-refresh-token';
  onLogout: EventEmitter<void> = new EventEmitter<void>();
  onLogin: EventEmitter<void> = new EventEmitter<void>();

  setToken(accessToken: string, refreshToken: string): void {
    localStorage.setItem(this.accessTokenKey, accessToken);
    localStorage.setItem(this.refreshTokenKey, refreshToken);
    this.onLogin.emit();
  }

  getUserRole() {
    var jwt;
    if (this.getToken()) {
      jwt = jwtDecode(this.getToken()!) as any;
    } else {
      return '';
    }
    return jwt.role;
  }

  getToken(): string | null {
    return localStorage.getItem(this.accessTokenKey);
  }

  removeToken(): void {
    localStorage.removeItem(this.accessTokenKey);
    localStorage.removeItem(this.refreshTokenKey);
    this.onLogout.emit();
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    return !!token;
  }
}
