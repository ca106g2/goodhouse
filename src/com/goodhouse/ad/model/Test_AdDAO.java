//package com.goodhouse.ad.model;
//
//import java.sql.Date;
//import java.util.List;
//
//public class Test_AdDAO {
//	public static void main(String[] args) {
//		AdJDBCDAO dao = new AdJDBCDAO();
//		//新增
//		AdVO ar1 = new AdVO();
//		ar1.setHou_id("HOU0000001");
//		ar1.setLan_id("L000000001");
//		ar1.setAd_status("要");
//		ar1.setAd_statue("要");
//		ar1.setAd_sort_id("ADS0000001");
//		ar1.setAd_paymethod("1");
//		ar1.setAd_forfree("1");
//		Date addate = new Date(System.currentTimeMillis());
//		ar1.setAd_date(addate);
//		dao.insert(ar1);
//		
//		//修改
////		AdVO ar2 = new AdVO();
////		ar2.setAd_status("不要");
////		ar2.setAd_statue("不要");
////		ar2.setAd_sort_id("ADS0000001");
////		ar2.setAd_paymethod("2");
////		ar2.setAd_forfree("2");
////		Date addate = new Date(System.currentTimeMillis());
////		ar2.setAd_date(addate);
////		ar2.setAd_id("AD00000007");
////		dao.update(ar2);
//		
//		//刪除
//	
//		//單一查詢
////		AdVO ar4 = new AdVO();
////		ar4 = dao.findByPrimaryKey("AD00000007");
////		System.out.println(ar4.getAd_id());
////		System.out.println(ar4.getHou_id());
////		System.out.println(ar4.getLan_id());
////		System.out.println(ar4.getAd_sort_id());
////		System.out.println(ar4.getAd_statue());
////		System.out.println(ar4.getAd_status());
////		System.out.println(ar4.getAd_forfree());
////		System.out.println(ar4.getAd_paymethod());
////		System.out.println(ar4.getAd_date());
//		
//		//查詢全部
////		List<AdVO> list = dao.getAll();
////		for(AdVO ar5:list) {
////			System.out.println("Ad_id: "+ar5.getAd_id());
////			System.out.println("Hou_id: "+ar5.getHou_id());
////			System.out.println("Lan_id: "+ar5.getLan_id());
////			System.out.println("Ad_sort: "+ar5.getAd_sort_id());
////			System.out.println("Ad_statue: "+ar5.getAd_statue());
////			System.out.println("Ad_status: "+ar5.getAd_status());
////			System.out.println("Ad_forfree: "+ar5.getAd_forfree());
////			System.out.println("Ad_paymethod: "+ar5.getAd_paymethod());
////			System.out.println("Ad_date: "+ar5.getAd_date());
////			System.out.println();
////		}
//	}
//}
