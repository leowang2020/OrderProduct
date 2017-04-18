package npu.services;
import npu.domain.*;

public interface InventoryService {
	public void adjustInventory(Order order);
	public void printCurrentInventory();
}
