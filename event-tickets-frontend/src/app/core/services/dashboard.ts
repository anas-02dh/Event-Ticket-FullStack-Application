import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dashboard } from '../models/dashboard';
import { environment } from '../../../environment/environment';
import { RecentEvent } from '../models/event-recent';
import { RecentTicket } from '../models/ticket-recent';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {

  private apiUrl = `${environment.apiUrl}`;


  constructor(
    private http :HttpClient
  ){}

  getStatistics(): Observable<Dashboard>{
     return this.http.get<Dashboard>(
      `${this.apiUrl}/admin/dashboard`
    );

}

  getRecentEvents(): Observable<RecentEvent[]> {

    return this.http.get<RecentEvent[]>(
      `${this.apiUrl}/admin/dashboard/recent-events`
    );

  }

  getRecentTickets(): Observable<RecentTicket[]> {

    return this.http.get<RecentTicket[]>(
        `${this.apiUrl}/admin/dashboard/recent-tickets`
    );

}
}