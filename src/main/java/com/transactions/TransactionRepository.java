package com.transactions;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    List<Trans> findAll();
    Optional<Trans> findById(long id);
    Trans save(Trans trans);
}