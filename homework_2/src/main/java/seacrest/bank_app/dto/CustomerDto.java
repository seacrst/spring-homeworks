package seacrest.bank_app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerDto {
  private final String name;
  private final String email;
  private final Integer age;
  
}
