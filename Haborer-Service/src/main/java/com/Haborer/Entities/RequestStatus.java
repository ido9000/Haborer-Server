package com.Haborer.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestStatus {
	@JsonProperty("PENDING")
	PENDING,
	@JsonProperty("APPROVED")
	APPROVED,
	@JsonProperty("DECLINED")
	DECLINED,
	@JsonProperty("TAKEN")
	TAKEN,
	@JsonProperty("RETURNED")
	RETURNED,
	@JsonProperty("CANCELD")
	CANCELD

	

}
