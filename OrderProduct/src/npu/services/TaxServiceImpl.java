package npu.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import npu.domain.*;

@Service("taxServiceImpl")
public class TaxServiceImpl implements TaxService {
	private Map stateTaxMap;
	
	@Autowired
	public TaxServiceImpl(@Value ("${stateTaxRates.list}") String taxRateList){
		stateTaxMap = convertStringListToMap(taxRateList);
	}
	
	public static Map<String, String> convertStringListToMap(String taxRateList) {
		String[] taxArr = taxRateList.split(",");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < taxArr.length; i ++){
			map.put(taxArr[i], taxArr[++i]);
		}
		
		return map;
	}

	public void setStateTaxMap(Map stateTaxMap) {
		this.stateTaxMap = stateTaxMap;
	}
	
	public void getSalesTaxMap(){
		System.out.println("Map Elements are: " + stateTaxMap);
	}

	@Override
	public double computeTax(Order order) {
		double tax = 0;
		double subTotal = OrderProcessor.computSubtotal(order);
		order.setSubtotal(subTotal);
		String state = order.getCus().getState();
		tax = subTotal * Double.parseDouble((String)stateTaxMap.get(state));
		return tax;
	}
}
