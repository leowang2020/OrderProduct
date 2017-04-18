package npu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import npu.domain.Order;

@Service("acctServiceImpl")
public class AccountingServiceImpl implements AccountingService{		
	@Autowired
	@Qualifier("taxServiceImpl")
	private TaxService taxService;
	
	@Override
	public void recordNewOrder(Order order) {
		System.out.println("Accounting Service Implementation: ");
		return;
	}
	@Override
	public double processNewOrder(Order order) {
		return taxService.computeTax(order); 
	}

	public void setTaxService (TaxService taxService){
		this.taxService = taxService;
	}

}
