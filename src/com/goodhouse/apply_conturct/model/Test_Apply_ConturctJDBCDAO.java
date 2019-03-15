package com.goodhouse.apply_conturct.model;

import java.util.List;

public class Test_Apply_ConturctJDBCDAO {

	public static void main(String[] args) {
		
		Apply_ConturctJDBCDAO dao = new Apply_ConturctJDBCDAO();
		
		Apply_ConturctVO appcVO = new Apply_ConturctVO();
		
		//新增
//		appcVO.setEle_con_id("ECON000001");
//		appcVO.setMem_id("M000000001");
//		appcVO.setHou_id("HOU0000001");
//		appcVO.setApp_con_content("A3租金延期");
//		appcVO.setApp_con_status("S1待處理");
//		appcVO.setApp_con_other("");
//		
//		dao.insert(appcVO);
		
		//修改
//		Apply_ConturctVO appcVO2 = new Apply_ConturctVO();
//		
//		appcVO2.setApp_con_id("APPC000010");
//		appcVO2.setEle_con_id("ECON000010");
//		appcVO2.setMem_id("M000000006");
//		appcVO2.setHou_id("HOU0000010");
//		appcVO2.setApp_con_content("A1解約");
//		appcVO2.setApp_con_status("S2已處理");
//		appcVO2.setApp_con_other("");
//		
//		dao.update(appcVO2);
		
		//刪除
		dao.delete("");
		
		//單一查詢
//		Apply_ConturctVO appcVO1 = dao.findByPrimaryKey("APPC000008");
//		
//		System.out.println(appcVO1.getApp_con_id());
//		System.out.println(appcVO1.getEle_con_id());
//		System.out.println(appcVO1.getMem_id());
//		System.out.println(appcVO1.getHou_id());
//		System.out.println(appcVO1.getApp_con_content());
//		System.out.println(appcVO1.getApp_con_status());
//		System.out.println(appcVO1.getApp_con_other());
//		
		System.out.println("--------------------------------------");
		
		//查詢全部
//		List<Apply_ConturctVO> list = dao.getAll();
//		
//		for (Apply_ConturctVO aAppcVO : list) {
//			
//			System.out.println(aAppcVO.getApp_con_id() + ",");
//			System.out.println(aAppcVO.getEle_con_id() + ",");
//			System.out.println(aAppcVO.getMem_id() + ",");
//			System.out.println(aAppcVO.getHou_id() + ",");
//			System.out.println(aAppcVO.getApp_con_content() + ",");
//			System.out.println(aAppcVO.getApp_con_status() + ",");
//			System.out.println(aAppcVO.getApp_con_other() + ",");
//			System.out.println();
//		}
		
		List<Apply_ConturctVO>	list = dao.getApplyListByHou_id("HOU0000001");
		for (Apply_ConturctVO aAppcVO : list) {
			
			System.out.println(aAppcVO.getApp_con_id() + ",");
			System.out.println(aAppcVO.getEle_con_id() + ",");
			System.out.println(aAppcVO.getMem_id() + ",");
			System.out.println(aAppcVO.getHou_id() + ",");
			System.out.println(aAppcVO.getApp_con_content() + ",");
			System.out.println(aAppcVO.getApp_con_status() + ",");
			System.out.println(aAppcVO.getApp_con_other() + ",");
			System.out.println();
		}
		
	}

}
