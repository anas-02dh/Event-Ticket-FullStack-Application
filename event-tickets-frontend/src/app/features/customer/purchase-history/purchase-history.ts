import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  Ticket
} from '../../../core/models/ticket';

import {
  TicketService
} from '../../../core/services/ticket';
import { AuthService } from '../../../core/services/auth-service';



@Component({

  selector: 'app-purchase-history',

  imports: [],

  templateUrl: './purchase-history.html',

  styleUrl: './purchase-history.css'

})
export class PurchaseHistoryComponent
  implements OnInit {


  tickets =
    signal<Ticket[]>([]);


  loading =
    signal<boolean>(false);


  errorMessage =
    signal<string>('');


  constructor(

    private ticketService:
      TicketService,

    private authService:
      AuthService

  ) {}


  ngOnInit(): void {

    this.loadPurchaseHistory();

  }


  loadPurchaseHistory(): void {

    const userId =
      this.authService.getUserId();


    if (!userId) {

      this.errorMessage.set(
        'User not found.'
      );

      return;

    }


    this.loading.set(true);


    this.ticketService

      .getPurchaseHistory(userId)

      .subscribe({

        next: (purchaseHistory) => {

          this.tickets.set(
            purchaseHistory.ticketDTOS
          );


          this.loading.set(false);

        },


        error: (error) => {

          console.error(
            'Error loading purchase history:',
            error
          );


          this.errorMessage.set(
            'Unable to load purchase history.'
          );


          this.loading.set(false);

        }

      });

  }

}