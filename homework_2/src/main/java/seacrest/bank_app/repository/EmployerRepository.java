package seacrest.bank_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import seacrest.bank_app.entities.EmployerEntity;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {
  
}
