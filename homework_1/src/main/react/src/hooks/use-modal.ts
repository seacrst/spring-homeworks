import { CustomerService } from "../services/customers";

export class ModalState {

  static customerService: CustomerService = new CustomerService();

  private static customerDto: CustomerDto = {
    age: 0,
    email: "",
    name: ""
  }

  static save(data: CustomerDto) {
    ModalState.customerDto = data;
  }

  static async load(id: number) {

    return await this.customerService.getUserById(id).then(res => {
        this.customerDto.age = res.age;
        this.customerDto.email = res.email;
        this.customerDto.name = res.name;
    });
  }

  static use(type: modalType): ModalData {
    switch(type) {
      case "create_customer": return ModalState.addCustomer();
      case "update_customer": return ModalState.updateCustomer(ModalState.customerDto);
      case "add_account" : return ModalState.addAccount()
      case "del_account" : return ModalState.removeAccount()
    }
  }

  static updateCustomer({age, email, name}: CustomerDto): ModalData {
    return {
      title: "Змінити дані",
      description: "Після оновлення збережіть дані",
      fields: [
        {
          field: "name",
          label: name || "Ім'я",
          type: "text"
        },
        {
          field: "email",
          label: email || "Пошта",
          type: "email"
        },
        {
          field: "age",
          label: age ? age.toString() : "Вік",
          type: "number"
        }
      ],
      action: "Зберегти"
    }
  }


  static addCustomer(): ModalData {
    return {
      title: "Реєстрація",
      description: "Для реєстрації нового клієнта введіть ім'я, електронну пошту та вік.",
      fields: [
        {
          field: "name",
          label: "Ім'я",
          type: "text"
        },
        {
          field: "email",
          label: "Пошта",
          type: "email"
        },
        {
          field: "age",
          label: "Вік",
          type: "number"
        }
      ],
      action: "Зареєструватися"
    }
  }

  static addAccount(): ModalData {
    return {
      title: "Створити рахунок",
      description: "Виберіть валюту для рахунку",
      fields: [
        {
          field: "currency",
          label: "Валюта",
          type: "text"
        }
      ],
      action: "Створити"  
    }
  }


  static removeAccount(): ModalData {
    return {
      title: "Видалити рахунок",
      description: "Виберіть валюту для рахунку",
      fields: [
        {
          field: "currency",
          label: "Валюта",
          type: "text"
        }
      ],
      action: "Видалити"  
    }
  }

  static get values(): CustomerDto {
    return {
      age: ModalState.customerDto.age,
      email: ModalState.customerDto.email,
      name: ModalState.customerDto.name
    }
  }

}

