package com.goodhouse.ele_contract.model;

import java.sql.Date;
import java.util.List;

import com.goodhouse.bill.model.BillVO;
import com.sun.beans.editors.IntegerEditor;

public class Ele_ContractService {
	
	private Ele_ContractDAO_interface dao;
	
	public Ele_ContractService() {
		dao = new Ele_ContractJNDIDAO();
	}
	
	//新增
	public Ele_ContractVO addEC(String con_id , String mem_id , String mem_idnumber , String lan_id , String lan_idnumber, 
			String hou_id , Integer ele_rent_money , Integer ele_deposit_money , Integer ele_rent_time , 
			Date ele_rent_f_day , Date ele_rent_l_day , Date ele_singdate ,String ele_con_status , String bill_paymenttype , String ele_con_note) {
		
		Ele_ContractVO ecVO = new Ele_ContractVO();
		ecVO.setCon_id(con_id);
		ecVO.setMem_id(mem_id);
		ecVO.setMem_idnumber(mem_idnumber);
		ecVO.setLan_id(lan_id);
		ecVO.setLan_idnumber(lan_idnumber);
		ecVO.setHou_id(hou_id);
		ecVO.setEle_rent_money(ele_rent_money);
		ecVO.setEle_deposit_money(ele_deposit_money);
		ecVO.setEle_rent_time(ele_rent_time);
		ecVO.setEle_rent_f_day(ele_rent_f_day);
		ecVO.setEle_rent_l_day(ele_rent_l_day);
		ecVO.setEle_singdate(ele_singdate);
		ecVO.setEle_con_status(ele_con_status);
		ecVO.setBill_paymenttype(bill_paymenttype);
		ecVO.setEle_con_note(ele_con_note);
		
		dao.insert(ecVO);
		
		return ecVO;
	}
	
	//預留給 Struts 2 用的
	public void addEC(Ele_ContractVO ecVO) {
		dao.insert(ecVO);
	}
	
	//修改
	public Ele_ContractVO updateEC(String ele_con_id, String con_id, String mem_id , String mem_idnumber , String lan_id ,
			String lan_idnumber , String hou_id , Integer ele_rent_money , Integer ele_deposit_money , 
			Integer ele_rent_time , Date ele_rent_f_day , Date ele_rent_l_day , Date ele_singdate , 
			String ele_con_status , String bill_paymenttype , String ele_con_note) {
		
		Ele_ContractVO ecVO = new Ele_ContractVO();
		
		ecVO.setCon_id(con_id);
		ecVO.setMem_id(mem_id);
		ecVO.setMem_idnumber(mem_idnumber);
		ecVO.setLan_id(lan_id);
		ecVO.setLan_idnumber(lan_idnumber);
		ecVO.setHou_id(hou_id);
		ecVO.setEle_rent_money(ele_rent_money);
		ecVO.setEle_deposit_money(ele_deposit_money);
		ecVO.setEle_rent_time(ele_rent_time);
		ecVO.setEle_rent_f_day(ele_rent_f_day);
		ecVO.setEle_rent_l_day(ele_rent_l_day);
		ecVO.setEle_singdate(ele_singdate);
		ecVO.setEle_con_status(ele_con_status);
		ecVO.setBill_paymenttype(bill_paymenttype);
		ecVO.setEle_con_note(ele_con_note);
		dao.update(ecVO);
		
		return dao.findByPrimaryKey(ele_con_id);
	}
	
	//預留給 Struts 2 用的
	public void updateEC(Ele_ContractVO ecVO) {
		dao.update(ecVO);
	}
	
//	//刪除
//	public void deleteEC(String ele_con_id) {
//		dao.delete(ele_con_id);
//	}
	
	//單一查詢
	public Ele_ContractVO getOneEC(String ele_con_id) {
		return dao.findByPrimaryKey(ele_con_id);
	}
	
	//查詢全部
	public List<Ele_ContractVO> getAll(){
		return dao.getAll();
	}
	
	//利用mem_id查詢全部
	public List<Ele_ContractVO> getAllForEle_ConByMem_id(String mem_id){
		return dao.getAllForEle_ConByMem_id(mem_id);
	}
	
	//利用lan_id查詢全部
	public List<Ele_ContractVO> getAllForEle_ConByLan_id(String lan_id){
		return dao.getAllForEle_ConByLan_id(lan_id);
	}
	
//	public void addEC(Ele_ContractVO ecVO, List <BillVO> billVOlist) {
//		dao.insert(ecVO, billVOlist);
//	}
}
