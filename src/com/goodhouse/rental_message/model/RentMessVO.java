package com.goodhouse.rental_message.model;

import java.sql.Date;

public class RentMessVO implements java.io.Serializable{
	
	private String ren_mes_id;
	private String hou_id;
	private String mem_id;
	private String lan_id;
	private Date ren_mes_time;
	private String ren_mes_request;
	private String ren_mes_response;
	
	public String getRen_mes_id() {
		return ren_mes_id;
	}
	public void setRen_mes_id(String ren_mes_id) {
		this.ren_mes_id = ren_mes_id;
	}
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
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
	public Date getRen_mes_time() {
		return ren_mes_time;
	}
	public void setRen_mes_time(Date ren_mes_time) {
		this.ren_mes_time = ren_mes_time;
	}
	public String getRen_mes_request() {
		return ren_mes_request;
	}
	public void setRen_mes_request(String ren_mes_request) {
		this.ren_mes_request = ren_mes_request;
	}
	public String getRen_mes_response() {
		return ren_mes_response;
	}
	public void setRen_mes_response(String ren_mes_response) {
		this.ren_mes_response = ren_mes_response;
	}
	
	
	

}
//git上傳註解用無意義