package com.goodhouse.ad_report.model;

import java.sql.*;

public class Ad_reportVO implements java.io.Serializable{
	private String ad_rep_id;
	private String ad_id;
	private String mem_id;
	private String emp_id;
	private String ad_rep_status;
	private String ad_rep_reason;
	private Date ad_rep_date;
	public String getAd_rep_id() {
		return ad_rep_id;
	}
	public void setAd_rep_id(String ad_rep_id) {
		this.ad_rep_id = ad_rep_id;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getAd_rep_status() {
		return ad_rep_status;
	}
	public void setAd_rep_status(String ad_rep_status) {
		this.ad_rep_status = ad_rep_status;
	}
	public String getAd_rep_reason() {
		return ad_rep_reason;
	}
	public void setAd_rep_reason(String ad_rep_reason) {
		this.ad_rep_reason = ad_rep_reason;
	}
	public Date getAd_rep_date() {
		return ad_rep_date;
	}
	public void setAd_rep_date(Date ad_rep_date) {
		this.ad_rep_date = ad_rep_date;
	}
	

}
