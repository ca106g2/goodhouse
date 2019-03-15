package com.goodhouse.bill.model;

import java.util.Date;
import java.util.List;

public class Test_BillJDBCDAO {

	public static void main(String[] args) {
		
		BillJDBCDAO dao = new BillJDBCDAO();
		
		BillVO bVO = new BillVO();
		
		//新增
//		bVO.setEle_con_id("ECON000002");
//		bVO.setBill_pay(5000);
//		bVO.setBill_date(new java.sql.Date(new Date("2019/03/16").getTime()));
//		bVO.setBill_producetime(new java.sql.Date(new Date("2019/02/16").getTime()));
//		bVO.setBill_status("S1待繳款");
//		bVO.setBill_paymethod("M1銀行轉帳");
//		bVO.setBill_paymenttype("P1月繳");
//		
//		dao.insert(bVO);
	
		//修改
//		bVO.setBill_id("20190219-B00012");
//		bVO.setEle_con_id("ECON000002");
//		bVO.setBill_pay(5000);
//		bVO.setBill_date(new java.sql.Date(new Date("2019/03/16").getTime()));
//		bVO.setBill_producetime(new java.sql.Date(new Date("2019/02/16").getTime()));
//		bVO.setBill_status("S2已繳款");
//		bVO.setBill_paymethod("M1銀行轉帳");
//		bVO.setBill_paymenttype("P1月繳");
//		
//		dao.update(bVO);
		
		//刪除
//		dao.delete("20190219-B00012");
		
		//單一查詢
//		bVO = dao.findByPrimaryKey("20190218-B00011");
//		System.out.println(bVO.getBill_id());
//		System.out.println(bVO.getEle_con_id());
//		System.out.println(bVO.getBill_pay());
//		System.out.println(bVO.getBill_date());
//		System.out.println(bVO.getBill_producetime());
//		System.out.println(bVO.getBill_status());
//		System.out.println(bVO.getBill_paymethod());
//		System.out.println(bVO.getBill_paymenttype());
		
		System.out.println("----------------------------------------");
		
		//查詢全部
		
		List<BillVO> list = dao.getAll();
		
		for(BillVO abVO : list) {
			
			System.out.println(abVO.getBill_id() + ",");
			System.out.println(abVO.getEle_con_id() + ",");
			System.out.println(abVO.getBill_pay() + ",");
			System.out.println(abVO.getBill_date() + ",");
			System.out.println(abVO.getBill_producetime() + ",");
			System.out.println(abVO.getBill_status() + ",");
			System.out.println(abVO.getBill_paymethod() + ",");
			System.out.println(abVO.getBill_paymenttype() + ",");
			System.out.println();
		}
		
		
		
	}

}
