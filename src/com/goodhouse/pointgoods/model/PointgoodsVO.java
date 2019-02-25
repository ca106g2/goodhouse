package com.goodhouse.pointgoods.model;

import java.io.Serializable;

public class PointgoodsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String good_id;
	private String good_nam;
	private String good_dsc;
	private Integer good_amo;
	private Integer good_pri;
	private String good_sta;
	private byte[] good_pic;
	
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_nam() {
		return good_nam;
	}
	public void setGood_nam(String good_nam) {
		this.good_nam = good_nam;
	}
	public String getGood_dsc() {
		return good_dsc;
	}
	public void setGood_dsc(String good_dsc) {
		this.good_dsc = good_dsc;
	}
	public Integer getGood_amo() {
		return good_amo;
	}
	public void setGood_amo(Integer good_amo) {
		this.good_amo = good_amo;
	}
	public Integer getGood_pri() {
		return good_pri;
	}
	public void setGood_pri(Integer good_pri) {
		this.good_pri = good_pri;
	}
	public String getGood_sta() {
		return good_sta;
	}
	public void setGood_sta(String good_sta) {
		this.good_sta = good_sta;
	}
	public byte[] getGood_pic() {
		return good_pic;
	}
	public void setGood_pic(byte[] good_pic) {
		this.good_pic = good_pic;
	}
}
