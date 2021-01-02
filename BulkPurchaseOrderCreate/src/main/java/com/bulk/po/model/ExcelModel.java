package com.bulk.po.model;

public class ExcelModel {

    private int userid;
    private String sitename;
    private String projectname;
    private String projecttype;
    private String projectid;
    private int shiptolocationcode;
    private String duedate;
    private int quantity;
    private int totalcost;
  private String status;
    private String productname;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
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
	public String getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public int getShiptolocationcode() {
		return shiptolocationcode;
	}
	public void setShiptolocationcode(int shiptolocationcode) {
		this.shiptolocationcode = shiptolocationcode;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
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
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	@Override
	public String toString() {
		return "ExcelModel [userid=" + userid + ", sitename=" + sitename + ", projectname=" + projectname
				+ ", projecttype=" + projecttype + ", projectid=" + projectid + ", shiptolocationcode="
				+ shiptolocationcode + ", duedate=" + duedate + ", quantity=" + quantity + ", totalcost=" + totalcost
				+ ", status=" + status + ", productname=" + productname + "]";
	}
	

 

 
}