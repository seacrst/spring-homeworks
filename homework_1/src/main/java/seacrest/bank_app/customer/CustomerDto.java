package seacrest.bank_app.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {

  private final String name;
  private final String email;
  private final Integer age;
  
}
