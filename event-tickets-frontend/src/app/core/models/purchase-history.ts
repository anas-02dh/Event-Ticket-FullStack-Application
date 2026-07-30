import { Ticket, TicketStatus } from './ticket';


export interface PurchaseHistory {

  userId: string;

  userName: string;

  eventId: string;

  eventTitle: string;

  ticketDTOS: Ticket[];

  purchaseDate: string;

  totalPrice: number;

  status: TicketStatus;

}