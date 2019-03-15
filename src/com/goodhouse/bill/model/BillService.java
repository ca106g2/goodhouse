package com.goodhouse.bill.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;


public class BillService {

	private BillDAO_interface dao ;
	
	public BillService() {
		dao = new BillJNDIDAO();
	}
	
	//新增
	public BillVO addB(String bill_id , String ele_con_id , Integer bill_pay , Date bill_date , 
			Date bill_producetime , String bill_status , String bill_paymethod , String bill_paymemttype) {
		
		BillVO bVO = new BillVO();
		
		bVO.setEle_con_id(ele_con_id);
		bVO.setBill_pay(bill_pay);
		bVO.setBill_date(bill_date);
		bVO.setBill_producetime(bill_producetime);
		bVO.setBill_status(bill_status);
		bVO.setBill_paymethod(bill_paymethod);
		bVO.setBill_paymenttype(bill_paymemttype);
		
		dao.insert(bVO);
		
		return bVO;
	}

	//預留給 Struts 2 用的
		public void addB(BillVO bVO) {
			dao.insert(bVO);
		}
	
	//修改
	public BillVO updateB(String bill_id , String ele_con_id , Integer bill_pay , Date bill_date , Date bill_producetime , String bill_status , String bill_paymethod , String bill_paymemttype) {
		
		BillVO bVO = new BillVO();
		
		bVO.setBill_id(bill_id);
		bVO.setEle_con_id(ele_con_id);
		bVO.setBill_pay(bill_pay);
		bVO.setBill_date(bill_date);
		bVO.setBill_producetime(bill_producetime);
		bVO.setBill_status(bill_status);
		bVO.setBill_paymethod(bill_paymethod);
		bVO.setBill_paymenttype(bill_paymemttype);
		
		dao.update(bVO);
		return bVO;
	}
	
	//預留給 Struts 2 用的
		public void updateB(BillVO bVO) {
			dao.update(bVO);
		}
		
		//刪除
		public void deleteB(String bill_id) {
			dao.delete(bill_id);
		}
		
		//單一查詢
		public BillVO getOneB(String bill_id) {
			return dao.findByPrimaryKey(bill_id);
		}
		
		//查詢全部
		public List<BillVO> getAll(){
			return dao.getAll();
		}
		
		public List<BillVO> getBillByEleContractId(String ele_contract_id){
			return dao.findByEleContractId(ele_contract_id);
		}
		
		public List<BillVO> getListByEleContractBetweenRentTime (String ele_contract_id){
			return dao.findByEleContractRentTime(ele_contract_id);
		}
//		public void addB(Connection con, List <BillVO> billVOlist ,String eleConKey) {
//			dao.insert(con, billVOlist, eleConKey);
//		}
}
