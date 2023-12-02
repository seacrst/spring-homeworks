package seacrest.app.dao;

import seacrest.app.customer.Customer;
import org.springframework.stereotype.Repository;

import lombok.Data;

import java.util.*;

@Repository
@Data
public class CustomerDAO implements DAO<Customer>{

    private final Map<Integer, Customer> customers = new HashMap<>();

    @Override
    public Customer save(Customer obj) {
        if (customers.containsValue(obj)) {
            return getOne(obj.getId()).get();
        }

        customers.put(obj.getId(), obj);
        return getOne(obj.getId()).get();
    }

    public boolean update(Customer customer) {

        customers.put(customer.getId(), customer);

        return true;
    }

    @Override
    public boolean delete(Customer obj) {
        customers.remove(obj.getId(), obj);

        return !customers.containsValue(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {

    }

    @Override
    public void saveAll(List<Customer> entities) {

    }

    @Override
    public List<Customer> findAll() {
        return customers.values().stream().toList();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Customer getOne(long id) {
        return null;
    }

    @Override
    public Optional<Customer> getOne(int id) {
        Customer customer = customers.get(id);

        return customer == null ? Optional.empty() : Optional.of(customer);
    }

    @Override
    public Customer getOne(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }
}
