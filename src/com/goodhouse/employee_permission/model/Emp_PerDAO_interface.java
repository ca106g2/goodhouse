package com.goodhouse.employee_permission.model;

import java.util.List;



public interface Emp_PerDAO_interface {

		public void insert( Emp_PerVO emp_perVo);
		public void update(Emp_PerVO emp_perVO_old, Emp_PerVO emp_perVO_new);
		public void delete(String emp_id ,String per_id);
	    public void deletePer(String emp_id);
		public Emp_PerVO findByPrimaryKey(String emp_id, String per_id);
		
		public List<Emp_PerVO> findByPartOfOnePrimaryKey(String emp_id);
	    public List<String> findByPartOfOnePrimaryKey2(String emp_id);
		public List<Emp_PerVO>getall();
		
	    public List<Emp_PerVO> insertPer(List<Emp_PerVO> listEmp_PerVO);
}
