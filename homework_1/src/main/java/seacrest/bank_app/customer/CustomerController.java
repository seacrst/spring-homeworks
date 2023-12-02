package seacrest.bank_app.customer;

import jakarta.servlet.http.HttpServletResponse;

import seacrest.bank_app.common.Currency;
import seacrest.bank_app.common.CurrencyDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCustomer(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
    }

    @GetMapping("customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") String id, HttpServletResponse res) {
        Optional<Customer> customer = customerService.getCustomer(Integer.parseInt(id));

        if (!customer.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return customer;
        }

        return customer;
    }

    @PatchMapping("customers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Boolean> updateCustomer(@PathVariable("id") String id, @RequestBody CustomerDto customerDto,  HttpServletResponse response) {
        boolean rsl = customerService.updateCustomer(customerDto, Integer.parseInt(id));

        response.setStatus(rsl ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_NOT_MODIFIED);

        Map<String, Boolean> json = new HashMap<>();
        json.put("result", rsl);
        
        return json;
    }

    @DeleteMapping("customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable("id") String id) {

        boolean rsl = customerService.deleteCustomer(Integer.parseInt(id));

        Map<String, Boolean> json = new HashMap<>();
        json.put("result", rsl);

        return json;

    }


    @PostMapping("customers/{id}/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Customer> createAccountForOneCustomer(@RequestBody CurrencyDto currencyDto, @PathVariable("id") String id, HttpServletResponse res) {


        Optional<Customer> customer = customerService.getCustomer(Integer.parseInt(id));

        if (!customer.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return customer;
        } else {
            return customerService.createAccount(currencyDto, customer.get());
        }
    }

    @DeleteMapping("customers/{id}/account")
    public Optional<Customer> deleteAccountForOneCustomer(@PathVariable("id") String id, @RequestParam() String currency, HttpServletResponse res) {
        Optional<Customer> customer = customerService.getCustomer(Integer.parseInt(id));

        if (!customer.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return customer;
        }

        try {
            Currency currencyParam = Currency.valueOf(currency.toUpperCase());
            customerService.deleteAccount(Integer.parseInt(id), currencyParam);
        } catch(IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Optional.empty();
        }
        

        return customer;
    }


}
