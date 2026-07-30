enum TicketStatus {
    RESERVED= "RESERVED",
    PENDING= "PENDING",
    CONFIRMED= "CONFIRMED",
    USED= "USED",
    CANCELLED= "CANCELLED",
    EXPIRED= "EXPIRED",
    REFUNDED= "REFUNDED"
}

export interface RecentTicket {

  id: string;

  buyerName: string;

  eventTitle: string;

  quantity: number;

  totalPrice: number;

  purchaseDate: string;

  status: TicketStatus;

}