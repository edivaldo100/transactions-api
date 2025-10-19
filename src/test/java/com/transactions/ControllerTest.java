package com.transactions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void getListReturnsOk() throws Exception {
        Trans t = new Trans();
        t.setId(1L);
        t.setPayeeId(10L);
        t.setPayerId(20L);
        t.setValue(50L);
        t.setTimestamp(Instant.now());

        when(transactionService.findAll()).thenReturn(Arrays.asList(t));

        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postInvalidReturnsBadRequest() throws Exception {
        String payload = "{\"payee_id\":10,\"payer_id\":20}"; // missing value

        mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postValidReturnsCreated() throws Exception {
        String payload = "{\"payee_id\":10,\"payer_id\":20,\"value\":50}";

        Trans created = new Trans();
        created.setId(1L);
        created.setPayeeId(10L);
        created.setPayerId(20L);
        created.setValue(50L);
        created.setTimestamp(Instant.now());

        when(transactionService.create(ArgumentMatchers.any(Trans.class))).thenReturn(created);

        mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}

