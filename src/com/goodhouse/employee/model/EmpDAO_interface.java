package com.goodhouse.employee.model;

import java.util.List;

public interface EmpDAO_interface {
	public void insert(EmpVO empVO);
	public void update(EmpVO empVO);
	public void delete(String emp_id);
	public EmpVO findByPrimaryKey(String emp_id);
	public EmpVO findByEmp_ID(String emp_id,String emp_password);
	public List<EmpVO>getall();
	
}
