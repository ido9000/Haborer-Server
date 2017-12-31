
package com.Haborer.Entities;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "factory") 
public class ItemRequestsFactory {
    @XmlElement
	public String fromSquadron;
    @Override
	public String toString() {
		return "ItemRequestsFactory [fromSquadron=" + fromSquadron + ", toSquadron=" + toSquadron + ", fDate=" + fDate
				+ ", tDate=" + tDate + ", items=" + items + ", comments=" + comments + "]";
	}

	@XmlElement
	public String toSquadron;
    @XmlElement
	public Date fDate;
    @XmlElement
	public Date tDate;
    @XmlElement
	public ArrayList<? extends Item> items=new ArrayList<>();
    @XmlElement
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
	public ArrayList<? extends Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<? extends Item> items) {
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
	
	public ArrayList<Request> manageRequests(){
		ArrayList<Request> requests=new ArrayList<>();
		items.forEach(item->{
			requests.add(new Request(RequestStatus.PENDING, fromSquadron, toSquadron, fDate, tDate, comments,item));
		});
		return requests;
	}
	
	

}
