package com.infinira.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.infinira.pms.models.Customer;
import com.infinira.pms.util.ServiceUtil;
import com.infinira.pms.util.DBService;


public  class CustomerDAO {
    public  static  long  create(Customer customer)  {
        long customerId = -1;  
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Connection con =null;
		try {
			con = DBService.getInstance().getConnection();
			con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_QUERY, new String[] { "CUSTOMER_ID" });
			pstmt.setString(1, customer.getCustomerName());
			pstmt.setString(2, customer.getProjectExecutive() );
			pstmt.setString(3, customer.getProjectManager());
		
			pstmt.executeUpdate();
			con.setAutoCommit(true);
			rs = pstmt.getGeneratedKeys();
			
			//customerId = (rs == null) || (rs.next()) ? rs.getLong(1) : null;
			
            if (null !=rs && rs.next()) {
                customerId = rs.getLong(1);
            }else{
                ServiceUtil.throwException("PMS-0001", null, customer.getCustomerName());
            }    
            
		}
        catch (Throwable th) {
           try {
			con.rollback();
           } catch (Throwable th1) {
			 throw new RuntimeException("Failed To Rollback",th1);
           }
           ServiceUtil.throwException("PMS-0001", th, customer.getCustomerName());
		}finally{
			DBService.getInstance().closeResources(rs, pstmt, con);
		}
        return customerId;
	}
	public static int remove(long customerId) {
		PreparedStatement pstmt = null;
        int deletedRows = -1;
        
        Connection con = DBService.getInstance().getConnection();
		try {
			pstmt =  con.prepareStatement(DELETE_QUERY);
			pstmt.setLong(1, customerId);
            deletedRows = pstmt.executeUpdate();
            if(deletedRows == -1) {
                ServiceUtil.throwException("PMS-0002", null, customerId);
            }
		} catch(Throwable th){
            ServiceUtil.throwException("PMS-0002", th,customerId);
		} finally{
			DBService.getInstance().closeResources(null, pstmt, con);
		}    
        return deletedRows;
	} 
	public static int  update(Customer customer) {
		PreparedStatement pstmt = null;
        int updatedRows = -1;
        
		Connection con =DBService.getInstance().getConnection();
		try {
			pstmt = con.prepareStatement(UPDATE_QUERY);  
			pstmt.setString(1, customer.getCustomerName());
			pstmt.setString(2, customer.getProjectExecutive());
			pstmt.setString(3, customer.getProjectManager());
			pstmt.setLong(4, customer.getCustomerId());
			updatedRows = pstmt.executeUpdate();
            if(updatedRows == -1) {
                ServiceUtil.throwException("PMS-0003", null, customer.getCustomerId(),customer.getCustomerName());
            }
		}catch (Throwable th) {  
            ServiceUtil.throwException("PMS-0003", th, customer.getCustomerId(),customer.getCustomerName());
		}finally{
			DBService.getInstance().closeResources(null, pstmt, con);
		}
        return updatedRows;
	} 

	public static Customer getCustomer(Long customerId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = new Customer();
        Connection con = DBService.getInstance().getConnection();
        try {
        	pstmt = con.prepareStatement(GET_CUSTOMER_QuERY);
        	pstmt.setLong(1, customerId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	customer.setCustomerId(rs.getLong("CUSTOMER_ID"));
            	customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
            	customer.setProjectExecutive(rs.getString("PROJECT_EXECUTIVE"));
            	customer.setProjectManager(rs.getString("PROJECT_MANAGER"));
                
            }
        } catch(Exception ex) {
        	ServiceUtil.throwException("PMS-0004", null, customer.getCustomerId());
        } finally {
        	DBService.getInstance().closeResources(null, pstmt, con);
        }
        return customer;
    }
	
	private  static final String DELETE_QUERY = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID =?";
	private  static final String INSERT_QUERY = "INSERT INTO CUSTOMER  VALUES(?,?,?)";
	private  static final String UPDATE_QUERY = "UPDATE  CUSTOMER SET CUSTOMER_NAME=?,PROJECT_EXECUTIVE = ?,PROJECT_MANAGER = ? WHERE  CUSTOMER_ID=?";
	private  static final String GET_CUSTOMER_QuERY = "SELECT CUSTOMER_ID, CUSTOMER_NAME, PROJECT_EXECUTIVE, PROJECT_MANAGER FROM CUSTOMER WHERE CUSTOMER_ID=?";
}