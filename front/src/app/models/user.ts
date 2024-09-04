
export interface User {
    id: number;
    name: string;
    surname: string;
    email: string;
    address?: string;
    phoneNumber?: string;
    role: string;
    isActive: boolean;
    accommodationIds: number[];
  }
  