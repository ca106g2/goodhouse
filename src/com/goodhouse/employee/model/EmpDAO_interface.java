package com.goodhouse.employee.model;

import java.util.List;

public interface EmpDAO_interface {
	public void insert(EmpVO empVO);
	public void update(EmpVO empVO);
	public void delete(String EMP_ID);
	public EmpVO findByPrimaryKey(String EMP_ID);
	public List<EmpVO>getall();
	
}
