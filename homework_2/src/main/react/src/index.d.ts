declare type Currency = "USD" | "EUR" | "UAH" | "CHF" | "GBP"

declare interface Customer {
  name: string;
  email: string;
  age: number;
  id: number
}

declare interface Account {
  number: string;
  id: string;
  balance: number;
  currency: Currency
}

declare interface Customer {
  name: string;
  email: string;
  age: number;
  accounts: Account[];
}

declare interface CustomerDto {
  name: string;
  email: string;
  age: number;
}

declare interface ModalData {
  title: string;
  description?: string;
  fields: Array<{label: string | number, type: "text" | "email" | "number", field: "name" | "email" | "age" | "currency"}>;
  action: "Зареєструватися" | "Зберегти" | "Створити" | "Видалити"
}


declare type modalType = "create_customer" | "update_customer" | "add_account" | "del_account";


declare enum ModalType {
  CREATE_CUSTOMER = "CREATE_CUSTOMER",
  EDIT_CUSTOMER = "EDIT_CUSTOMER"
}