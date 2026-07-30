import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { EventService } from '../../../core/services/event';


import { Event } from '../../../core/models/event';
import { User } from '../../../core/models/user';
import { CreateEvent } from '../../../core/models/create-event';
import { UserService } from '../../../core/services/user';

import { Category } from '../../../core/models/category';
import { CategoryService } from '../../../core/services/category';

@Component({
  selector: 'app-events',

  imports: [
    FormsModule,
    ReactiveFormsModule
  ],

  templateUrl: './events.html',

  styleUrl: './events.css'
})
export class EventsComponent implements OnInit {

  events = signal<Event[]>([]);

  organizers = signal<User[]>([]);
  
  categories = signal<Category[]>([]);
  
  searchKeyword = '';

  showCreateForm = false;

  eventForm: FormGroup;


  constructor(

    private eventService: EventService,

    private userService: UserService,

    private categoryService: CategoryService,

    private formBuilder: FormBuilder

  ) {

    this.eventForm =
      this.formBuilder.group({

        title: [

          '',

          [
            Validators.required
          ]

        ],

        description: [

          ''

        ],

        date: [

          '',

          [
            Validators.required
          ]

        ],

        time: [

          '',

          [
            Validators.required
          ]

        ],

        location: [

          '',

          [
            Validators.required
          ]

        ],

        price: [

          0,

          [

            Validators.required,

            Validators.min(0.01)

          ]

        ],

        capacity: [

          1,

          [

            Validators.required,

            Validators.min(1)

          ]

        ],

        categoryName: [

          '',

          [

            Validators.required

          ]

        ],

        organizerId: [

          '',

          [

            Validators.required

          ]

        ]

      });

  }


  ngOnInit(): void {

    this.loadEvents();

    this.loadOrganizers();

    this.loadCategories();
  }


  loadEvents(): void {

    this.eventService

      .getAllEvents()

      .subscribe({

        next: (events) => {

          this.events.set(events);

        },

        error: (error) => {

          console.error(

            'Error loading events:',

            error

          );

        }

      });

  }


  loadOrganizers(): void {

    this.userService

      .getUsersByRole('ADMIN')

      .subscribe({

        next: (users) => {

          this.organizers.set(users);

        },

        error: (error) => {

          console.error(

            'Error loading organizers:',

            error

          );

        }

      });

  }


  searchEvents(): void {

    const keyword =
      this.searchKeyword.trim();


    if (!keyword) {

      this.loadEvents();

      return;

    }


    this.eventService

      .searchEvents(keyword)

      .subscribe({

        next: (events) => {

          this.events.set(events);

        },

        error: (error) => {

          console.error(

            'Error searching events:',

            error

          );

        }

      });

  }


  openCreateForm(): void {

    this.showCreateForm = true;


    this.eventForm.reset({

      title: '',

      description: '',

      date: '',

      time: '',

      location: '',

      price: 0,

      capacity: 1,

      categoryName: '',

      organizerId: ''

    });

  }


  closeCreateForm(): void {

    this.showCreateForm = false;

    this.eventForm.reset();

  }


  createEvent(): void {

    if (

      this.eventForm.invalid

    ) {

      this.eventForm.markAllAsTouched();

      return;

    }


    const event: CreateEvent =

      this.eventForm.getRawValue();


    this.eventService

      .createEvent(event)

      .subscribe({

        next: (createdEvent) => {

          this.events.update(

            events => [

              createdEvent,

              ...events

            ]

          );


          this.closeCreateForm();

        },

        error: (error) => {

          console.error(

            'Error creating event:',

            error

          );

        }

      });

  }


  deleteEvent(id: string): void {

    this.eventService

      .deleteEvent(id)

      .subscribe({

        next: () => {

          this.events.update(

            events =>

              events.filter(

                event => event.id !== id

              )

          );

        },

        error: (error) => {

          console.error(

            'Error deleting event:',

            error

          );

        }

      });

  }

  loadCategories(): void {

    this.categoryService
        .getCategories()
        .subscribe({

            next: (categories) => {

                this.categories.set(categories);

            },

            error: (error) => {

                console.error(
                    'Error loading categories:',
                    error
                );

            }

        });

}

}