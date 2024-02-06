package seacrest.bank_app.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class CompanyEntity extends AbstractEntity {
  @ManyToMany
  @JoinTable(
        name = "company_employer",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "employer_id")
    )
  public Set<EmployerEntity> employers = new HashSet<>();
}
