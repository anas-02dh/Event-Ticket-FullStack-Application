import { UserRole } from '../models/user';

export interface LoginResponse {

  accessToken: string;

  tokenType: string;

  userId: string;

  name: string;

  email: string;

  role: UserRole;

}