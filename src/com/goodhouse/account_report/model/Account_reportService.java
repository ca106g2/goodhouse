package com.goodhouse.account_report.model;

import java.sql.Date;
import java.util.List;

public class Account_reportService {
	
	private Account_reportDAO_interface dao;
	
	public Account_reportService() {
		dao = new Account_reportDAO();
	}
	
	public Account_reportVO addAccount_report(
			String emp_id, String mem_id, String lan_id, String acc_rep_status,
			String acc_rep_reason, Date acc_rep_date) {
		Account_reportVO acreVO = new Account_reportVO();
		
		acreVO.setEmp_id(emp_id);
		acreVO.setMem_id(mem_id);
		acreVO.setLan_id(lan_id);
		acreVO.setAcc_rep_status(acc_rep_status);
		acreVO.setAcc_rep_reason(acc_rep_reason);
		acreVO.setAcc_rep_date(acc_rep_date);
		dao.insert(acreVO);
		
		return acreVO;
	}
	//struts 2
	public void addAccount_report(Account_reportVO account_reportVO) {
		dao.insert(account_reportVO);
	}
	
	public Account_reportVO updateAccount_report(String acc_rep_id, String emp_id, 
			String mem_id, String lan_id, String acc_rep_status,
			String acc_rep_reason, Date acc_rep_date) {
		Account_reportVO acreVO = new Account_reportVO();
		
		acreVO.setAcc_rep_id(acc_rep_id);
		acreVO.setEmp_id(emp_id);
		acreVO.setMem_id(mem_id);
		acreVO.setLan_id(lan_id);
		acreVO.setAcc_rep_status(acc_rep_status);
		acreVO.setAcc_rep_reason(acc_rep_reason);
		acreVO.setAcc_rep_date(acc_rep_date);
		dao.update(acreVO);
		
		return dao.findByPrimaryKey(acc_rep_id);
	}
	
	public void updateAccount_report(Account_reportVO account_reportVO) {
		dao.update(account_reportVO);
	}
	
	public void deleteAccount_report(String acc_rep_id) {
		dao.delete(acc_rep_id);
	}
	public Account_reportVO getOneAccount_report(String acc_rep_id) {
		return dao.findByPrimaryKey(acc_rep_id);
	}
	
	public List<Account_reportVO> getAll(){
		return dao.getAll();
	}
}
