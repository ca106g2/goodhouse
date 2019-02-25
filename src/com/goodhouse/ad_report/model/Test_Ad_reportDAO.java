package com.goodhouse.ad_report.model;

import java.sql.Date;
import java.util.List;

public class Test_Ad_reportDAO {
	public static void main(String[] args) {
		Ad_reportJDBCDAO dao = new Ad_reportJDBCDAO();
		//更新
//		Ad_reportVO ar1= new Ad_reportVO();
//		ar1.setAd_id("AD00000001");
//		ar1.setEmp_id("E000000001");
//		ar1.setMem_id("M000000001");
//		ar1.setAd_rep_reason("請給問題");
//		ar1.setAd_rep_status("未知");
//		Date adreportdate = new Date(System.currentTimeMillis());
//		ar1.setAd_rep_date(adreportdate);
//		dao.insert(ar1);
		//修改
//		Ad_reportVO ar2 = new Ad_reportVO();
//		ar2.setAd_rep_reason("問題");
//		ar2.setAd_rep_status("無知");
//		Date adreportdate2 = new Date(System.currentTimeMillis());
//		ar2.setAd_rep_date(adreportdate2);
//		ar2.setAd_rep_id("ADR0000004");
//		dao.update(ar2);
		//刪除
//		Ad_reportVO ar3 = new Ad_reportVO();
//		ar3.setAd_rep_id("ADR000002");
//		dao.delete(ar3);
		//單一查詢
//		Ad_reportVO ar4 = new Ad_reportVO();
//		ar4 = dao.findByPrimaryKey("ADR0000004");
//		System.out.println(ar4.getAd_rep_id());
//		System.out.println(ar4.getAd_id());
//		System.out.println(ar4.getEmp_id());
//		System.out.println(ar4.getMem_id());
//		System.out.println(ar4.getAd_rep_status());
//		System.out.println(ar4.getAd_rep_reason());
//		System.out.println(ar4.getAd_rep_date());
		//全部查詢
		List<Ad_reportVO> list =dao.getAll();
		for(Ad_reportVO ar5 : list) {
			System.out.println("Ad_rep_id :"+ar5.getAd_rep_id());
			System.out.println("Ad_id :"+ar5.getAd_id());
			System.out.println("Emp_id :"+ar5.getEmp_id());
			System.out.println("Mem_id :"+ar5.getMem_id());
			System.out.println("Ad_rep_status :"+ar5.getAd_rep_status());
			System.out.println("Ad_rep_reason :"+ar5.getAd_rep_reason());
			System.out.println("Ad_rep_date :"+ar5.getAd_rep_date());
			System.out.println();
		}
	}
}
