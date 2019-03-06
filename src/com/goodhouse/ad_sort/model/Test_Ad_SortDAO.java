package com.goodhouse.ad_sort.model;

import java.util.List;

public class Test_Ad_SortDAO {
	public static void main(String[] args) {
		Ad_sortJDBCDAO dao= new Ad_sortJDBCDAO();
		
		//新增
//		Ad_SortVO ar1 = new Ad_SortVO();
//		ar1.setAd_forfree("免費");
//		ar1.setAd_chargetype("包夜");
//		ar1.setAd_charge(2500);
//		dao.insert(ar1);
		
		//修改
//		Ad_SortVO ar2 = new Ad_SortVO();
//		ar2.setAd_forfree("要錢");
//		ar2.setAd_chargetype("包天");
//		ar2.setAd_charge(0);
//		ar2.setAd_sort_id("ADS0000001");
//		dao.update(ar2);
		
		//刪除
//		String ar3 ="ADS0000002" ;
//		dao.delete(ar3);
		
		//單一查詢
//		Ad_SortVO ar4 = new Ad_SortVO();
//		ar4 = dao.findByPrimaryKey("ADS0000001");
//		System.out.println(ar4.getAd_sort_id());
//		System.out.println(ar4.getAd_forfree());
//		System.out.println(ar4.getAd_chargetype());
//		System.out.println(ar4.getAd_charge());
		
		//查詢全部
		List<Ad_sortVO> list = dao.getAll();
		for(Ad_sortVO ar5 :list) {
		System.out.println(ar5.getAd_sort_id());
		System.out.println(ar5.getAd_forfree());
		System.out.println(ar5.getAd_chargetype());
		System.out.println(ar5.getAd_charge());
		System.out.println();
		}
	}
}
