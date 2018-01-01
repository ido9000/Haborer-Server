package com.Haborer.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
public abstract class Item implements Serializable {
   private static final long serialVersionUID = 1L;
   private ObjectId itemId=new ObjectId();
   private String itemName;
   private String itemCategory;
   private Date dateAdded;
   private String squadron;
   
   	public Item() {
   		
   	}

	public Item(String itemName, String itemCategory,String squadron) {
		super();
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.dateAdded = new Date();
		this.squadron=squadron;
	}

	public String getSquadron() {
		return squadron;
	}

	public void setSquadron(String squadron) {
		this.squadron = squadron;
	}

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemCategory=" + itemCategory + ", dateAdded="
				+ dateAdded + ", squadron=" + squadron + "]";
	}




   


  
}
