package seacrest.app.customer;

import seacrest.app.dto.OwnedAccout;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private static List<Customer> instances = new ArrayList<>();

    private Integer id;
    private String name;
    private String email;
    Integer age;

    public List<OwnedAccout> accounts = new ArrayList<>();

    public Customer(CustomerDto customerDto) {
        this.age = customerDto.getAge();
        this.name = customerDto.getName();
        this.email = customerDto.getEmail();

        if (!instances.contains(this)) {
            instances.add(this);
        }

        id = instances.size();
    }

}
