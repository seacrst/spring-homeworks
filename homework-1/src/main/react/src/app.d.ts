type Currency = "USD" | "EUR" | "UAH" | "CHF" | "GBP"

interface Customer {
  name: string;
  email: string;
  age: number;
  id: number
}

interface Account {
  number: string;
  id: string;
  balance: number;
  currency: Currency
}

interface Customer {
  name: string;
  email: string;
  age: number;
  accounts: Account[];
}

interface CustomerDto {
  name: string;
  email: string;
  age: number;
}

interface ModalData {
  title: string;
  description?: string;
  fields: Array<{label: string | number, type: "text" | "email" | "number", field: "name" | "email" | "age" | "currency"}>;
  action: "Зареєструватися" | "Зберегти" | "Створити" | "Видалити"
}


type modalType = "create_customer" | "update_customer" | "add_account" | "del_account";


enum ModalType {
  CREATE_CUSTOMER = "CREATE_CUSTOMER",
  EDIT_CUSTOMER = "EDIT_CUSTOMER"
}