package com.Haborer.Entities;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.omg.CORBA.PUBLIC_MEMBER;
public class Request {

	private RequestStatus status;
	@XmlElement
	public String fromSquadron;
	public String toSquadron;
	public Date fDate;
	public Date tDate;
	public String comments;
	public Item item;
	public String requestRespond;
	
	public Request(RequestStatus status, String fromSquadron, String toSquadron, Date fDate, Date tDate,
			String comments, Item item) {
		super();
		this.status = status;
		this.fromSquadron = fromSquadron;
		this.toSquadron = toSquadron;
		this.fDate = fDate;
		this.tDate = tDate;
		this.comments = comments;
		this.item = item;
		this.requestRespond="";
	}

	public String getRequestRespond() {
		return requestRespond;
	}

	public void setRequestRespond(String requestRespond) {
		this.requestRespond = requestRespond;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public String getFromSquadron() {
		return fromSquadron;
	}

	public void setFromSquadron(String fromSquadron) {
		this.fromSquadron = fromSquadron;
	}

	public String getToSquadron() {
		return toSquadron;
	}

	public void setToSquadron(String toSquadron) {
		this.toSquadron = toSquadron;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Request [status=" + status + ", fromSquadron=" + fromSquadron + ", toSquadron=" + toSquadron
				+ ", fDate=" + fDate + ", tDate=" + tDate + ", comments=" + comments + ", item=" + item
				+ ", requestRespond=" + requestRespond + "]";
	}

	
}
