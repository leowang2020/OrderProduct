package npu.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import npu.domain.*;

@Service("inventoryServiceImpl")
public class InventoryServiceImpl implements InventoryService {
	private static long idGenerator = 0;
	private ArrayList<Product> prodList;
	
	//public InventoryServiceImpl() {
	private void originalInventoryList(){
		Product prod;
		
		prodList = new ArrayList<Product>();
		
		prod = new Product("HP Desktop", 998, 10);
		idGenerator++;
		prod.setId(idGenerator);
		prodList.add(prod);
		
		prod = new Product("Lenovo Laptop", 1298, 8);
		idGenerator++;
		prod.setId(idGenerator);
		prodList.add(prod);
		
		prod = new Product("SamSung NotePad", 598, 15);
		idGenerator++;
		prod.setId(idGenerator);
		prodList.add(prod);
		
	}
	
	public void adjustInventory(Order order) {
		for(int i=0;i<order.getOrderItems().size();i++) {
			for(int j=0;j<order.getOrderItems().size();j++){
				if(prodList.get(i).getName().equals(order.getOrderItems().get(j).getProd().getName())) {		
					int quantity = prodList.get(i).getQuantity() - order.getOrderItems().get(j).getQuantity();
					prodList.get(i).setQuantity(quantity);
				}
			}
		}
	}
	
	public void printCurrentInventory() {
		System.out.println("Current Inventory: ");
		for(int i=0;i<prodList.size();i++){
			System.out.println(prodList.get(i).getName()+" : "+prodList.get(i).getQuantity()+" in stock");
		}
		System.out.println("=============================");
	}
	
	public void destoryInventoryList(){
		printCurrentInventory();
	}
}
