package npu.domain;

import java.util.ArrayList;

public class Order {
	private String code;
	private Customer cus;
	private double subtotal, tax, total;
	private ArrayList<OrderItem> orderItems;
	
	public Order(String newOrderCode) {
		code = newOrderCode;
	}

	public Order(Customer cus, ArrayList<OrderItem> orderItems) {
		this.cus = cus;
		this.orderItems = orderItems;
	}
	
	public Order(Customer cus) {
		this.cus = cus;
		this.subtotal = 0;
		this.tax = 0;
		this.total = 0;
		this.orderItems = new ArrayList<OrderItem>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Customer getCus() {
		return cus;
	}

	public void setCus(Customer cus) {
		this.cus = cus;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void addItem(OrderItem item) {
		for (int i = 0; i < orderItems.size(); i++) {
			if (item.getProd().getName() == orderItems.get(i).getProd().getName()) {
				orderItems.get(i).setQuantity(item.getQuantity()+orderItems.get(i).getQuantity());
				break;
			}

			else
				orderItems.add(item);
		}		
	}
	
	public void removeProduct(Product prod) {
		for (int i = 0; i < orderItems.size(); i++) {
			if (prod == orderItems.get(i).getProd()) {
				orderItems.remove(i);
			}
		}
	}

}
