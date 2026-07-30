import { Injectable } from '@angular/core';
import { environment } from '../../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../models/login-request';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login-response';
import { RegisterUser } from '../models/register-user';
import { User } from '../models/user';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  login(request : LoginRequest):Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request);
  }

  register(request: RegisterUser): Observable<User> {

    return this.http.post<User>(
        `${this.apiUrl}/register`,
        request
    );

}

  saveUser (login : LoginResponse) {
    localStorage.setItem("token", login.accessToken);
    localStorage.setItem("role", login.role);
    localStorage.setItem("userId", login.userId);
    localStorage.setItem("name", login.name);
  }

  getToken() : String | null {
    return localStorage.getItem("token");
  }

  getRole() : String | null {
    return localStorage.getItem("role");
  }

  getUserId(){

  return localStorage.getItem("userId");

  }

  getName(){

  return localStorage.getItem("name");

  }

  isLoggedIn() : boolean {
    return this.getToken() !== null;
  }

  logout() {
    localStorage.clear();
  }

}
