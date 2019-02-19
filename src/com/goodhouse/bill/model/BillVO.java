package com.goodhouse.bill.model;

import java.sql.Date;


//對應資料庫：帳單
public class BillVO implements java.io.Serializable{
	
	private String bill_id;//帳單編號
	private String ele_con_id;//電子合約編號
	private String emp_id;//員工編號
	private Integer bill_pay;//繳交費用
	private Date bill_date;//繳交日期
	private Date bill_producetime;//帳單產生日期
	private String bill_status;//帳單繳費狀態
	private String bill_paymethod;//付款方式
	private String bill_paymenttype;//繳費型態
	
	public String getBill_paymenttype() {
		return bill_paymenttype;
	}
	public void setBill_paymenttype(String bill_paymenttype) {
		this.bill_paymenttype = bill_paymenttype;
	}
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public String getEle_con_id() {
		return ele_con_id;
	}
	public void setEle_con_id(String ele_con_id) {
		this.ele_con_id = ele_con_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getBill_pay() {
		return bill_pay;
	}
	public void setBill_pay(Integer bill_pay) {
		this.bill_pay = bill_pay;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public Date getBill_producetime() {
		return bill_producetime;
	}
	public void setBill_producetime(Date bill_producetime) {
		this.bill_producetime = bill_producetime;
	}
	public String getBill_status() {
		return bill_status;
	}
	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}
	public String getBill_paymethod() {
		return bill_paymethod;
	}
	public void setBill_paymethod(String bill_paymethod) {
		this.bill_paymethod = bill_paymethod;
	}
	
	
}
