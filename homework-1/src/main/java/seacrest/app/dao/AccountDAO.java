package seacrest.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import lombok.Data;

import seacrest.app.account.Account;
import seacrest.app.common.Currency;
import seacrest.app.customer.Customer;

@Repository
@Data
public class AccountDAO implements DAO<Account>{
  private final Map<UUID, Account> accounts = new HashMap<>();

  @Override
  public boolean delete(Account obj) {
    accounts.remove(obj.getId(), obj);
    return accounts.containsKey(obj.getId());
  }

  @Override
  public void deleteAll(List<Account> entities) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
  }

  @Override
  public void saveAll(List<Account> entities) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
  }

  @Override
  public List<Account> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public boolean deleteById(long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public Account getOne(long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOne'");
  }

  @Override
  public Account getOne(UUID id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOne'");
  }

  @Override
  public Optional<Account> getOne(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOne'");
  }

  @Override
  public Account save(Account obj) {
    accounts.put(obj.getId(), obj);
    return obj;
  }

  public Account create(Currency currency, Customer customer) {
    Account account = new Account(currency, customer);

    return account;
  }

}
