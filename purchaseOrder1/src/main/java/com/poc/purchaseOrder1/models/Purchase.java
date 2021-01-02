package com.poc.purchaseOrder1.models;

import java.sql.Date;

public class Purchase {

	
	private int purchaseid;
	private	Integer userid;
	
	private String sitename;
	private String projectname;
	private String projectType;
	private String siteProjectid;
	
	private int shiptoLocationCode;
	private Date duedate;
	private int quantity;
	private int totalcost;
	private String status;
	private String productname;
	
	public Purchase() {
		super();
	}
	
	public Purchase(int purchaseid, Integer userid, String sitename, String projectname, String projectType,
			String siteProjectid, int shiptoLocationCode, Date duedate, int quantity, int totalcost,String status,String productname) {
		super();
		this.purchaseid = purchaseid;
		this.userid = userid;
		this.sitename = sitename;
		this.projectname = projectname;
		this.projectType = projectType;
		this.siteProjectid = siteProjectid;
		this.shiptoLocationCode = shiptoLocationCode;
		this.duedate = duedate;
		this.quantity = quantity;
		this.totalcost = totalcost;
		this.status=status;
		this.productname = productname;
	}
	
	
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getSiteProjectid() {
		return siteProjectid;
	}
	public void setSiteProjectid(String siteProjectid) {
		this.siteProjectid = siteProjectid;
	}
	public int getShiptoLocationCode() {
		return shiptoLocationCode;
	}
	public void setShiptoLocationCode(int shiptoLocationCode) {
		this.shiptoLocationCode = shiptoLocationCode;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(int totalcost) {
		this.totalcost = totalcost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	
}
