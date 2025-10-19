package com.transactions;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Trans {
	
	private Long id;

	@JsonProperty("payee_id")
	private Long payeeId;

	@JsonProperty("payer_id")
	private Long payerId;

	// serialized in UTC as ISO-8601
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private Instant timestamp;

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
	@Override
	public String toString() {
		return "Trans{" +
				"id=" + id +
				", payeeId=" + payeeId +
				", payerId=" + payerId +
				", timestamp=" + timestamp +
				", value=" + value +
				'}';
	}

	
}
