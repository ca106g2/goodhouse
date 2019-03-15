package com.goodhouse.employee_permission.model;

import java.io.Serializable;

public class Emp_PerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String emp_id;
	private String per_id;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getPer_id() {
		return per_id;
	}
	public void setPer_id(String per_id) {
		this.per_id = per_id;
	}
	
	
	
}
