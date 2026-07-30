import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login';
import { RegisterComponent } from './features/auth/register/register';
import { AdminLayout } from './layouts/admin-layout/admin-layout';

import { authGuard } from './core/guards/auth-guard';
import { adminGuard } from './core/guards/admin-guard';
import { DashboardComponent } from './features/admin/dashboard/dashboard';

import { UsersComponent } from './features/admin/users/users';
import { EventsComponent } from './features/admin/events/events';
import { CategoryComponent } from './features/admin/category/category';
import { TicketComponent } from './features/admin/ticket/ticket';

import { CustomerLayout } from './layouts/customer-layout/customer-layout';

import { CustomerEventsComponent }
  from './features/customer/events/events';

import { EventDetailsComponent }
  from './features/customer/event-details/event-details';

import { PurchaseHistoryComponent }
  from './features/customer/purchase-history/purchase-history';

export const routes: Routes = [
    {path:'', redirectTo:'login', pathMatch:'full'},
    {path:'login',  component:LoginComponent},
    {path:'register',  component:RegisterComponent},
    {
        path:'admin',  
        component:AdminLayout,
        canActivate:[authGuard,adminGuard],
        children:[
            {
                path:'dashboard',
                component:DashboardComponent
            },
            {
                path: 'users',
                component: UsersComponent
            },
            {
                path: 'events',
                component: EventsComponent
            },

            {
                path: 'categories',
                component: CategoryComponent
            },

            {
                path: 'tickets',
                component: TicketComponent
            },
            {
                path:'',
                redirectTo:'dashboard',
                pathMatch:'full'
            }
        ]
    },

    {
    path: 'customer',

    component: CustomerLayout,

    canActivate: [authGuard],

    children: [

        {
            path: 'events',

            component: CustomerEventsComponent
        },

        {
            path: 'events/:id',

            component: EventDetailsComponent
        },

        {
            path: 'history',

            component: PurchaseHistoryComponent
        },

        {
            path: '',

            redirectTo: 'events',

            pathMatch: 'full'
        }

        ]

    },

    {path:'**', redirectTo:'login'},
];
