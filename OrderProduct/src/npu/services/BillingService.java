package npu.services;
import npu.domain.*;

public interface BillingService {
	public void billCustomer(Customer cus, Order order);
}
