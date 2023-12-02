package seacrest.app.customer;

import seacrest.app.account.Account;
import seacrest.app.account.AccountService;
import seacrest.app.common.Currency;
import seacrest.app.common.CurrencyDto;
import seacrest.app.dao.CustomerDAO;
import seacrest.app.dto.OwnedAccout;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDAO dao;
    private final AccountService accountService;

    public List<Customer> getAll() {
        return dao.findAll();
    }

    public void create(CustomerDto customerDto) {

        Customer customer = new Customer(customerDto);
        dao.save(customer);
    }

    public Optional<Customer> getCustomer(Integer id) {
        Optional<Customer> customer = dao.getOne(id);
        return customer;
    }

    public boolean updateCustomer(CustomerDto customerDto, Integer id) {

        Optional<Customer> сustomer = getCustomer(id);

        if (!сustomer.isPresent()) {
            return false;
        } else {
            Customer сustomerToUpdate = сustomer.get();
            String name = customerDto.getName();
            String email = customerDto.getEmail();
            Integer age = customerDto.getAge();

            if (name != null) {
                сustomerToUpdate.setName(name);
            }

            if (email != null) {
                сustomerToUpdate.setEmail(email);
            }
            
            if (age != null) {
                сustomerToUpdate.setAge(age);
            }
            
            return dao.update(сustomerToUpdate);
        }

    }


    public boolean deleteCustomer(Integer id) {
        Optional<Customer> customer = getCustomer(id);

        return !customer.isPresent() || dao.delete(customer.get());
    }


    public Optional<Customer> createAccount(CurrencyDto currencyDto, Customer customer) {

        Account account = accountService.create(customer, currencyDto.getCurrency());
        
        return Optional.of(account.getCustomer());
    }

    public void deleteAccount(Integer id, Currency currency) {

        List<Account> accounts = accountService.findAllByCustomerId(id).stream()
            .filter(ac -> ac.getCurrency().equals(currency))
            .toList();



        if (accounts.size() == 0) return;

        accounts.stream().forEach(account -> {
            List<OwnedAccout> list = account.getCustomer().accounts.stream().filter(owned -> !account.getId().equals(owned.getId())).toList();
            account.getCustomer().accounts = list;

            accountService.remove(account);
        });
    }

}
