package com.goodhouse.ad.model;

import java.sql.Date;

public class AdVO implements java.io.Serializable{
	private String ad_id;
	private String lan_id;
	private String hou_id;
	private Date ad_date;
	private String ad_sort_id;
	private String ad_status;
	private String ad_forfree;
	private String ad_statue;
	private String ad_paymethod;
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getLan_id() {
		return lan_id;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
	public Date getAd_date() {
		return ad_date;
	}
	public void setAd_date(Date ad_date) {
		this.ad_date = ad_date;
	}
	public String getAd_sort_id() {
		return ad_sort_id;
	}
	public void setAd_sort_id(String ad_sort_id) {
		this.ad_sort_id = ad_sort_id;
	}
	public String getAd_status() {
		return ad_status;
	}
	public void setAd_status(String ad_status) {
		this.ad_status = ad_status;
	}
	public String getAd_forfree() {
		return ad_forfree;
	}
	public void setAd_forfree(String ad_forfree) {
		this.ad_forfree = ad_forfree;
	}
	public String getAd_statue() {
		return ad_statue;
	}
	public void setAd_statue(String ad_statue) {
		this.ad_statue = ad_statue;
	}
	public String getAd_paymethod() {
		return ad_paymethod;
	}
	public void setAd_paymethod(String ad_paymethod) {
		this.ad_paymethod = ad_paymethod;
	}
	
}
