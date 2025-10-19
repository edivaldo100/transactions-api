package com.transactions;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Trans> storage = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<Trans> findAll() {
        // return an unmodifiable snapshot to preserve encapsulation
        return Collections.unmodifiableList(new ArrayList<>(storage));
    }

    @Override
    public Optional<Trans> findById(long id) {
        return storage.stream()
                .filter(t -> t.getId() != null && t.getId().longValue() == id)
                .findFirst();
    }

    @Override
    public Trans save(Trans trans) {
        if (trans.getId() == null || trans.getId() == 0) {
            trans.setId(idGenerator.incrementAndGet());
        } else {
            // replace existing if present
            storage.removeIf(t -> t.getId() != null && t.getId().equals(trans.getId()));
        }
        storage.add(trans);
        return trans;
    }
}
