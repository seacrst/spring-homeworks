package seacrest.bank_app.services;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import seacrest.bank_app.dto.CurrencyDto;
import seacrest.bank_app.entities.AccountEntity;
import seacrest.bank_app.entities.CustomerEntity;
import seacrest.bank_app.repository.AccountRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository accountRepo;

  public AccountEntity create(CustomerEntity customer, CurrencyDto currency) {
    var account = new AccountEntity(genarateNumber(), currency.getCurrency(), customer.getId());

    account.balance = 0.0;
    return accountRepo.save(account);
  }

  public List<AccountEntity> getAll() {
    return accountRepo.findAll();
  }


  // public List<Account> findAllByCustomerId(Long id) {
  //   return dao.getAccounts()
  //     .values()
  //     .stream()
  //     .filter(ac -> ac.getCustomer().getId().equals(id))
  //     .toList();
  // }


  public boolean remove(AccountEntity account) {
    accountRepo.delete(account);
    return accountRepo.existsById(account.id);
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
