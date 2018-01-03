
package com.Haborer.Entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MakatItem extends Item{
	private String itemMakat;
	
	public MakatItem() {
	}
	
	public MakatItem( String itemName, String itemCategory,String squadron,String itemMakat) {
		super(itemName, itemCategory,squadron);
		this.itemMakat=itemMakat;
	}
	@JsonCreator
	public MakatItem( @JsonProperty("itemName") String itemName,@JsonProperty("itemCategory") String itemCategory,@JsonProperty("squadron") String squadron,@JsonProperty("itemMakat") String itemMakat,@JsonProperty("dateAdded")Date dateAdded) {
		super(itemName, itemCategory,squadron,dateAdded);
		this.itemMakat=itemMakat;
	}

	public String getItemMakat() {
		return itemMakat;
	}

	public void setItemMakat(String itemMakat) {
		this.itemMakat = itemMakat;
	}

	@Override
	public String toString() {
		return "MakatItem [itemMakat=" + itemMakat + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((itemMakat == null) ? 0 : itemMakat.hashCode());
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
		MakatItem other = (MakatItem) obj;
		if (itemMakat == null) {
			if (other.itemMakat != null)
				return false;
		} else if (!itemMakat.equals(other.itemMakat))
			return false;
		return true;
	}



	
	
}
