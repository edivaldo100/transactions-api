package com.transactions;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create a transaction")
public class TransRequest {

    @NotNull
    @Schema(description = "ID of the payee (receiver)", example = "10")
    private Long payee_id;

    @NotNull
    @Schema(description = "ID of the payer (sender)", example = "20")
    private Long payer_id;

    @NotNull
    @Min(0)
    @Max(99)
    @Schema(description = "Transaction amount. Must be between 0 and 99 inclusive.", example = "50")
    private Long value;

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public Long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(Long payer_id) {
        this.payer_id = payer_id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
