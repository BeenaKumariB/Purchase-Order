package com.poc.purchaseOrder1.models;

import java.util.List;

public class User {
	
	private int userid;
	private String name;
	private String contactno;
	private List<Integer> purchaseid;
	
	public User() {
		
	}
	
	public User(int userid, String name, String contactno, List<Integer> purchaseid) {
		super();
		this.userid = userid;
		this.name = name;
		this.contactno = contactno;
		this.purchaseid = purchaseid;
	}
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public List<Integer> getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(List<Integer> purchaseid) {
		this.purchaseid = purchaseid;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", contactno=" + contactno + ", purchaseid=" + purchaseid
				+ "]";
	}




}
