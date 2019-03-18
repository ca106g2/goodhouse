package com.goodhouse.house_track.model;

import java.util.List;

public class House_TrackService {
	
	private House_TrackDAO_interface dao;
	
	public House_TrackService() {
		dao = new House_TrackJNDIDAO();
	}
	
	//新增
	public House_TrackVO addHT(String mem_id,String hou_id) {
		
		House_TrackVO houTraVO = new House_TrackVO();
		houTraVO.setHou_id(hou_id);
		houTraVO.setMem_id(mem_id);
		dao.insert(houTraVO);
		
		return houTraVO;
	}
	
	//預留給 Struts 2 用的
	public void addHT(House_TrackVO htVO) {
		dao.insert(htVO);
	}
	
	//修改
	public House_TrackVO updateHT(String hou_tra_id,String mem_id,String hou_id,String hou_tra_status) {
		
		House_TrackVO houTraVO = new House_TrackVO();
		
		houTraVO.setHou_tra_id(hou_tra_id);
		houTraVO.setHou_id(hou_id);
		houTraVO.setMem_id(mem_id);
		dao.update(houTraVO);
		
		return houTraVO;
	}
	
	//預留給 Struts 2 用的
	public void updateHT(House_TrackVO htVO) {
		dao.update(htVO);
	}
	//刪除
	public void deleteHT(String hou_tra_id) {
		dao.delete(hou_tra_id);
	}
	
	//單一查詢
	public House_TrackVO getOneHT(String hou_tra_id) {
		return dao.findByPrimaryKey(hou_tra_id);
		
	}
	
	//查詢全部
	public List<House_TrackVO> getAll(){
		return dao.getAll();
	}
	
	//查詢某會員的所有追蹤
	public List<House_TrackVO> getListByMemId(String mem_id){
		return dao.getListByMemId(mem_id);
	}
	//查詢單一房屋被某會員追蹤
	public House_TrackVO findByHouIdAndMem_id(String hou_id,String mem_id){
		return dao.findByHouIdAndMem_id(hou_id, mem_id);
	}
}
