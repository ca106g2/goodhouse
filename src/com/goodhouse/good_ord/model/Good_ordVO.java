package com.goodhouse.good_ord.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Good_ordVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String good_ord_id;
	private String mem_id;
	private Timestamp good_ord_dat;
	private String good_ord_sta;
	private String good_ord_nam;
	private String good_ord_add;
	
	public String getGood_ord_id() {
		return good_ord_id;
	}
	public void setGood_ord_id(String good_ord_id) {
		this.good_ord_id = good_ord_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public Timestamp getGood_ord_dat() {
		return good_ord_dat;
	}
	public void setGood_ord_dat(Timestamp good_ord_dat) {
		this.good_ord_dat = good_ord_dat;
	}
	public String getGood_ord_sta() {
		return good_ord_sta;
	}
	public void setGood_ord_sta(String good_ord_sta) {
		this.good_ord_sta = good_ord_sta;
	}
	public String getGood_ord_nam() {
		return good_ord_nam;
	}
	public void setGood_ord_nam(String good_ord_nam) {
		this.good_ord_nam = good_ord_nam;
	}
	public String getGood_ord_add() {
		return good_ord_add;
	}
	public void setGood_ord_add(String good_ord_add) {
		this.good_ord_add = good_ord_add;
	}
}
