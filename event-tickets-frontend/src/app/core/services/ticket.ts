import {
  Injectable
} from '@angular/core';

import {
  HttpClient
} from '@angular/common/http';

import {
  Observable
} from 'rxjs';

import {
  environment
} from '../../../environment/environment';

import {
  Ticket
} from '../models/ticket';

import {
  PurchaseTicket
} from '../models/purchase-ticket';

import {
  PurchaseHistory
} from '../models/purchase-history';


@Injectable({
  providedIn: 'root'
})
export class TicketService {


  private apiUrl =
    `${environment.apiUrl}/tickets`;


  constructor(
    private http: HttpClient
  ) {}


  // ADMIN

  getAllTickets():

    Observable<Ticket[]> {

    return this.http.get<Ticket[]>(
      this.apiUrl
    );

  }


  // CUSTOMER

  purchaseTicket(

    userId: string,

    purchaseTicket: PurchaseTicket

  ): Observable<Ticket> {

    return this.http.post<Ticket>(

      `${this.apiUrl}/purchase/${userId}`,

      purchaseTicket

    );

  }


  // CUSTOMER

  getPurchaseHistory(

    userId: string

  ): Observable<PurchaseHistory> {

    return this.http.get<PurchaseHistory>(

      `${this.apiUrl}/purchase/history/${userId}`

    );

  }

}