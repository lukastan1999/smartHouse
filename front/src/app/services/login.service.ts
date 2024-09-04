import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user'; // Adjust path based on your app structure

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/login'; // Adjust based on your backend API URL

  constructor(private http: HttpClient) {}

  login(loginData: { email: string; password: string }): Observable<User> {
    return this.http.post<User>(this.apiUrl, loginData);
  }
}
