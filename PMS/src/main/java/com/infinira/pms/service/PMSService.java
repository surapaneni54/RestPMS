package com.infinira.pms.service;


import com.infinira.pms.dao.CustomerDAO;
import com.infinira.pms.service.IPMSService;
import com.infinira.pms.models.Customer;


public class PMSService implements IPMSService{
    
    private static volatile PMSService pmsServiceInstance;


	public static PMSService getInstance () {
		if (pmsServiceInstance == null) {
			synchronized(PMSService.class) {
				if (pmsServiceInstance == null) {
					pmsServiceInstance = new PMSService ();  // lazy loading                 
				}
			}
		}
        return pmsServiceInstance;
	}
    
	private PMSService() { 
	}   
	
	public long createCustomer(Customer customer) {
        return CustomerDAO.create(customer); 
	}
	
	public int  removeCustomer(long customerId) {
        return CustomerDAO.remove(customerId);
	}
    
	public int updateCustomer(Customer customer){
        return CustomerDAO.update(customer);
	}

	
	public Customer getCustomer(long customerId) {
		return CustomerDAO.getCustomer(customerId);
	}
}