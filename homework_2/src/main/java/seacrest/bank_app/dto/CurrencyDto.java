package seacrest.bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import seacrest.bank_app.common.Currency;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
  private Currency currency;
}
