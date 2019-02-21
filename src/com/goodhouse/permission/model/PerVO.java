package com.goodhouse.permission.model;

import java.io.Serializable;

public class PerVO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String per_id;
	private String per_name;
	
	public String getPer_id() {
		return per_id;
	}
	public void setPer_id(String per_id) {
		this.per_id = per_id;
	}
	public String getPer_name() {
		return per_name;
	}
	public void setPer_name(String per_name) {
		this.per_name = per_name;
	}
	
	

	
	
}
