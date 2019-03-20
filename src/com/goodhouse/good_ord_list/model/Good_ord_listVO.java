package com.goodhouse.good_ord_list.model;

import java.io.Serializable;

public class Good_ord_listVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String good_id;
	private String good_ord_id;
	private Integer good_ord_amo;
	
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_ord_id() {
		return good_ord_id;
	}
	public void setGood_ord_id(String good_ord_id) {
		this.good_ord_id = good_ord_id;
	}
	public Integer getGood_ord_amo() {
		return good_ord_amo;
	}
	public void setGood_ord_amo(Integer good_ord_amo) {
		this.good_ord_amo = good_ord_amo;
	}
}
