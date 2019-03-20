package com.goodhouse.employee.model;

import java.util.List;

public class EmpService {
	
	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	
	public EmpVO addEmp( String emp_name , Integer emp_phone,String emp_account,String emp_password,String emp_status) {
		
		
		EmpVO empVO = new EmpVO();
		empVO.setEmp_name(emp_name);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_password(emp_password);
		empVO.setEmp_status(emp_status);
		dao.insert(empVO);
		
		
		return empVO;
		
	}
	
public EmpVO updateEmp( String emp_id,String emp_name , Integer emp_phone,String emp_account,String emp_password,String emp_status) {
		
		
		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_id(emp_id);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_password(emp_password);
		empVO.setEmp_status(emp_status);

		dao.update(empVO);
		return empVO;
		
	}

	public void deleteEmp(String emp_id) {
	dao.delete(emp_id);
	}

	public EmpVO getOneEmp(String emp_id) {
	return dao.findByPrimaryKey(emp_id);
	}

	public List <EmpVO> getAll(){
		return dao.getall();
	}
	
	public EmpVO findByEmp_ID(String emp_id, String emp_password){
		return dao.findByEmp_ID(emp_id ,emp_password);
	}



}
