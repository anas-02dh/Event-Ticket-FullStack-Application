enum EventStatus {
    PreSale = "PreSale",
    OnSale = "OnSale",
    SoldOut = "SoldOut",
}

export interface RecentEvent {

  id: string;

  title: string;

  date: string;

  time: string;

  location: string;

  price: number;

  status: EventStatus;

  categoryName: string;

}