package com.Haborer.Entities;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.omg.CORBA.PUBLIC_MEMBER;
public class Request {
	private String _id=new ObjectId().toString();
	private RequestStatus status;
	public String fromSquadron;
	public String toSquadron;
	public Date fDate;
	public Date tDate;
	public String comments;
	public Item item;
	public String requestRespond;
	
	public Request() {
		
	}
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	@Override
	public String toString() {
		return "Request [_id=" + _id + ", status=" + status + ", fromSquadron=" + fromSquadron + ", toSquadron="
				+ toSquadron + ", fDate=" + fDate + ", tDate=" + tDate + ", comments=" + comments + ", item=" + item
				+ ", requestRespond=" + requestRespond + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((fDate == null) ? 0 : fDate.hashCode());
		result = prime * result + ((fromSquadron == null) ? 0 : fromSquadron.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((requestRespond == null) ? 0 : requestRespond.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tDate == null) ? 0 : tDate.hashCode());
		result = prime * result + ((toSquadron == null) ? 0 : toSquadron.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (fDate == null) {
			if (other.fDate != null)
				return false;
		} else if (!fDate.equals(other.fDate))
			return false;
		if (fromSquadron == null) {
			if (other.fromSquadron != null)
				return false;
		} else if (!fromSquadron.equals(other.fromSquadron))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (requestRespond == null) {
			if (other.requestRespond != null)
				return false;
		} else if (!requestRespond.equals(other.requestRespond))
			return false;
		if (status != other.status)
			return false;
		if (tDate == null) {
			if (other.tDate != null)
				return false;
		} else if (!tDate.equals(other.tDate))
			return false;
		if (toSquadron == null) {
			if (other.toSquadron != null)
				return false;
		} else if (!toSquadron.equals(other.toSquadron))
			return false;
		return true;
	}



	
}
