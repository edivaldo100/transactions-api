package com.transactions;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultTransactionService implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTransactionService.class);

    private final TransactionRepository repository;

    public DefaultTransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Trans> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Trans> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Trans create(Trans trans) {
        // business rule: value must be less than 100
        if (trans.getValue() == null || trans.getValue() >= 100) {
            logger.warn("Transaction value not allowed: {}", trans.getValue());
            throw new TransactionValidationException("401", "Transação não autorizada, pois o valor não pode ser superior há 99");
        }

        // set timestamp in UTC using Instant
        Instant now = Instant.now();
        trans.setTimestamp(now);

        Trans created = repository.save(trans);
        logger.info("Created transaction with id={}", created.getId());
        return created;
    }
}
