package com.goodhouse.apply_conturct.model;

//資料庫對應：申請合約處理
public class Apply_ConturctVO implements java.io.Serializable{
	
	private String app_con_id;//合約處理編號
	private String ele_con_id;//電子合約編號
	private String mem_id;//會員編號
	private String hou_id;//房屋編號
	private String app_con_content;//申請內容
	private String app_con_status;//申請狀態
	private String app_con_other;//其他備註
	public String getApp_con_id() {
		return app_con_id;
	}
	public void setApp_con_id(String app_con_id) {
		this.app_con_id = app_con_id;
	}
	public String getEle_con_id() {
		return ele_con_id;
	}
	public void setEle_con_id(String ele_con_id) {
		this.ele_con_id = ele_con_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
	public String getApp_con_content() {
		return app_con_content;
	}
	public void setApp_con_content(String app_con_content) {
		this.app_con_content = app_con_content;
	}
	public String getApp_con_status() {
		return app_con_status;
	}
	public void setApp_con_status(String app_con_status) {
		this.app_con_status = app_con_status;
	}
	public String getApp_con_other() {
		return app_con_other;
	}
	public void setApp_con_other(String app_con_other) {
		this.app_con_other = app_con_other;
	}
	
	
}
