package com.goodhouse.appointment.model;

import java.sql.Date;

public class AppointVO implements java.io.Serializable{
	private String appoint_id;
	private String mem_id;
	private String lan_id;
	private String hou_id;
	private String hou_app_time;
	private Date hou_app_date;
	private String app_status;
	private String app_remind;
	
	//�S���Ыثغc�l���w�]�|���@�ӡE�C
	
	public String getAppoint_id() {
		return appoint_id;
	}
	public void setAppoint_id(String appoint_id) {
		this.appoint_id = appoint_id;
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
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
	public String getHou_app_time() {
		return hou_app_time;
	}
	public void setHou_app_time(String hou_app_time) {
		this.hou_app_time = hou_app_time;
	}
	
	public Date getHou_app_date() {
		return hou_app_date;
	}
	public void setHou_app_date(Date hou_app_date) {
		this.hou_app_date = hou_app_date;
	}
	
	
	public String getApp_status() {
		return app_status;
	}
	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}
	public String getApp_remind() {
		return app_remind;
	}
	public void setApp_remind(String app_remind) {
		this.app_remind = app_remind;
	}
	
	

}
//git上傳註解用無意義