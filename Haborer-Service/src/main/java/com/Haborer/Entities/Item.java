package com.Haborer.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use=Id.CLASS,include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(value=CountItem.class),
	@Type(value=MakatItem.class)
})
public abstract class Item implements Serializable {
   private static final long serialVersionUID = 1L;
   private String _id=new ObjectId().toString();
   private String itemName;
   private String itemCategory;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private Date dateAdded;
   private String squadron;
   private boolean taken=false;
   
   	public boolean isTaken() {
	return taken;
}

public void setTaken(boolean taken) {
	this.taken = taken;
}

	public Item() {
   		
   	}

	public Item(String itemName, String itemCategory,String squadron) {
		super();
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.dateAdded = new Date();
		this.squadron=squadron;
	}

	public Item(String itemName, String itemCategory,String squadron,Date dateAdded) {
		super();
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.dateAdded =dateAdded;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result + ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((squadron == null) ? 0 : squadron.hashCode());
		result = prime * result + (taken ? 1231 : 1237);
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
		Item other = (Item) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (dateAdded == null) {
			if (other.dateAdded != null)
				return false;
		} else if (!dateAdded.equals(other.dateAdded))
			return false;
		if (itemCategory == null) {
			if (other.itemCategory != null)
				return false;
		} else if (!itemCategory.equals(other.itemCategory))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (squadron == null) {
			if (other.squadron != null)
				return false;
		} else if (!squadron.equals(other.squadron))
			return false;
		if (taken != other.taken)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [_id=" + _id + ", itemName=" + itemName + ", itemCategory=" + itemCategory + ", dateAdded="
				+ dateAdded + ", squadron=" + squadron + ", taken=" + taken + "]";
	}

	






   


  
}
