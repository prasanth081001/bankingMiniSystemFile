package com.example.bankingMiniSystem.Repository;

import com.example.bankingMiniSystem.Entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountDetails,Long> {
    Optional<AccountDetails> findByAccountNumber(String accountNumber);
}
