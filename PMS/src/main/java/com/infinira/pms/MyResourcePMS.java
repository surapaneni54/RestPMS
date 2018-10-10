package com.infinira.pms;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.infinira.pms.models.Customer;
import com.infinira.pms.service.PMSService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/registration")
public class MyResourcePMS {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/customer/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("customerId") long customerId) {
        return PMSService.getInstance().getCustomer(customerId);
    }
    
    @POST
    @Path("/customer/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long createCustomer(Customer customer) {
    	return PMSService.getInstance().createCustomer(customer);
    }
    
    @POST
    @Path("/customer/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int updateCustomer(Customer customer) {
    	return PMSService.getInstance().updateCustomer(customer);
    }
    
    @POST
    @Path("/customer/remove/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int removeCustomer(@PathParam("customerId")long   customerId) {
    	return PMSService.getInstance().removeCustomer(customerId);
    }
    
    
    
    
}
