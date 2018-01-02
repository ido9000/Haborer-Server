
package com.Haborer.Entities;


public class MakatItem extends Item{
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
