package com.goodhouse.good_record.model;

import java.sql.Timestamp;
import java.util.List;

public class Good_recordService {

	private Good_recordDAO_interface dao;
	
	public Good_recordService() {
		dao = new Good_recordJNDIDAO();
	}
	
	public Good_recordVO addGood_record(String mem_id, String good_rec_des, Integer good_rec_poi, Timestamp good_rec_dat) {
		
		Good_recordVO good_recordVO = new Good_recordVO();
		good_recordVO.setMem_id(mem_id);
		good_recordVO.setGood_rec_des(good_rec_des);
		good_recordVO.setGood_rec_poi(good_rec_poi);
		good_recordVO.setGood_rec_dat(good_rec_dat);
		dao.insert(good_recordVO);
		
		return good_recordVO;
	}
	
	public void deleteGood_record(String good_rec_id) {
		dao.delete(good_rec_id);
	}
	
	public Good_recordVO getOneGood_record(String good_rec_id) {
		return dao.findByPrimaryKey(good_rec_id);
	}
	
	public List<Good_recordVO> getAll() {
		return dao.getAll();
	}
	
	public List<Good_recordVO> getPart(String mem_id) {
		return dao.getPart(mem_id);
	}
	
	public List<String> getDisid() {
		return dao.getDisId();
	}
}
