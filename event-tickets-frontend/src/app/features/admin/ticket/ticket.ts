import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  Ticket,
  TicketStatus
} from '../../../core/models/ticket';

import {
  TicketService
} from '../../../core/services/ticket';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-ticket',

  imports: [
    FormsModule
  ],

  templateUrl: './ticket.html',

  styleUrl: './ticket.css'
})
export class TicketComponent
  implements OnInit {


 allTickets =
    signal<Ticket[]>([]);


  tickets =
    signal<Ticket[]>([]);


  searchKeyword = '';


  selectedStatus: TicketStatus| '' = '';


  selectedDate = '';


  statuses =
    Object.values(TicketStatus);



  constructor(
    private ticketService:
      TicketService
  ) {}


  ngOnInit(): void {

    this.loadTickets();

  }


  loadTickets(): void {

  this.ticketService
    .getAllTickets()
    .subscribe({

      next: (tickets) => {

        this.allTickets.set(tickets);

        this.tickets.set(tickets);

      },

      error: (error) => {

        console.error(
          'Error loading tickets:',
          error
        );

      }

    });

}

  filterTickets(): void {

  const keyword =
    this.searchKeyword
      .toLowerCase()
      .trim();


  const filteredTickets =
    this.allTickets().filter(ticket => {

      const matchesKeyword =

        ticket.userName
          .toLowerCase()
          .includes(keyword)

        ||

        ticket.eventTitle
          .toLowerCase()
          .includes(keyword);


      const matchesStatus =

        !this.selectedStatus

        ||

        ticket.status ===
        this.selectedStatus;


      const matchesDate =

        !this.selectedDate

        ||

        ticket.purchaseDate ===
        this.selectedDate;


      return (

        matchesKeyword

        &&

        matchesStatus

        &&

        matchesDate

      );

    });


  this.tickets.set(
    filteredTickets
  );

}
}