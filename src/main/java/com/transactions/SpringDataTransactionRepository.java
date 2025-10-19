package com.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {
}

