
package com.Haborer.Entities;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
public class ItemRequestsFactory {
	public String fromSquadron;
    @Override
	public String toString() {
		return "ItemRequestsFactory [fromSquadron=" + fromSquadron + ", toSquadron=" + toSquadron + ", fDate=" + fDate
				+ ", tDate=" + tDate + ", items=" + items + ", comments=" + comments + "]";
	}

	public String toSquadron;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date fDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date tDate;
	public ArrayList<Item> items=new ArrayList<>();
	public String comments;
	
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
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ItemRequestsFactory(String fromSquadron, String toSquadron, Date fDate, Date tDate, ArrayList<Item> items,
			String comments) {
		super();
		this.fromSquadron = fromSquadron;
		this.toSquadron = toSquadron;
		this.fDate = fDate;
		this.tDate = tDate;
		this.items = items;
		this.comments = comments;
	}
	
	public ItemRequestsFactory() {
	}
	public ArrayList<Request> manageRequests(){
		ArrayList<Request> requests=new ArrayList<>();
		items.forEach(item->{
			requests.add(new Request(RequestStatus.PENDING, fromSquadron, toSquadron, fDate, tDate, comments,item));
		});
		return requests;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((fDate == null) ? 0 : fDate.hashCode());
		result = prime * result + ((fromSquadron == null) ? 0 : fromSquadron.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		ItemRequestsFactory other = (ItemRequestsFactory) obj;
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
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
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
