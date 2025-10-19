package com.transactions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postThenGetReturnsCreatedAndFound() {
        String base = "http://localhost:" + port + "/transactions";

        String payload = "{\"payee_id\":101,\"payer_id\":202,\"value\":42}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<TransResponse> postResp = restTemplate.postForEntity(base, new HttpEntity<>(payload, headers), TransResponse.class);
        assertThat(postResp.getStatusCodeValue()).isEqualTo(201);
        assertThat(postResp.getBody()).isNotNull();
        Long id = postResp.getBody().getId();
        assertThat(id).isNotNull();

        // fetch by id
        ResponseEntity<TransResponse> getResp = restTemplate.getForEntity(base + "/" + id, TransResponse.class);
        assertThat(getResp.getStatusCodeValue()).isEqualTo(200);
        TransResponse body = getResp.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getPayeeId()).isEqualTo(101L);
        assertThat(body.getPayerId()).isEqualTo(202L);
        assertThat(body.getValue()).isEqualTo(42L);
    }
}

