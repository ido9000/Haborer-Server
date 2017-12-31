
package com.Haborer.Entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class CountItem extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement 
	private int itemCount;

	public CountItem () {
		
	}
	
	
	public CountItem(String itemName, String itemCategory,String squadron,int itemCount) {
		super( itemName, itemCategory,squadron);
		this.itemCount=itemCount;
	}


	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
}
