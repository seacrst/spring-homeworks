package seacrest.bank_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import seacrest.bank_app.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


}
