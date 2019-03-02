package com.goodhouse.house_noappointment.model;

import java.sql.Date;

public class HouNoAppVO implements java.io.Serializable {
	
	private String hou_noapp_id;
	private String hou_id;
	private String lan_id;
	private String hou_noapp_time;
	private Date hou_noapp_date;
	public String getHou_noapp_id() {
		return hou_noapp_id;
	}
	public void setHou_noapp_id(String hou_noapp_id) {
		this.hou_noapp_id = hou_noapp_id;
	}
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
	public String getLan_id() {
		return lan_id;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public String getHou_noapp_time() {
		return hou_noapp_time;
	}
	public void setHou_noapp_time(String hou_noapp_time) {
		this.hou_noapp_time = hou_noapp_time;
	}
	public Date getHou_noapp_date() {
		return hou_noapp_date;
	}
	public void setHou_noapp_date(Date hou_noapp_date) {
		this.hou_noapp_date = hou_noapp_date;
	}
	
	

}
//git上傳註解用無意義