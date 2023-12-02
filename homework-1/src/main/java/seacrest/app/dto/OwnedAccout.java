package seacrest.app.dto;

import java.util.UUID;

import seacrest.app.common.Currency;

import lombok.Getter;

@Getter
public class OwnedAccout {
  private final String number;
  private final UUID id;
  private final Currency currency;
  private final Double balance;

  private OwnedAccout(String number, UUID id, Currency currency, Double bal) {
    this.number = number;
    this.id = id;
    this.currency = currency;
    balance = bal;
  }

  public static OwnedAccout impl(String number, UUID id, Currency currency, Double balance) {
    return new OwnedAccout(number, id, currency, balance);
  }
}
