package seacrest.bank_app.account;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import seacrest.bank_app.common.Currency;
import seacrest.bank_app.customer.Customer;
import seacrest.bank_app.dao.AccountDAO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountDAO dao;

  public Account create(Customer customer, Currency currency) {
    Account account = dao.create(currency, customer);

    return dao.save(account);
  }

  public List<Account> getAll() {
    return dao.getAccounts().values().stream().toList();
  }


  public List<Account> findAllByCustomerId(Integer id) {
    return dao.getAccounts()
      .values()
      .stream()
      .filter(ac -> ac.getCustomer().getId().equals(id))
      .toList();
  }


  public void remove(Account account) {
    dao.delete(account);
  }


  public static String genarateNumber() {
    String prefix = "UA20";
    String zeros = "00000";

    Function<Integer, String> takeNumToStr = (len) -> {
      String[] arrlen = new String[len];

      for(int i = 0; i < len; i++) {
        Double n = Math.random() * 10;
        String number = Integer.toString(n.intValue());
        arrlen[i] = number;
      }

      return Arrays.stream(arrlen)
        .reduce("", (s, n) -> s + n);
    };

    return prefix + takeNumToStr.apply(6) + zeros + takeNumToStr.apply(14);
  }
}
