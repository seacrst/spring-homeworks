package seacrest.bank_app.account;
import seacrest.bank_app.common.Currency;
import seacrest.bank_app.customer.Customer;
import seacrest.bank_app.dto.OwnedAccout;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Account {
    private final UUID id;
    private final String number;
    private final Currency currency;

    private Customer customer;
    private Double balance;

    public Account(Currency currency, Customer customer) {
        id = UUID.randomUUID();
        number = AccountService.genarateNumber();
        balance = 0.0;
        this.customer = customer;
        this.currency = currency;

        customer.accounts.add(OwnedAccout.impl(this.number, this.id, currency, this.balance));
    }

}
