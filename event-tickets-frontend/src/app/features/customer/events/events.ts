import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  FormsModule
} from '@angular/forms';

import {
  Router
} from '@angular/router';

import {
  Event
} from '../../../core/models/event';

import {
  EventService
} from '../../../core/services/event';


@Component({

  selector: 'app-customer-events',

  imports: [

    FormsModule

  ],

  templateUrl:
    './events.html',

  styleUrl:
    './events.css'

})
export class CustomerEventsComponent
  implements OnInit {


  events =
    signal<Event[]>([]);


  searchKeyword = '';


  constructor(

    private eventService:
      EventService,

    private router:
      Router

  ) {}


  ngOnInit(): void {

    this.loadEvents();

  }


  loadEvents(): void {

    this.eventService

      .getAllEvents()

      .subscribe({

        next: (events) => {

          this.events.set(
            events
          );

        },

        error: (error) => {

          console.error(

            'Error loading events:',

            error

          );

        }

      });

  }


  searchEvents(): void {

    const keyword =

      this.searchKeyword
        .trim();


    if (!keyword) {

      this.loadEvents();

      return;

    }


    this.eventService

      .searchEvents(keyword)

      .subscribe({

        next: (events) => {

          this.events.set(
            events
          );

        },

        error: (error) => {

          console.error(

            'Error searching events:',

            error

          );

        }

      });

  }


  clearSearch(): void {

    this.searchKeyword = '';

    this.loadEvents();

  }


  viewEventDetails(
    eventId: string
  ): void {

    this.router.navigate([

      '/customer/events',

      eventId

    ]);

  }

}