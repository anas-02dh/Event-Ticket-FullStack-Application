import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environment/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) {}

  getCustomers(): Observable<User[]> {

    return this.http.get<User[]>(
      `${this.apiUrl}/users/CUSTOMER`
    );

  }

  searchUsers(
    keyword: string
  ): Observable<User[]> {

    return this.http.get<User[]>(
      `${this.apiUrl}/users/search`,
      {
        params: {
          keyword
        }
      }
    );

  }


   getUsersByRole(role: string): Observable<User[]> {

    return this.http.get<User[]>(
      `${this.apiUrl}/users/${role}`
    );

  }

  deleteUser(
    userId: string
  ): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/users/${userId}`
    );

  }

  

}