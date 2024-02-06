package seacrest.bank_app.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import seacrest.bank_app.common.Currency;
import seacrest.bank_app.dto.CurrencyDto;
import seacrest.bank_app.dto.CustomerDto;
import seacrest.bank_app.entities.CustomerEntity;
import seacrest.bank_app.services.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers")
    public List<CustomerEntity> getAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCustomer(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
    }

    @GetMapping("customers/{id}")
    public Optional<CustomerEntity> getCustomer(@PathVariable("id") String id, HttpServletResponse res) {
        Optional<CustomerEntity> customer = customerService.getCustomer(Long.parseLong(id));

        if (!customer.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return customer;
        }

        return customer;
    }

    @PatchMapping("customers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Boolean> updateCustomer(@PathVariable("id") String id, @RequestBody CustomerDto customerDto,  HttpServletResponse response) {
        boolean rsl = customerService.updateCustomer(customerDto, Long.parseLong(id));

        response.setStatus(rsl ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_NOT_MODIFIED);

        Map<String, Boolean> json = new HashMap<>();
        json.put("result", rsl);
        
        return json;
    }

    @DeleteMapping("customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable("id") String id) {

        boolean rsl = customerService.deleteCustomer(Long.parseLong(id));

        Map<String, Boolean> json = new HashMap<>();
        json.put("result", rsl);

        return json;
    }


    @PostMapping("customers/{id}/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<CustomerEntity> createAccountForOneCustomer(@RequestBody CurrencyDto currencyDto, @PathVariable("id") String id, HttpServletResponse res) {
        Optional<CustomerEntity> customer = customerService.getCustomer(Long.parseLong(id));

        if (!customer.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return customer;
        } else {
            customerService.createAccount(currencyDto, customer.get());
            return customerService.getCustomer(Long.parseLong(id));
        }
    }

    @DeleteMapping("customers/{id}/account")
    public Optional<CustomerEntity> deleteAccountForOneCustomer(@PathVariable("id") String id, @RequestParam() String currency, HttpServletResponse res) {
        Optional<CustomerEntity> customer = customerService.getCustomer(Long.parseLong(id));

        if (!customer.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return customer;
        }

        try {
            Currency currencyParam = Currency.valueOf(currency.toUpperCase());
            customerService.deleteAccount(Long.parseLong(id), currencyParam);
        } catch(IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Optional.empty();
        }
        

        return customer;
    }


}
