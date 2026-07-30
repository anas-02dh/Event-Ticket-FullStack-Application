import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-navbar',
  imports: [],
  templateUrl: './admin-navbar.html',
  styleUrl: './admin-navbar.css',
})
export class AdminNavbarComponent {
  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  logout() {

    this.authService.logout();

    this.router.navigate(['/login']);

  }
}
