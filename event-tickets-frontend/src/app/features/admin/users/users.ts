import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  FormsModule
} from '@angular/forms';

import {
  User
} from '../../../core/models/user';

import {
  UserService
} from '../../../core/services/user';


@Component({
  selector: 'app-users',

  imports: [
    FormsModule
  ],

  templateUrl: './users.html',

  styleUrl: './users.css'
})
export class UsersComponent
  implements OnInit {


  users = signal<User[]>([]);


  searchKeyword = signal('');


  constructor(
    private userService: UserService
  ) {}


  ngOnInit(): void {

    this.loadCustomers();

  }


  loadCustomers(): void {

    this.userService
      .getCustomers()
      .subscribe({

        next: (users) => {

          this.users.set(users);

        },

        error: (error) => {

          console.error(
            'Error loading customers:',
            error
          );

        }

      });

  }


  search(): void {

    const keyword =
      this.searchKeyword().trim();


    if (!keyword) {

      this.loadCustomers();

      return;

    }


    this.userService
      .searchUsers(keyword)
      .subscribe({

        next: (users) => {

          const customers =
            users.filter(
              user =>
                user.role === 'CUSTOMER'
            );


          this.users.set(customers);

        },

        error: (error) => {

          console.error(
            'Error searching users:',
            error
          );

        }

      });

  }


  clearSearch(): void {

    this.searchKeyword.set('');

    this.loadCustomers();

  }


  deleteUser(user: User): void {

  this.userService
    .deleteUser(user.id)
    .subscribe({

      next: () => {

        console.log(
          'User deleted successfully'
        );

        this.loadCustomers();

      },

      error: (error) => {

        console.error(
          'Error deleting user:',
          error
        );

      }

    });

}

}