package com.goodhouse.keyword.model;

import java.util.List;

public class Test_KeyWordJDBCDAO {

	public static void main(String[] args) {
		
		KeyWordJDBCDAO dao = new KeyWordJDBCDAO(); 
		
		KeyWordVO kwVO = new KeyWordVO();
		
		
		//新增
//		kwVO.setKw_keyword("找出租");
//		kwVO.setKw_count(1);
//		dao.insert(kwVO);
		
		//修改
//		KeyWordVO kwVO2 = new KeyWordVO();
//		
//		kwVO2.setKw_id("3");	
//		kwVO2.setKw_keyword("透天修改");
//		kwVO2.setKw_count(3);
//		dao.update(kwVO2);
		
		//刪除
//		dao.delete("2");
		
		//單一查詢
//		KeyWordVO kwVO3 = dao.findByPrimaryKey("3");
//		System.out.println(kwVO3.getKw_id());
//		System.out.println(kwVO3.getKw_keyword());
//		System.out.println(kwVO3.getKw_count());
//		
//		System.out.println("----------------------");
		
		//查詢全部
		List<KeyWordVO> list = dao.getAll();
		
		for(KeyWordVO aKeyWordVO : list) {
			System.out.println(aKeyWordVO.getKw_id() + ",");
			System.out.println(aKeyWordVO.getKw_keyword() + ",");
			System.out.println(aKeyWordVO.getKw_count() + ",");
			System.out.println();
		}
		
	}

}
