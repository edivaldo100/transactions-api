package com.transactions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class DefaultTransactionServiceTest {

    @Test
    void createValidTransaction() {
        InMemoryTransactionRepository repo = new InMemoryTransactionRepository();
        DefaultTransactionService service = new DefaultTransactionService(repo);

        Trans t = new Trans();
        t.setPayeeId(10L);
        t.setPayerId(20L);
        t.setValue(50L);

        Trans created = service.create(t);

        assertNotNull(created.getId());
        assertNotNull(created.getTimestamp());

        List<Trans> all = service.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void createInvalidTransactionThrows() {
        InMemoryTransactionRepository repo = new InMemoryTransactionRepository();
        DefaultTransactionService service = new DefaultTransactionService(repo);

        Trans t = new Trans();
        t.setPayeeId(10L);
        t.setPayerId(20L);
        t.setValue(100L); // invalid

        assertThrows(TransactionValidationException.class, () -> service.create(t));
    }
}
