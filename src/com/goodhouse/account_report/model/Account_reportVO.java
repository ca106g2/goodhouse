package com.goodhouse.account_report.model;

import java.sql.Date;

public class Account_reportVO {
	private String acc_rep_id;
	private String emp_id;
	private String mem_id;
	private String lan_id;
	private String acc_rep_status;
	private String acc_rep_reason;
	private Date acc_rep_date;
	public String getAcc_rep_id() {
		return acc_rep_id;
	}
	public void setAcc_rep_id(String acc_rep_id) {
		this.acc_rep_id = acc_rep_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getLan_id() {
		return lan_id;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public String getAcc_rep_status() {
		return acc_rep_status;
	}
	public void setAcc_rep_status(String acc_rep_status) {
		this.acc_rep_status = acc_rep_status;
	}
	public String getAcc_rep_reason() {
		return acc_rep_reason;
	}
	public void setAcc_rep_reason(String acc_rep_reason) {
		this.acc_rep_reason = acc_rep_reason;
	}
	public Date getAcc_rep_date() {
		return acc_rep_date;
	}
	public void setAcc_rep_date(Date acc_rep_date) {
		this.acc_rep_date = acc_rep_date;
	}
	
}
