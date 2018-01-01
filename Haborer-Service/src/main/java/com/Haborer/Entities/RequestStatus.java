package com.Haborer.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RequestStatus {
	PENDING("PENDING"),
	APPROVED("APPROVED"),
	DECLINED("DECLINED"),
	TAKEN("TAKEN"),
	RETURNED("RETURNED"),
	CANCELD("CANCELD");
	
	private String status;
	private RequestStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
