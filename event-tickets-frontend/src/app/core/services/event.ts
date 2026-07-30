import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environment/environment';
import { Event } from '../models/event';
import { CreateEvent } from '../models/create-event';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = `${environment.apiUrl}/events`;

  constructor(
    private http: HttpClient
  ) {}

  getAllEvents(): Observable<Event[]> {

    return this.http.get<Event[]>(
      this.apiUrl
    );

  }

  searchEvents(
    keyword: string
  ): Observable<Event[]> {

    return this.http.get<Event[]>(
      `${this.apiUrl}/search`,
      {
        params: {
          keyword
        }
      }
    );

  }

  getEventById(
    id: string
  ): Observable<Event> {

    return this.http.get<Event>(
      `${this.apiUrl}/${id}`
    );

  }

  createEvent(
    event: CreateEvent
  ): Observable<Event> {

    return this.http.post<Event>(
      this.apiUrl,
      event
    );

  }

  deleteEvent(
    id: string
  ): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );

  }

}