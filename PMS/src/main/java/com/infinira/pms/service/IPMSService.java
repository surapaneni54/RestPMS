package com.infinira.pms.service;

import com.infinira.pms.models.Customer;
public interface IPMSService  {
	public  long     createCustomer(Customer customer);
    public  int      removeCustomer(long customerId);
    public  int      updateCustomer(Customer customer);
    public Customer getCustomer(long customerId);
}
