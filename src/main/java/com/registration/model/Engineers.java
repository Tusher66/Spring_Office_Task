package com.registration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="engineers2")
public class Engineers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pId;
	private String pName;
	private String pDept;
	private String pLang;
	private String pAddress;
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_date")
    private Date join_date;
	

	

	public Engineers() {
		super();
	}
	

	public Engineers(int pId, String pName, String pDept, String pLang, String pAddress, Date join_date) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pDept = pDept;
		this.pLang = pLang;
		this.pAddress = pAddress;
		this.join_date = join_date;
	}


	public int getpId() {
		return pId;
	}


	public void setpId(int pId) {
		this.pId = pId;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public String getpDept() {
		return pDept;
	}


	public void setpDept(String pDept) {
		this.pDept = pDept;
	}


	public String getpLang() {
		return pLang;
	}


	public void setpLang(String pLang) {
		this.pLang = pLang;
	}


	public String getpAddress() {
		return pAddress;
	}


	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}


	public Date getJoin_date() {
		return join_date;
	}


	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}





//	@Override
//	public String toString() {
//		return "Engineers [pId="+pId+", pNmae="+pName+", pDept="+pDept+", pLang="+pLang+", pAddress="+pAddress+"]";
//	}
	
	
	
	

}
