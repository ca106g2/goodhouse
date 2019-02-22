package com.goodhouse.contract.model;


//資料庫對應：合約分類
public class ContractVO implements java.io.Serializable{
	
	private String con_id;//合約分類編號
	private String con_name;//合約分類名稱
	private String con_content;//合約內容
	private String con_status;//合約分類狀態
	public String getCon_id() {
		return con_id;
	}
	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}
	public String getCon_name() {
		return con_name;
	}
	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}
	public String getCon_content() {
		return con_content;
	}
	public void setCon_content(String con_content) {
		this.con_content = con_content;
	}
	public String getCon_status() {
		return con_status;
	}
	public void setCon_status(String con_status) {
		this.con_status = con_status;
	}
	
	
	
}