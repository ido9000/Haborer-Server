
package com.Haborer.Entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CountItem extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int itemCount;

	public CountItem () {
		
	}
	
	
	public CountItem(String itemName, String itemCategory,String squadron,int itemCount) {
		super( itemName, itemCategory,squadron);
		this.itemCount=itemCount;
	}
	@JsonCreator
	public CountItem(@JsonProperty("itemName") String itemName,@JsonProperty("itemCategory") String itemCategory,@JsonProperty("squadron") String squadron,@JsonProperty("itemCount") int itemCount,@JsonProperty("dateAdded")Date dateAdded) {
		super( itemName, itemCategory,squadron,dateAdded);
		this.itemCount=itemCount;
	}

	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}


	@Override
	public String toString() {
		return "CountItem [itemCount=" + itemCount + ", toString()=" + super.toString() + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + itemCount;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountItem other = (CountItem) obj;
		if (itemCount != other.itemCount)
			return false;
		return true;
	}


	



	
}
