package com.goodhouse.house_evaluate.model;

import java.util.List;

public class House_EvaluateService {
	
	private House_EvaluateDAO_interface dao;
	
	public House_EvaluateService() {
		dao = new House_EvaluateJNDIDAO();
	}
	
	//新增
	public House_EvaluateVO addHE(String mem_id,String hou_id,String hou_eva_grade,String hou_eva_content) {
		
		House_EvaluateVO heVO = new House_EvaluateVO();
		
		heVO.setMem_id(mem_id);
		heVO.setHou_id(hou_id);
		heVO.setHou_eva_grade(hou_eva_grade);
		heVO.setHou_eva_content(hou_eva_content);
		dao.insert(heVO);
		
		return heVO;
	}
	
	//預留給 Struts 2 用的
	public void addHE(House_EvaluateVO heVO) {
		dao.insert(heVO);
	}
	
	//修改
	public House_EvaluateVO updateHE(String hou_eva_id,String mem_id,String hou_id,String hou_eva_grade,String hou_eva_content) {
		
		House_EvaluateVO heVO = new House_EvaluateVO();
		
		heVO.setHou_eva_id(hou_eva_id);
		heVO.setMem_id(mem_id);
		heVO.setHou_id(hou_id);
		heVO.setHou_eva_grade(hou_eva_grade);
		heVO.setHou_eva_content(hou_eva_content);
		
		dao.update(heVO);
		return heVO;
	}
	
	//預留給 Struts 2 用的
	public void updateHE(House_EvaluateVO heVO) {
		dao.update(heVO);
	}
	
	//刪除
	public void deleteHE(String hou_eva_id) {
		dao.delete(hou_eva_id);
	}
	
	//單一查詢
	public House_EvaluateVO getOneHE(String hoe_eva_id) {
		return dao.findByPrimmaryKey(hoe_eva_id);
	}
	
	//查詢全部
	public List<House_EvaluateVO> getAll(){
		return dao.getAll();
	} 
	
	public List<House_EvaluateVO> getListByHouId(String hou_id){
		return dao.getListByHouId(hou_id);
	}
}
