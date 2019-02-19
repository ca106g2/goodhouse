package com.goodhouse.ele_contract.model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Test_Ele_ContractJDBCDAO {

	public static void main(String[] args) {
		
		Ele_ContractJDBCDAO dao = new Ele_ContractJDBCDAO();
		
		Ele_ContractVO ecVO = new Ele_ContractVO();
		
		//新增
		ecVO.setCon_id("CON0000002");
		ecVO.setMem_id("M000000005");
		ecVO.setMem_idnumber("P123456755");
		ecVO.setLan_id("L000000002");
		ecVO.setLan_idnumber("Q123456589");
		ecVO.setHou_id("HOU0000005");
		ecVO.setEle_rent_money(5000);
		ecVO.setEle_deposit_money(10000);
		ecVO.setEle_rent_time(12);
		ecVO.setEle_rent_f_day(new java.sql.Date(new Date("2019/02/16").getTime()));
		ecVO.setEle_rent_l_day(new java.sql.Date(new Date("2019/02/16").getTime()));
		ecVO.setEle_singdate(new java.sql.Date(new Date("2019/02/16").getTime()));
		ecVO.setEle_con_status("S2已簽約");
		ecVO.setBill_paymenttype("P1月繳");
		ecVO.setEle_con_note("null");
		
		dao.insert(ecVO);
		
		//修改
//		Ele_ContractVO ecVO2 = new Ele_ContractVO();
//		ecVO2.setEle_con_id("ECON000010");
//		ecVO2.setCon_id("CON0000002");
//		ecVO2.setMem_id("M000000006");
//		ecVO2.setMem_idnumber("P123456759");
//		ecVO2.setLan_id("L000000002");
//		ecVO2.setLan_idnumber("Q123456589");
//		ecVO2.setHou_id("HOU0000010");
//		ecVO2.setEle_rent_money(5000);
//		ecVO2.setEle_deposit_money(10000);
//		ecVO2.setEle_rent_time(12);
//		ecVO2.setEle_rent_f_day(new java.sql.Date(new Date("2019/02/15").getTime()));
//		ecVO2.setEle_rent_l_day(new java.sql.Date(new Date("2019/02/15").getTime()));
//		ecVO2.setEle_singdate(new java.sql.Date(new Date("2019/02/15").getTime()));
//		ecVO2.setEle_con_status("S2已簽約");
//		ecVO2.setBill_paymenttype("P1月繳");
//		ecVO2.setEle_con_note("測試");
//		
//		dao.update(ecVO2);
		
		//刪除
//		dao.delete("ECON000010");
		
		//單一查詢
//		Ele_ContractVO ecVO3 = dao.findByPrimaryKey("ECON000004");
//		System.out.println(ecVO3.getEle_con_id());
//		System.out.println(ecVO3.getCon_id());
//		System.out.println(ecVO3.getMem_id());
//		System.out.println(ecVO3.getMem_idnumber());
//		System.out.println(ecVO3.getLan_id());
//		System.out.println(ecVO3.getLan_idnumber());
//		System.out.println(ecVO3.getHou_id());
//		System.out.println(ecVO3.getEle_rent_money());
//		System.out.println(ecVO3.getEle_deposit_money());
//		System.out.println(ecVO3.getEle_rent_time());
//		System.out.println(ecVO3.getEle_rent_f_day());
//		System.out.println(ecVO3.getEle_rent_l_day());
//		System.out.println(ecVO3.getEle_singdate());
//		System.out.println(ecVO3.getEle_con_status());
//		System.out.println(ecVO3.getBill_paymenttype());
//		System.out.println(ecVO3.getEle_con_note());
	
		System.out.println("------------------------------------------");
	
		//查詢全部
	
	}
	

}
