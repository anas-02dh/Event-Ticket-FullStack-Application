export enum TicketStatus {

    RESERVED = 'RESERVED',
    PENDING = 'PENDING',
    CONFIRMED = 'CONFIRMED',
    USED = 'USED',
    CANCELLED = 'CANCELLED',
    EXPIRED = 'EXPIRED',
    REFUNDED = 'REFUNDED'

}

export interface Ticket {

  id: string;

  purchaseDate: string;

  quantity: number;

  totalPrice: number;

  status: TicketStatus;

  userId: string;

  userName: string;

  eventId: string;

  eventTitle: string;

}