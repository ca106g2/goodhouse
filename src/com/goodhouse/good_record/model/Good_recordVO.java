package com.goodhouse.good_record.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Good_recordVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String good_rec_id;
	private String mem_id;
	private String good_rec_des;
	private Integer good_rec_poi;
	private Timestamp good_rec_dat;
	
	public String getGood_rec_id() {
		return good_rec_id;
	}
	public void setGood_rec_id(String good_rec_id) {
		this.good_rec_id = good_rec_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getGood_rec_des() {
		return good_rec_des;
	}
	public void setGood_rec_des(String good_rec_des) {
		this.good_rec_des = good_rec_des;
	}
	public Timestamp getGood_rec_dat() {
		return good_rec_dat;
	}
	public void setGood_rec_dat(Timestamp good_rec_dat) {
		this.good_rec_dat = good_rec_dat;
	}
	public Integer getGood_rec_poi() {
		return good_rec_poi;
	}
	public void setGood_rec_poi(Integer good_rec_poi) {
		this.good_rec_poi = good_rec_poi;
	}
}
