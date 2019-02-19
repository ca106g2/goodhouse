package com.goodhouse.apply_conturct.model;

import java.util.List;

import sun.awt.AppContext;

public class Apply_ConturctService {

	private Apply_ConturctDAO_interface dao;
	
	public Apply_ConturctService() {
		dao = new Apply_ConturctJDBCDAO();
	}
	
	//新增
	public Apply_ConturctVO addAppC(String ele_con_id , String mem_id , String hou_id , String app_con_content , String app_con_status , String app_con_other) {
		
		Apply_ConturctVO appCVO = new Apply_ConturctVO();
		
		appCVO.setEle_con_id(ele_con_id);
		appCVO.setMem_id(mem_id);
		appCVO.setHou_id(hou_id);
		appCVO.setApp_con_content(app_con_content);
		appCVO.setApp_con_status(app_con_status);
		appCVO.setApp_con_other(app_con_other);
		dao.insert(appCVO);
		
		return appCVO;
	}
	
	//預留給 Struts 2 用的
	public void addAppC(Apply_ConturctVO appCVO) {
		dao.insert(appCVO);
	}
	
	//修改
	public Apply_ConturctVO updateAppC(String app_con_id , String ele_con_id , String mem_id ,String hou_id  , String app_con_content , String app_con_status ,String app_con_other ) {
		
		Apply_ConturctVO appCVO = new Apply_ConturctVO();
		
		appCVO.setApp_con_id(app_con_id);
		appCVO.setEle_con_id(ele_con_id);
		appCVO.setMem_id(mem_id);
		appCVO.setHou_id(hou_id);
		appCVO.setApp_con_content(app_con_content);
		appCVO.setApp_con_status(app_con_status);
		appCVO.setApp_con_other(app_con_other);
		
		dao.update(appCVO);
		
		return appCVO;
	}
	
	//預留給 Struts 2 用的
	public void updateAppC(Apply_ConturctVO appCVO) {
		dao.update(appCVO);
	}
	
	//刪除
	public void deleteAppC(String app_con_id) {
		dao.delete(app_con_id);
	}
	
	//單一查詢
	public Apply_ConturctVO getOneAppC(String app_con_id) {
		return dao.findByPrimaryKey(app_con_id);
	}
	
	//查詢全部
	public List<Apply_ConturctVO> getAll(){
		return dao.getAll();
	}
}
