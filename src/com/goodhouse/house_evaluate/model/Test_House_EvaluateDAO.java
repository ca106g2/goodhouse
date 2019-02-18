package com.goodhouse.house_evaluate.model;

import java.util.List;

import com.sun.org.apache.bcel.internal.util.SecuritySupport;

public class Test_House_EvaluateDAO {

	public static void main(String[] args) {
		
		House_EvaluateJDBCDAO dao = new House_EvaluateJDBCDAO();
		
		House_EvaluateVO heVO = new House_EvaluateVO();
	
		//新增
//		heVO.setMem_id("M000000001");
//		heVO.setHou_id("HOU0000002");
//		heVO.setHou_eva_grade("G5非常好");
//		heVO.setHou_eva_content("房東超級貼心");
//		dao.insert(heVO);
		
		//修改
		heVO.setHou_eva_id("EVA0000001");
		heVO.setMem_id("M000000001");
		heVO.setHou_id("HOU0000001");
		heVO.setHou_eva_grade("G1非常不好");
		heVO.setHou_eva_content("房子壁癌超級嚴重");
		
		dao.update(heVO);
		
		//刪除
//		dao.delete("EVA0000010");
		
		//單一查詢
		
//		House_EvaluateVO heVO1 = dao.findByPrimmaryKey("EVA0000005");
//		System.out.println(heVO1.getHou_eva_id());
//		System.out.println(heVO1.getMem_id());
//		System.out.println(heVO1.getHou_id());
//		System.out.println(heVO1.getHou_eva_grade());
//		System.out.println(heVO1.getHou_eva_content());
		
		System.out.println("------------------------------------------");
		
		//查詢全部
		
//		List<House_EvaluateVO> list = dao.getAll();
//		
//		for(House_EvaluateVO aheVO : list) {
//			System.out.println(aheVO.getHou_eva_id() + ",");
//			System.out.println(aheVO.getMem_id() + ",");
//			System.out.println(aheVO.getHou_id() + ",");
//			System.out.println(aheVO.getHou_eva_grade() + ",");
//			System.out.println(aheVO.getHou_eva_content() + ",");
//			System.out.println();
//		}
		
	}

}
