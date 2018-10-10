package com.infinira.pms.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.infinira.pms.models.Customer;

public class RestApiClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget = client.target("http://localhost:6060/PMS/webapi/");
		WebTarget registrationTarget = baseTarget.path("registration");
		WebTarget customerTarget = registrationTarget.path("customer");
		WebTarget singleTarget = customerTarget.path("{customer_id}");
		
		
		
		Builder builder = singleTarget.resolveTemplate("customer_id" ,5 ).request(MediaType.APPLICATION_JSON);
		
		Customer res=builder.get(Customer.class);
		//Customer customer = res.readEntity(Customer.class);
		System.out.println(res.getCustomerName());
		
		
		Customer customer = new Customer();
		customer.setCustomerId(34);
		customer.setCustomerName("harsha");
		customer.setProjectExecutive("vardhan");
		customer.setProjectManager("surapanei");
		Builder builder1 = customerTarget.path("/create").request(MediaType.APPLICATION_JSON);
		
		Response postResponse= builder1.post(Entity.json(customer));
		
		System.out.println(postResponse);
		
//	
//		if (postResponse.getStatus() != 201) {
//			System.out.println("Error");
//		}
		Long customerId = postResponse.readEntity(Long.class);
		System.out.println(customerId);
	}

}
