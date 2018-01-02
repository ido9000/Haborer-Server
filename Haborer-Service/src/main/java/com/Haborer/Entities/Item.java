package com.Haborer.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class Item implements Serializable {
   private static final long serialVersionUID = 1L;
   private String _id=new ObjectId().toString();
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	@Override
	public String toString() {
		return "Item [_id=" + _id + ", itemName=" + itemName + ", itemCategory=" + itemCategory + ", dateAdded="
				+ dateAdded + ", squadron=" + squadron + "]";
	}







   


  
}
