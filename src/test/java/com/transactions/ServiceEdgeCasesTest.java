package com.transactions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceEdgeCasesTest {

    @Test
    void createValueZeroAllowed() {
        InMemoryTransactionRepository repo = new InMemoryTransactionRepository();
        DefaultTransactionService service = new DefaultTransactionService(repo);

        Trans t = new Trans();
        t.setPayeeId(1L);
        t.setPayerId(2L);
        t.setValue(0L);

        Trans created = service.create(t);

        assertNotNull(created.getId());
        assertEquals(0L, created.getValue().longValue());
    }

    @Test
    void createValueNinetyNineAllowed() {
        InMemoryTransactionRepository repo = new InMemoryTransactionRepository();
        DefaultTransactionService service = new DefaultTransactionService(repo);

        Trans t = new Trans();
        t.setPayeeId(1L);
        t.setPayerId(2L);
        t.setValue(99L);

        Trans created = service.create(t);

        assertNotNull(created.getId());
        assertEquals(99L, created.getValue().longValue());
    }
}
