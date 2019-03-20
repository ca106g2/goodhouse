package com.goodhouse.favorite_good.model;

import java.io.Serializable;

public class Favorite_goodVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String good_id;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
}
