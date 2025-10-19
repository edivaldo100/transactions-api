package com.transactions;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Transaction response returned by the API")
public class TransResponse {

    @Schema(description = "Transaction id", example = "1")
    private Long id;

    @JsonProperty("payee_id")
    @Schema(description = "ID of the payee (receiver)", example = "10")
    private Long payeeId;

    @JsonProperty("payer_id")
    @Schema(description = "ID of the payer (sender)", example = "20")
    private Long payerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @Schema(description = "Timestamp (UTC)", example = "2023-01-01T00:00:00Z")
    private Instant timestamp;

    @Schema(description = "Transaction amount", example = "50")
    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
