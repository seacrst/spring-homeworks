package seacrest.bank_app.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import seacrest.bank_app.dto.CustomerDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends AbstractEntity {
  @Column
  public String name;
  @Column
  public String email;
  @Column
  public Integer age;
  
  @ElementCollection
  public Set<AccountEntity> accounts;

  public CustomerEntity(CustomerDto customerDto) {
    name = customerDto.getName();
    email = customerDto.getEmail();
    age = customerDto.getAge();
  }
}
