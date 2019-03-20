package com.goodhouse.employee.model;

import java.io.Serializable;

public class EmpVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String emp_id;
	private String emp_name;
	private Integer emp_phone;
	private String emp_account;
	private String emp_password;
	private String emp_status;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Integer getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(Integer emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_account() {
		return emp_account;
	}
	public void setEmp_account(String emp_account) {
		this.emp_account = emp_account;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}
	
	
	
	

}
