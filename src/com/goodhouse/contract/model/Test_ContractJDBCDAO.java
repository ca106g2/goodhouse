package com.goodhouse.contract.model;

import java.util.List;

import com.goodhouse.contract.model.ContractJDBCDAO;
import com.goodhouse.contract.model.ContractVO;

public class Test_ContractJDBCDAO {
	
	public static void main(String[] args) {
		
		ContractJDBCDAO dao = new ContractJDBCDAO();
		
		ContractVO cVO = new ContractVO();
		
		//新增
//		cVO.setCon_name("豪宅合約");
//		cVO.setCon_content("豪宅合約內容");
//		dao.insert(cVO);
		
		//修改
//		cVO.setCon_name("套房合約");
//		cVO.setCon_content("套房合約內容修改");
//		cVO.setCon_id("CON0000001");
//		dao.update(cVO);
		
		//刪除
		dao.delete("CON0000006");
		
		//單一查詢
		
//		ContractVO cVo2 = dao.findByPrimaryKey("CON0000005");
//		System.out.println(cVo2.getCon_id());
//		System.out.println(cVo2.getCon_name());
//		System.out.println(cVo2.getCon_content());
		
		System.out.println("------------------------------");
		
		//查詢全部
		
//		List<ContractVO> list = dao.getAll();
//		
//		for(ContractVO aVo : list) {
//			System.out.println(aVo.getCon_id() + ",");
//			System.out.println(aVo.getCon_name() + ",");
//			System.out.println(aVo.getCon_content() + ",");
//			System.out.println();
//		}
		
	}
}
