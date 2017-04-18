package npu.client;
import npu.domain.*;
import npu.services.*;

import java.util.ArrayList;

import org.springframework.context.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OrderApplication {
	public static void main(String args[]) {	
		AbstractApplicationContext container = new ClassPathXmlApplicationContext("application.xml");	
		OrderProcessor orderProc = (OrderProcessor) container.getBean("orderProcessor");
		InventoryService inventoryService = (InventoryService) container.getBean("inventoryServiceImpl");
		
		Order order;
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		Customer caCus = new Customer("Leo", "CA");				
		Product prod = new Product("HP Desktop", 998);
		Product prod1 = new Product("Lenovo Laptop", 1298);		
		OrderItem orderItem = new OrderItem(prod, 5);	
		OrderItem orderItem1 = new OrderItem(prod1, 2);		
		
		orderItemList.add(orderItem);
		orderItemList.add(orderItem1);
		
		order = new Order(caCus, orderItemList);
		
		inventoryService.printCurrentInventory();
		orderProc.newOrder(order);
		orderProc.printOrder(order);
		inventoryService.adjustInventory(order);
		inventoryService.printCurrentInventory();
	}
}
