package com.transactions;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class DefaultTransactionServiceTest {

    @Test
    public void createValidTransaction() {
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

    @Test(expected = TransactionValidationException.class)
    public void createInvalidTransactionThrows() {
        InMemoryTransactionRepository repo = new InMemoryTransactionRepository();
        DefaultTransactionService service = new DefaultTransactionService(repo);

        Trans t = new Trans();
        t.setPayeeId(10L);
        t.setPayerId(20L);
        t.setValue(100L); // invalid

        service.create(t);
    }
}

