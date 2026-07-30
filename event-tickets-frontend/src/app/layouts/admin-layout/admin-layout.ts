import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AdminNavbarComponent } from '../../shared/components/admin-navbar/admin-navbar';
import { AdminSidebarComponent } from '../../shared/components/admin-sidebar/admin-sidebar';

@Component({
  selector: 'app-admin-layout',
  imports: [RouterOutlet, RouterLink, AdminNavbarComponent, AdminSidebarComponent],
  templateUrl: './admin-layout.html',
  styleUrl: './admin-layout.css',
})
export class AdminLayout {}
