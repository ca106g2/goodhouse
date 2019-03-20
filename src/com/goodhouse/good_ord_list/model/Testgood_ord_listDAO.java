package com.goodhouse.good_ord_list.model;

import java.util.*;

public class Testgood_ord_listDAO {

	public static void main(String[] args) {
		Good_ord_listJDBCDAO dao = new Good_ord_listJDBCDAO();
		
		// 新增
//		Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
//		good_ord_listVO.setGood_id("P000000003");
//		good_ord_listVO.setGood_ord_id("O000000003");
//		good_ord_listVO.setGood_ord_amo(100);
//		
//		dao.insert(good_ord_listVO);
		
		// 修改
//		Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
//		good_ord_listVO.setGood_ord_amo(300);
//		good_ord_listVO.setGood_id("P000000003");
//		good_ord_listVO.setGood_ord_id("O000000003");
//		
//		dao.update(good_ord_listVO);
		
		// 刪除
		
//		dao.delete("P000000003", "O000000003");
		
		// 查詢
//		Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
//		
//		good_ord_listVO = dao.findByPrimaryKey("P000000008", "O000000001");
//		System.out.println(good_ord_listVO.getGood_id());
//		System.out.println(good_ord_listVO.getGood_ord_id());
//		System.out.println(good_ord_listVO.getGood_ord_amo());
		
		// 查詢全部
		List<Good_ord_listVO> list = dao.getAll();
		for(Good_ord_listVO good_ord_listVO : list) {
			System.out.print(good_ord_listVO.getGood_id() + ",");
			System.out.print(good_ord_listVO.getGood_ord_id() + ",");
			System.out.print(good_ord_listVO.getGood_ord_amo() + ",");
			System.out.println();
		}
	}
}
