import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  ActivatedRoute,
  Router
} from '@angular/router';

import {
  EventService
} from '../../../core/services/event';

import {
  TicketService
} from '../../../core/services/ticket';

import {
  Event
} from '../../../core/models/event';

import {
  PurchaseTicket
} from '../../../core/models/purchase-ticket';

import {
  FormsModule
} from '@angular/forms';


@Component({

  selector: 'app-event-details',

  imports: [
    FormsModule
  ],

  templateUrl:
    './event-details.html',

  styleUrl:
    './event-details.css'

})
export class EventDetailsComponent
  implements OnInit {


  event =
    signal<Event | null>(null);


  quantity = 1;


  isPurchasing = false;


  errorMessage = '';


  successMessage = '';


  constructor(

    private route:
      ActivatedRoute,

    private router:
      Router,

    private eventService:
      EventService,

    private ticketService:
      TicketService

  ) {}


  ngOnInit(): void {

    const eventId =
      this.route.snapshot.paramMap
        .get('id');


    if (!eventId) {

      return;

    }


    this.loadEvent(eventId);

  }


  loadEvent(
    eventId: string
  ): void {

    this.eventService

      .getEventById(eventId)

      .subscribe({

        next: (event) => {

          this.event.set(event);

        },

        error: (error) => {

          console.error(

            'Error loading event:',

            error

          );

        }

      });

  }


  increaseQuantity(): void {

    const currentEvent =
      this.event();


    if (!currentEvent) {

      return;

    }


    if (

      this.quantity <
      currentEvent.remainSeats

    ) {

      this.quantity++;

    }

  }


  decreaseQuantity(): void {

    if (this.quantity > 1) {

      this.quantity--;

    }

  }


  purchaseEvent(): void {

    const currentEvent =
      this.event();


    if (!currentEvent) {

      return;

    }


    const userId =
      localStorage.getItem(
        'userId'
      );


    if (!userId) {

      this.router.navigate([
        '/login'
      ]);

      return;

    }


    if (

      this.quantity < 1 ||

      this.quantity >
      currentEvent.remainSeats

    ) {

      this.errorMessage =
        'Invalid ticket quantity.';

      return;

    }


    const purchaseTicket:
      PurchaseTicket = {

        eventId:
          currentEvent.id,

        quantity:
          this.quantity

      };


    this.isPurchasing = true;

    this.errorMessage = '';

    this.successMessage = '';


    this.ticketService

      .purchaseTicket(

        userId,

        purchaseTicket

      )

      .subscribe({

        next: (ticket) => {

          this.isPurchasing =
            false;


          this.successMessage =
            'Ticket purchased successfully.';


          // Update available seats
          this.event.update(
            event => {

              if (!event) {

                return event;

              }


              return {

                ...event,

                remainSeats:

                  event.remainSeats -

                  this.quantity

              };

            }

          );


          this.quantity = 1;

        },

        error: (error) => {

          this.isPurchasing =
            false;


          console.error(

            'Error purchasing ticket:',

            error

          );


          this.errorMessage =

            error.error?.message ||

            'Unable to purchase ticket.';

        }

      });

  }


  goBack(): void {

    this.router.navigate([
      '/customer/events'
    ]);

  }

}