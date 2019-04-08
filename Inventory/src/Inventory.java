//make another class to do user interface(basically main line)

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List <Item> itemInventory ;
	private List <Order> orders ;
	private List <Shipment> shippedOrders ;
	private static long itemid ;
	
	public Inventory() {
		itemInventory = new ArrayList <Item>() ;
		orders = new ArrayList<Order> () ;
		shippedOrders = new ArrayList<Shipment>() ;
		itemid = 0 ;
	}
	
	public void addItemToInventory (String itemName, double itemPrice, int itemQuantity)
	{
		boolean itemFound = false ;
		int counter = 0 ;
		while (itemFound && counter < itemInventory.size())
		{
			Item item = itemInventory.get(counter) ;
			if ( item.getName().equals(itemName) && item.getPrice() == itemPrice) 
			{
				item.setQuantity(item.getQuantity()+1);
				itemFound = true ;
			}
			counter++ ;
		}
		if (!itemFound)
		{
			Item item = new Item (itemid++ , itemName, itemPrice, itemQuantity) ;
			itemInventory.add(item) ;
		}
	}
	
	public int findItemQuatity (String itemName)
	{
		boolean itemFound = false ;
		int counter = 0 ;
		int quantity = -1 ;
		while (!itemFound && counter < itemInventory.size() ){ 
			
		}
		return quantity;   
	}
	
	public void createOrder (Item item)
	{
		Order order = new Order(item) ;
		orders.add(order) ;
	}
	
	public void createShipment (Item shipment)
	{
		Shipment shipmemt = new Shipment(shipment);
		shippedOrders.add(shipmemt);
	}
	
	public boolean processOrder(int orderId){
		boolean orderProcessed= false;
		Order order=null;
		int counter=0;
		while(!orderProcessed && counter<orders.size()){
		order=orders.get(counter);
			if(order.getOrderID()==orderId){
				orderProcessed=true;
			}
			counter++;
		}
		if(orderProcessed){
			createShipment(order.getOrderedItem());	
		}
		return orderProcessed;	
	}
	
	public void printAllItemDetails(){
		for( Item item : itemInventory){
			System.out.println(item.toString());
			
		}
	}
	
	public List<Item> getItemInventory() {
		return itemInventory;
	}
	public void setItemInventory(List<Item> itemInventory) {
		this.itemInventory = itemInventory;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Shipment> getShippedOrders() {
		return shippedOrders;
	}

	public void setShippedOrders(List<Shipment> shippedOrders) {
		this.shippedOrders = shippedOrders;
	}
}
