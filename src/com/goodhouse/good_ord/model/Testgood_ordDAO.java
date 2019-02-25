package com.goodhouse.good_ord.model;

import java.util.*;
import java.sql.Date;
import java.sql.Timestamp;
public class Testgood_ordDAO {

	public static void main(String[] args) {
		Good_ordJDBCDAO dao = new Good_ordJDBCDAO();
		
		// 新增
//		Good_ordVO good_ordVO = new Good_ordVO();
//		good_ordVO.setMem_id("M000000001");
//		Timestamp orderdate = new Timestamp(System.currentTimeMillis());
//		good_ordVO.setGood_ord_dat(orderdate);
//		good_ordVO.setGood_ord_sta("O001");
//		good_ordVO.setGood_ord_nam("DAVID");
//		good_ordVO.setGood_ord_add("桃園市中壢區中央大學1號1樓");
//		
//		dao.insert(good_ordVO);
		
		// 修改
//		Good_ordVO good_ordVO = new Good_ordVO();
//		Timestamp updatedate = new Timestamp(System.currentTimeMillis());
//		good_ordVO.setGood_ord_dat(updatedate);
//		good_ordVO.setGood_ord_sta("GO002");
//		good_ordVO.setGood_ord_nam("Rick");
//		good_ordVO.setGood_ord_add("新北市樹林區文化街5巷11號1樓");
//		good_ordVO.setGood_ord_id("O000000011");
//		
//		dao.update(good_ordVO);
		
		// 刪除
//		dao.delete("O000000011");
		
		// 查詢
//		Good_ordVO good_ordVO = new Good_ordVO();
//		
//		good_ordVO = dao.findByPrimaryKey("O000000002");
//		System.out.println(good_ordVO.getGood_ord_id());
//		System.out.println(good_ordVO.getMem_id());
//		System.out.println(good_ordVO.getGood_ord_dat());
//		System.out.println(good_ordVO.getGood_ord_sta());
//		System.out.println(good_ordVO.getGood_ord_nam());
//		System.out.println(good_ordVO.getGood_ord_add());
		
		// 查詢全部
		List<Good_ordVO> list = dao.getAll();
		for(Good_ordVO good_ordVO : list) {
			System.out.print(good_ordVO.getGood_ord_id() + ",");
			System.out.print(good_ordVO.getMem_id() + ",");
			System.out.print(good_ordVO.getGood_ord_dat() + ",");
			System.out.print(good_ordVO.getGood_ord_sta() + ",");
			System.out.print(good_ordVO.getGood_ord_nam() + ",");
			System.out.print(good_ordVO.getGood_ord_add() + ",");
			System.out.println();
		}
	}

}
