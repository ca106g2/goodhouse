package com.goodhouse.favorite_good.model;

import java.util.*;

public class Testfavorite_goodDAO {

	public static void main(String[] args) {
		Favorite_goodJDBCDAO dao = new Favorite_goodJDBCDAO();
		
		// 新增
//		Favorite_goodVO favorite_goodVO = new Favorite_goodVO();
//		favorite_goodVO.setMem_id("M000000004");
//		favorite_goodVO.setGood_id("P000000002");
//		
//		dao.insert(favorite_goodVO);
		
		// 刪除
		
//		dao.delete("M000000004", "P000000002");
		
		// 查詢
//		Favorite_goodVO favorite_goodVO = new Favorite_goodVO();
//		
//		favorite_goodVO = dao.findByPrimaryKey("M000000001", "P000000001");
//		System.out.println(favorite_goodVO.getMem_id());
//		System.out.println(favorite_goodVO.getGood_id());
		
		// 查詢全部
		List<Favorite_goodVO> list = dao.getAll();
		for(Favorite_goodVO favorite_goodVO : list) {
			System.out.print(favorite_goodVO.getMem_id() + ",");
			System.out.print(favorite_goodVO.getGood_id() + ",");
			System.out.println();
		}
	}
}
