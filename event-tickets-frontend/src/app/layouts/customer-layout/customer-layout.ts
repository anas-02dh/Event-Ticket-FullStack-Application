import {
  Component
} from '@angular/core';

import {
  RouterOutlet,
  RouterLink
} from '@angular/router';


@Component({

  selector: 'app-customer-layout',

  imports: [

    RouterOutlet,

    RouterLink

  ],

  templateUrl:
    './customer-layout.html',

  styleUrl:
    './customer-layout.css'

})
export class CustomerLayout {


  logout(): void {

    localStorage.removeItem(
      'token'
    );

    localStorage.removeItem(
      'user'
    );

    window.location.href =
      '/login';

  }

}