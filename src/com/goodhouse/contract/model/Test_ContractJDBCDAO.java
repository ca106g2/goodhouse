package com.goodhouse.contract.model;

import java.util.List;

public class Test_ContractJDBCDAO {

	public static void main(String[] args) {
		
		ContractJDBCDAO conDAO = new ContractJDBCDAO();
		
		ContractVO conVO = new ContractVO();
		ContractVO conVO1 = new ContractVO(); 
		
		//新增
		conVO.setCon_name("套房合約");
		conVO.setCon_content("套房合約內容");	
		
		conVO1.setCon_name("公寓合約");
		conVO1.setCon_content("公寓合約內容");
		
		conDAO.insert(conVO);
		conDAO.insert(conVO1);
		
		//修改
//		ContractVO conVO3 = new ContractVO();
//		conVO3.setCon_id("1");
//		conVO3.setCon_name("套房合約");
//		conVO3.setCon_content("套房合約內容");
//		conDAO.update(conVO3);
		
		//刪除
//		conDAO.delete("2");

		//單一查詢
//		ContractVO conVO2 = conDAO.findByPrimaryKey("1");
//		System.out.println(conVO2.getCon_id());
//		System.out.println(conVO2.getCon_name());
//		System.out.println(conVO2.getCon_content());
//		
//		System.out.println("-----------------------------------");
		
		//查詢
//		List<ContractVO> list = conDAO.getAll();
//		for(ContractVO aConVO : list) {
//			System.out.println(aConVO.getCon_id() + ",");
//			System.out.println(aConVO.getCon_name() + ",");
//			System.out.println(aConVO.getCon_content() + ",");
//			System.out.println();
//		}
//		
	}

}
