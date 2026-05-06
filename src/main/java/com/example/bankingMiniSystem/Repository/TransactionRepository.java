package com.example.bankingMiniSystem.Repository;

import com.example.bankingMiniSystem.Entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionDetails,Long> {
    List<TransactionDetails> findBySourceAccountOrTargetAccount(String source,String target);
}
