package npu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import npu.domain.*;

@Service("orderProcessor")
public class OrderProcessor {
	@Autowired
	@Qualifier("acctServiceImpl")
	private AccountingService acctService;
	
	@Autowired
	@Qualifier("inventoryServiceImpl")
	private InventoryService inventoryService;
	
	public OrderProcessor() {
	}
	
	public void setAcctService(AccountingService acctService) {
		this.acctService = acctService;
	}

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	public static double computSubtotal(Order order) {
		double sub = 0;
		for (int i = 0; i < order.getOrderItems().size(); i++) {
			sub += ((order.getOrderItems().get(i).getQuantity()) * (order.getOrderItems().get(i).getProd().getPrice()));
		}
		return sub;
	}

	public void newOrder(Order order) {
		acctService.recordNewOrder(order);
		order.setTax(acctService.processNewOrder(order));
		order.setTotal(computSubtotal(order)+order.getTax());
		inventoryService.adjustInventory(order);
	}
	
	public void printOrder(Order order) {
		System.out.println("Customer Name: "+order.getCus().getName()+",	State: "+order.getCus().getState());		
		for(OrderItem orderItem: order.getOrderItems()) {
			System.out.println("Ordered items: "+orderItem.getProd().getName()+"  Quantity: "+orderItem.getQuantity());
		}
		System.out.println("Sub total: "+order.getSubtotal()+"\nsales tax: "+order.getTax()+"\nTotal: "+order.getTotal());
		System.out.println("=============================");
	}
}