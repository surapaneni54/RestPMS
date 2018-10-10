package com.infinira.pms.models;

import java.sql.Date;
import com.infinira.pms.util.ServiceUtil;
public class Project{
    
    private     long projectId;
    private     String projectName;
    private     Date startDate;
    private     Date endDate;
    private     String description;
    private     long budget;
    private     String lastModifiedBY;
    private     String risk;
    private     long customerId;
	    
    public Project(String projectName,long customerId){
        if (projectName == null){
            ServiceUtil.throwException("PMS-0021", null,projectName);
        }
        if (customerId == -1){
            ServiceUtil.throwException("PMS-0022", null,customerId);
        }
        this.projectName = projectName;
        this.customerId = customerId;
    }
    
    //Setter Methods.
    public void setProjectId(long projectId) {
        if(projectId <=0L){
            ServiceUtil.throwException("PMS-0020", null,projectId);
        }    
		this.projectId = projectId;
	}
   	public void setStartDate(Date startDate) {
        if(startDate ==null){
            ServiceUtil.throwException("PMS-0023", null,startDate);
        }   
		this.startDate = startDate;
	}
    public void setEndDate(Date endDate) {
        if(endDate ==null){
            ServiceUtil.throwException("PMS-0024", null,endDate);
        }   
		this.endDate = endDate;
	}
    public void setDescription(String description) {
        if(description ==null){
            ServiceUtil.throwException("PMS-0025", null,description);
        }  
		this.description = description;
	}
    public void setBudget(long budget) {
        if(budget <=-1){
            ServiceUtil.throwException("PMS-0026", null,budget);
        }  
		this.budget = budget;
	}
    public void setRisk(String risk) {
        if(risk ==null){
            ServiceUtil.throwException("PMS-0027", null,risk);
        }  
		this.risk = risk;
	}
  
    public void setLastModifiedBY(String lastModifiedBY) {
        if(lastModifiedBY ==null){
            ServiceUtil.throwException("PMS-0028", null,lastModifiedBY);
        }  
		this.lastModifiedBY = lastModifiedBY;
	}
    
    //Getter Methods.
    public long getProjectId() {
		return projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getDescription() {
		return description;
	}
	
	public long getBudget() {
		return budget;
	}

	public String getLastModifiedBY() {
		return lastModifiedBY;
	}
	public String getRisk() {
		return risk;
	}
	
	public long getCustomerId() {
		return customerId;
	}
    
    
    public String toString() {  
        StringBuilder sb =  new StringBuilder();
        sb.append(projectId).append(":");
        sb.append(projectName).append(":");
        sb.append(startDate).append(":");
        sb.append(endDate).append(":");  
        sb.append(description).append(":");
        sb.append(budget).append(":");
        sb.append(lastModifiedBY).append(":");
        sb.append(risk).append(":");  
        sb.append(customerId).append(":");
        return sb.toString();
    }
  
}


