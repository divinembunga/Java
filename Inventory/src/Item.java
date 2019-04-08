


public class Item {
	String name ;
	double price ;
	int quantity ;
	long id ;
	
	public Item (String itemName, double itemPrice, int itemQuantity) {
		this.name = itemName ;
		this.price = itemPrice ;
		this.quantity = itemQuantity ;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + ", quantity=" + quantity + ", id=" + id + "]";
	}

	public Item(long itemID, String itemName, double itemPrice, int itemQuantity) {
		this.id = itemID ;
		this.name = itemName ;
		this.price = itemPrice ;
		this.quantity = itemQuantity ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
