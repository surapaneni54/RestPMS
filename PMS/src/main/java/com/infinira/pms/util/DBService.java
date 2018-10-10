package com.infinira.pms.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.InputStream;
import java.util.Properties;
import com.infinira.pms.util.ServiceUtil;


public class DBService {
    private String dbUser;  
    private String dbPassword;
    private String dbUrl;
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_URL = "db.url";
    public static final String PROPERTY_FILE = "config.properties";
  
   
   private static volatile DBService dbInstance;
    // lazy loading
    public static DBService getInstance () {
        if (dbInstance == null) {
            synchronized(DBService.class) {
                if (dbInstance == null) {
                    dbInstance = new DBService ();                    
                }
            }
        }
        return dbInstance;
    }
   
   
    private DBService() { 
        read();
    }
    //reading values from properti file
    private void read() {
        Properties properties = new Properties();
        InputStream inputStream = null;
         
        try {
            inputStream = DBService.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
        } catch(Throwable th) {
           ServiceUtil.throwException("PMS-0101", null,PROPERTY_FILE);
        }
        
        try {
            properties.load(inputStream);
        } catch(Throwable th) {
            ServiceUtil.throwException("PMS-0102", th,PROPERTY_FILE,dbUser,dbUrl);
        }
      
        dbUser = properties.getProperty(DB_USER);
        validate(DB_USER, dbUser, PROPERTY_FILE);
        dbPassword = properties.getProperty(DB_PASSWORD); 
        validate(DB_PASSWORD, dbPassword, PROPERTY_FILE);  
        dbUrl = properties.getProperty(DB_URL);
        validate(DB_URL, dbUrl, PROPERTY_FILE);                    
    }
    
    private void validate(String property, String value, String PROPERTY_FILE) {
        if(value == null || value.equals("")) {
            ServiceUtil.throwException("PMS-0103", null,property,PROPERTY_FILE);
        } 
    }
    
    //creating connection with database  
    public Connection getConnection() { 
        Connection con = null;
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	con = DriverManager.getConnection(dbUrl,"user1","user1");
//        	//Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection(dbUrl); 
        } 
        catch(Throwable th) {
            ServiceUtil.throwException("PMS-0100", th,PROPERTY_FILE,dbUser,dbUrl);
        }
        return con;
    }
     //closing Resources
    public void closeResources (ResultSet rs, PreparedStatement pstmt, Connection con) {
        if ( rs!= null ) {
            try {
                rs.close();
            } catch (Throwable th) {
                //This part will not Throw an exception we can continue the execution.
                //throw new RuntimeException("unable to close Resources   "+th);
            }
        }
        
       
        
        if ( pstmt != null ) {
            try {
                pstmt.close();
            } catch (Throwable th) {
                //This part will not Throw an exception we can continue the execution.
                //throw new RuntimeException("unable to close Resources   "+th);
            }
        }
        
        if ( con != null ) {
            try {
                con.close();
            } catch (Throwable th) {
                //This part will not Throw an exception we can continue the execution.
                //throw new RuntimeException("unable to close Resources   "+th);
            }
        }
    }
    

}