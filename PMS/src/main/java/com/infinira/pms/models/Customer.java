
package com.infinira.pms.models;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.infinira.pms.util.ServiceUtil;
 
@XmlRootElement
public class Customer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  long    customerId;
	private  String  customerName;
	private  String  projectExecutive;
	private  String  projectManager;
	
	public Customer() {}
		
    public  Customer(String customerName){
        if (customerName == null){
            ServiceUtil.throwException("PMS-0011", null,customerName);
        }
        this.customerName = customerName;
    } 
    
    //Getter Methods.
	public long getCustomerId(){
		return customerId;
	}
	public String getCustomerName(){
		return customerName;
	}
	public String getProjectExecutive() {
        return projectExecutive;
	}
	public String getProjectManager() {
		return projectManager;
	}
    
    //Setter Methods.
     
    
    public void setCustomerId(long customerId){
        if (customerId <= 0L) {
            ServiceUtil.throwException("PMS-0010", null,customerId);            
        }
		this.customerId=customerId;
	}
    
    public void setCustomerName(String customerName){
        if (customerName == null) {
            ServiceUtil.throwException("PMS-0011", null,customerName);            
        }
		this.customerName=customerName;
	}
    
    
    public void setProjectExecutive(String projectExecutive) {
        if (projectExecutive == null) {
            ServiceUtil.throwException("PMS-0012", null,projectExecutive);
        }
        this.projectExecutive = projectExecutive;
	}
	public void setProjectManager(String projectManager) {
        if (projectManager == null) {
            ServiceUtil.throwException("PMS-0013", null,projectManager);
        }
		this.projectManager = projectManager;
	}
    public String toString() {  
        StringBuilder sb =  new StringBuilder();
        sb.append(customerId).append(":");
        sb.append(customerName).append(":");
        sb.append(projectExecutive).append(":");
        sb.append(projectManager).append(":");  
        return sb.toString();
    }
	
 
 }
 