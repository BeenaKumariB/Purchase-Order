package com.poc.purchaseOrder1.entities;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "purchaseid")
public class PurchaseEntity {

	public PurchaseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purchaseid")
	private int purchaseid;
	
	
	private String sitename;
	
	
	private String projectname;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private UserEntity user;
	
	
	private String SiteProjectid;
	
	
	private int ShiptoLocationCode;
	
	
	private String ProjectType;
	
	
	private Date duedate;
	
	
	private int quantity;
	
	
	private int totalcost;
	
	private String status;
	private String productname;

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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getSiteProjectid() {
		return SiteProjectid;
	}

	public void setSiteProjectid(String siteProjectid) {
		SiteProjectid = siteProjectid;
	}

	public int getShiptoLocationCode() {
		return ShiptoLocationCode;
	}

	public void setShiptoLocationCode(int shiptoLocationCode) {
		ShiptoLocationCode = shiptoLocationCode;
	}

	public String getProjectType() {
		return ProjectType;
	}

	public void setProjectType(String projectType) {
		ProjectType = projectType;
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
