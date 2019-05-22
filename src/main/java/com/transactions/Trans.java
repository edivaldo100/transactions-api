package com.transactions;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Trans {
	
	private Long id;
	private Long payee_id;
	private Long payer_id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="PST")
	private Timestamp timestamp;
	
	private Long value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	
}
