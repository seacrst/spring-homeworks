
export class CustomerService {
  private readonly allCustomers = "http://localhost:9000/api/customers";
  private readonly newCustomers = "http://localhost:9000/api/customer";

  private repo: {customers: Customer[]} = {
    customers: []
  }

  set putCustomer(list: Customer[]) {
    this.repo.customers = [...this.repo.customers, ...list];
  }


  async getAllCustomers(): Promise<Customer[]> {
      const res = await fetch(this.allCustomers);
      return await res.json();
  }


  async addNewCustomer(name: string, age: number, email: string) {
    try {

      const res = await fetch(this.newCustomers, {
        method: "POST", 
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }, 
        body: JSON.stringify({name, age, email})
      });

      if (res.status !== 201) {
        throw new Error("New customer has not been created");
      }
      
    } catch (err) {
      console.error(err);
    }
  }


  async getUserById(id: number | string): Promise<Customer> {
      const res = await fetch(`${this.allCustomers}/${id}`);
      return res.json();
  }

  async updateCustomer(customer: Customer & {id: number}) {
    const res = await fetch(`${this.allCustomers}/${customer.id}`, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: "PATCH",
      body: JSON.stringify({...this.repo.customers.find(({id}) => id === customer.id), customer})
    });

    return res.json();
  }
}
