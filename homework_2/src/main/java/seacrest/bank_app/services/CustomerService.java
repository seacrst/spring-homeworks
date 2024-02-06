package seacrest.bank_app.services;

import seacrest.bank_app.common.Currency;
import seacrest.bank_app.dto.CurrencyDto;
import seacrest.bank_app.dto.CustomerDto;
import seacrest.bank_app.entities.AccountEntity;
import seacrest.bank_app.entities.CustomerEntity;
import seacrest.bank_app.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AccountService accountService;
    private final CustomerRepository customerRepo;

    public List<CustomerEntity> getAll() {
        return customerRepo.findAll();
    }

    public void create(CustomerDto customerDto) {
        var customer = new CustomerEntity(customerDto);
        customer.accounts = new HashSet<>();
        customerRepo.save(customer);
    }

    public Optional<CustomerEntity> getCustomer(Long id) {
        return customerRepo.findById(id);
    }

    public boolean updateCustomer(CustomerDto customerDto, Long id) {

        Optional<CustomerEntity> сustomer = getCustomer(id);

        if (!сustomer.isPresent()) {
            return false;
        } else {
            CustomerEntity сustomerToUpdate = сustomer.get();
            String name = customerDto.getName();
            String email = customerDto.getEmail();
            Integer age = customerDto.getAge();

            if (name != null) {
                сustomerToUpdate.name = name;
            }

            if (email != null) {
                сustomerToUpdate.email = email;
            }
            
            if (age != null) {
                сustomerToUpdate.age = age;
            }
            сustomerToUpdate.id = id;
            customerRepo.save(сustomerToUpdate);
            Optional<CustomerEntity> cst = getCustomer(id);

            return cst.isPresent() || сustomerToUpdate.equals(cst.get());
            // return dao.update(сustomerToUpdate);
        }

    }


    public boolean deleteCustomer(Long id) {
        customerRepo.deleteById(id);
        return customerRepo.existsById(id);
    }


    public AccountEntity createAccount(CurrencyDto currencyDto, CustomerEntity customer) {
        var account = accountService.create(customer, currencyDto);
        customer.accounts.add(account);
        customerRepo.save(customer);
        return account;
    }

    public boolean deleteAccount(Long id, Currency currency) {

        Optional<CustomerEntity> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            return false;
        }
        
        List<AccountEntity> accounts = accountService.getAll();
        
        Optional<AccountEntity> ac = accounts.stream().filter(a -> a.ownerId.equals(id) && a.currency.equals(currency)).findFirst();
        if (ac.isPresent()) {
            customer.get().accounts.remove(ac.get());
            customerRepo.save(customer.get());
            return accountService.remove(ac.get());
        } else {
            return false;
        }

    }

}
