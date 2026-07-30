import { Component, OnInit, signal } from '@angular/core';

import { StatCardComponent } from '../../../shared/components/stat-card/stat-card';

import { Dashboard } from '../../../core/models/dashboard';

import { DashboardService } from '../../../core/services/dashboard';

import { RecentEvent } from '../../../core/models/event-recent';

import { RecentTicket } from '../../../core/models/ticket-recent';


@Component({
  selector: 'app-dashboard',

  imports: [
    StatCardComponent
  ],

  templateUrl: './dashboard.html',

  styleUrl: './dashboard.css',
})
export class DashboardComponent implements OnInit {


  // Always has an initial value.
  // Therefore the template never reads undefined.
  dashboard = signal<Dashboard>({
    users: 0,
    events: 0,
    categories: 0,
    tickets: 0
  });


  recentEvents = signal<RecentEvent[]>([]);


  recentTickets = signal<RecentTicket[]>([]);


  constructor(
    private dashboardService: DashboardService
  ) {}


  ngOnInit(): void {

    this.loadDashboard();

    this.loadRecentEvents();

    this.loadRecentTickets();

  }


  loadDashboard(): void {

    this.dashboardService
      .getStatistics()
      .subscribe({

        next: (data: Dashboard) => {

          console.log('Dashboard:', data);

          this.dashboard.set(data);

        },

        error: (error) => {

          console.error(
            'Error loading dashboard:',
            error
          );

        }

      });

  }


  loadRecentEvents(): void {

    this.dashboardService
      .getRecentEvents()
      .subscribe({

        next: (events: RecentEvent[]) => {

          console.log(
            'Recent events:',
            events
          );

          this.recentEvents.set(events);

        },

        error: (error) => {

          console.error(
            'Error loading recent events:',
            error
          );

        }

      });

  }


  loadRecentTickets(): void {

    this.dashboardService
      .getRecentTickets()
      .subscribe({

        next: (tickets: RecentTicket[]) => {

          console.log(
            'Recent tickets:',
            tickets
          );

          this.recentTickets.set(tickets);

        },

        error: (error) => {

          console.error(
            'Error loading recent tickets:',
            error
          );

        }

      });

  }

}