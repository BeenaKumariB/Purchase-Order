package com.poc.purchaseOrder1.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "purchaseid")
public class UserEntity {

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private int userid;
	
	
	private String name;
	
	
	private String contactno;
	
	@OneToMany(mappedBy="user")
	private List<PurchaseEntity> purchase;

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

	public List<PurchaseEntity> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<PurchaseEntity> purchase) {
		this.purchase = purchase;
	}

	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", name=" + name + ", contactno=" + contactno + ", purchase=" + purchase
				+ "]";
	}

}
