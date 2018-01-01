package com.Haborer.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RequestStatus {
	PENDING,
	APPROVED,
	DECLINED,
	TAKEN,
	RETURNED,
	CANCELD

}
