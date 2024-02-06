package seacrest.bank_app.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class EmployerEntity extends AbstractEntity {
  public String name;
  public String address;

  @ManyToMany
    @JoinTable(
        name = "employer_company",
        joinColumns = @JoinColumn(name = "employer_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private Set<CompanyEntity> companies = new HashSet<>();
}
