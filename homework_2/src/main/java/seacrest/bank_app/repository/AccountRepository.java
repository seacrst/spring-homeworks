package seacrest.bank_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import seacrest.bank_app.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
  
}
