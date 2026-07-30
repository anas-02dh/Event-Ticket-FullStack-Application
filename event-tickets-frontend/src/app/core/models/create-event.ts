import { User } from "./user";

export interface CreateEvent {
  title: string;
  description: string;
  date: string;
  time: string;
  location: string;
  price: number;
  capacity: number;
  categoryName: string;
  organizerId: string;
}