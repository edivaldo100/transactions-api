package com.transactions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class JpaTransactionRepositoryAdapter implements TransactionRepository {

    private final SpringDataTransactionRepository repo;

    public JpaTransactionRepositoryAdapter(SpringDataTransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Trans> findAll() {
        return repo.findAll().stream().map(this::toTrans).collect(Collectors.toList());
    }

    @Override
    public Optional<Trans> findById(long id) {
        return repo.findById(id).map(this::toTrans);
    }

    @Override
    public Trans save(Trans trans) {
        TransactionEntity entity = toEntity(trans);
        TransactionEntity saved = repo.save(entity);
        return toTrans(saved);
    }

    private Trans toTrans(TransactionEntity e) {
        Trans t = new Trans();
        t.setId(e.getId());
        t.setPayeeId(e.getPayeeId());
        t.setPayerId(e.getPayerId());
        t.setTimestamp(e.getTimestamp());
        t.setValue(e.getValue());
        return t;
    }

    private TransactionEntity toEntity(Trans t) {
        TransactionEntity e = new TransactionEntity();
        e.setId(t.getId());
        e.setPayeeId(t.getPayeeId());
        e.setPayerId(t.getPayerId());
        // ensure timestamp is set; if null, use now
        if (t.getTimestamp() == null) {
            e.setTimestamp(java.time.Instant.now());
        } else {
            e.setTimestamp(t.getTimestamp());
        }
        e.setValue(t.getValue());
        return e;
    }
}

