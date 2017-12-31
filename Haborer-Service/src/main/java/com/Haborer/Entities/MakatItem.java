
package com.Haborer.Entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class MakatItem extends Item{
	@XmlElement 
	private String itemMakat;
	
	public MakatItem() {
	}
	
	public MakatItem( String itemName, String itemCategory,String squadron,String itemMakat) {
		super(itemName, itemCategory,squadron);
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



	
	
}
