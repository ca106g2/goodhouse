package com.goodhouse.employee_permission.model;

import java.util.*;



public class Emp_PerService {
	private Emp_PerDAO_interface dao;
	
	public Emp_PerService() {
		dao = new Emp_PerDAO();
	}
	public Emp_PerVO addEmp_Per(String emp_id, String per_id) {
		Emp_PerVO emp_perVO = new Emp_PerVO();
		
		emp_perVO.setEmp_id(emp_id);
		emp_perVO.setPer_id(per_id);
		
		dao.insert(emp_perVO);
		
		return emp_perVO;
	}
	
	public  Emp_PerVO updateEmp_Per(String emp_id, String per_id) {
		Emp_PerVO emp_perVO = new Emp_PerVO();
		
		emp_perVO.setEmp_id(emp_id);
		emp_perVO.setPer_id(per_id);
		
		return emp_perVO;		
	}
	
	public List<Emp_PerVO> updateAllPer(String emp_ID, List<Emp_PerVO> listEmp_PerVO){
		if((!listEmp_PerVO.isEmpty())&&(!emp_ID.trim().isEmpty())) {
			dao.deletePer(emp_ID);
			return dao.insertPer(listEmp_PerVO);
		}else {
			return null;
		}
	}
	
	
	
	
	public void deleteEmp_Per(String emp_ID, String per_id) {
		dao.delete(emp_ID, per_id);
	}
	
	
	public List<Emp_PerVO> getPartOfOneEmp_Per(String emp_ID) {
		return dao.findByPartOfOnePrimaryKey(emp_ID);
	}
	public List<String> getPartOfOneEmp_Per2(String emp_ID) {
		return dao.findByPartOfOnePrimaryKey2(emp_ID);
	}
	
	
	
	
	public Emp_PerVO getOneEmp_Per(String emp_id, String per_id) {
		return dao.findByPrimaryKey(emp_id, per_id);
	}
	
	public List<Emp_PerVO> getAll(){
		return dao.getall();
	}
	
}

