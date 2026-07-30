export enum EventStatus {
    PreSale = "PreSale",
    OnSale = "OnSale",
    SoldOut = "SoldOut",
}

export interface Event {
  id: string;
  title: string;
  description: string;
  date: string;
  time: string;
  location: string;
  price: number;
  capacity: number;
  remainSeats: number;
  status: EventStatus;
  categoryId: string;
  categoryName: string;
  organizerId: string;
  organizerName: string;
}