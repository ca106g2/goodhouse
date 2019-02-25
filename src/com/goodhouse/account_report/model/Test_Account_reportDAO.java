package com.goodhouse.account_report.model;

import java.sql.Date;
import java.util.List;

public class Test_Account_reportDAO {
	public static void main(String[] args) {
	Account_reportJDBCDAO dao = new Account_reportJDBCDAO();
	
	
	//新增
//	Account_reportVO ar1 = new Account_reportVO();
//	ar1.setEmp_id("E000000001");
//	ar1.setMem_id("M000000001");
//	ar1.setLan_id("L000000001");
//	ar1.setAcc_rep_reason("就是不跟你說");
//	ar1.setAcc_rep_status("迷霧");
//	Date accropdate = new Date(System.currentTimeMillis());
//	ar1.setAcc_rep_date(accropdate);
//	dao.insert(ar1);
	
	//更新
//	Account_reportVO ar2 = new Account_reportVO();
//	ar2.setAcc_rep_reason("就是要跟你說");
//	ar2.setAcc_rep_status("光明");
//	Date accropdate2 = new Date(System.currentTimeMillis());
//	ar2.setAcc_rep_date(accropdate2);
//	ar2.setAcc_rep_id("ACR0000005");
//	dao.update(ar2);
	
	//刪除
//	Account_reportVO ar3 = new Account_reportVO();
//	ar3.setAcc_rep_id("ACR0000001");
//	dao.delete(ar3);
	
	//單一查詢 
//	Account_reportVO ar4 = new Account_reportVO();
//	ar4 = dao.findByPrimaryKey("ACR0000005");
//	System.out.println("Acc_rep_id:"+ar4.getAcc_rep_id());
//	System.out.println("Emp_id:"+ar4.getEmp_id());
//	System.out.println("Mem_id:"+ar4.getMem_id());
//	System.out.println("Lan_id:"+ar4.getLan_id());
//	System.out.println("Acc_rep_status:"+ar4.getAcc_rep_status());
//	System.out.println("Acc_rep_reason:"+ar4.getAcc_rep_reason());
//	System.out.println("Acc_rep_date:"+ar4.getAcc_rep_date());
	
	
	//查全部
	List<Account_reportVO> list =dao.getAll();
	for(Account_reportVO ar5 : list ) {
		System.out.println("Acc_rep_id:"+ar5.getAcc_rep_id()+",");
		System.out.println("Emp_id:"+ar5.getEmp_id()+",");
		System.out.println("Mem_id:"+ar5.getMem_id()+",");
		System.out.println("Lan_id:"+ar5.getLan_id()+",");
		System.out.println("Acc_rep_status:"+ar5.getAcc_rep_status()+",");
		System.out.println("Acc_rep_reason:"+ar5.getAcc_rep_reason()+",");
		System.out.println("Acc_rep_date:"+ar5.getAcc_rep_date()+",");
		System.out.println();
	}
	}
}
	
