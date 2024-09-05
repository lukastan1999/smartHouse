import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  private baseUrl = 'http://localhost:8080/api/accommodation'; // Update with your backend URL

  constructor(private http: HttpClient) { }

  getAccommodation(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/get?id=${id}`);
  }
}
