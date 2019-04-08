

import java.util.Calendar;
import java.util.Date;

public class Order {
	private int orderID ;
	private Item orderedItem ;
	
	public Order(Item orderedItem){
		this.orderedItem=orderedItem;
		orderDate= Calendar.getInstance().getTime();
	}
	
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderedItem=" + orderedItem + ", orderDate=" + orderDate + "]";
	}

	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Item getOrderedItem() {
		return orderedItem;
	}
	public void setOrderedItem(Item orderedItem) {
		this.orderedItem = orderedItem;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	private Date orderDate ;
}

