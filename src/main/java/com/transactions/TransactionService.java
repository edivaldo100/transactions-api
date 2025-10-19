package com.transactions;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Trans> findAll();
    Optional<Trans> findById(long id);
    Trans create(Trans trans);
}
