package com.goodhouse.ele_contract.model;

import java.sql.Date;

//對應資料庫：電子合約
public class Ele_ContractVO implements java.io.Serializable{
	
	private String ele_con_id;//電子合約編號
	private String con_id;//合約分類編號
	private String mem_id;//會員編號
	private String mem_idnumber;//會員身分證字號
	private String lan_id;//房東編號
	private String lan_idnumber;//房東身分證字號
	private String hou_id;//房屋編號
	private Integer ele_rent_money;//租金
	private Integer ele_deposit_money;//押金
	private Integer ele_rent_time;//租賃期限
	private Date ele_rent_f_day;//租賃起訖日
	private Date ele_rent_l_day;//租賃結束日
	private Date ele_singdate;//簽約日期
	private String ele_con_status;//合約狀態
	private String bill_paymenttype;//繳費型態
	private String ele_con_note;//備註
	
	
	public String getEle_con_id() {
		return ele_con_id;
	}
	public void setEle_con_id(String ele_con_id) {
		this.ele_con_id = ele_con_id;
	}
	public String getCon_id() {
		return con_id;
	}
	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_idnumber() {
		return mem_idnumber;
	}
	public void setMem_idnumber(String mem_idnumber) {
		this.mem_idnumber = mem_idnumber;
	}
	public String getLan_id() {
		return lan_id;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public String getLan_idnumber() {
		return lan_idnumber;
	}
	public void setLan_idnumber(String lan_idnumber) {
		this.lan_idnumber = lan_idnumber;
	}
	public String getHou_id() {
		return hou_id;
	}
	public void setHou_id(String hou_id) {
		this.hou_id = hou_id;
	}
	public Integer getEle_rent_money() {
		return ele_rent_money;
	}
	public void setEle_rent_money(Integer ele_rent_money) {
		this.ele_rent_money = ele_rent_money;
	}
	public Integer getEle_deposit_money() {
		return ele_deposit_money;
	}
	public void setEle_deposit_money(Integer ele_deposit_money) {
		this.ele_deposit_money = ele_deposit_money;
	}
	public Integer getEle_rent_time() {
		return ele_rent_time;
	}
	public void setEle_rent_time(Integer ele_rent_time) {
		this.ele_rent_time = ele_rent_time;
	}
	public Date getEle_rent_f_day() {
		return ele_rent_f_day;
	}
	public void setEle_rent_f_day(Date ele_rent_f_day) {
		this.ele_rent_f_day = ele_rent_f_day;
	}
	public Date getEle_rent_l_day() {
		return ele_rent_l_day;
	}
	public void setEle_rent_l_day(Date ele_rent_l_day) {
		this.ele_rent_l_day = ele_rent_l_day;
	}
	public Date getEle_singdate() {
		return ele_singdate;
	}
	public void setEle_singdate(Date ele_singdate) {
		this.ele_singdate = ele_singdate;
	}
	public String getEle_con_status() {
		return ele_con_status;
	}
	public void setEle_con_status(String ele_con_status) {
		this.ele_con_status = ele_con_status;
	}
	public String getBill_paymenttype() {
		return bill_paymenttype;
	}
	public void setBill_paymenttype(String bill_paymenttype) {
		this.bill_paymenttype = bill_paymenttype;
	}
	public String getEle_con_note() {
		return ele_con_note;
	}
	public void setEle_con_note(String ele_con_note) {
		this.ele_con_note = ele_con_note;
	}
	
	
}
