package com.goodhouse.good_ord.model;

import java.sql.Timestamp;
import java.util.List;

import com.goodhouse.pointgoods.model.PointgoodsVO;

public class Good_ordService {
	private Good_ordDAO_interface dao;
	
	public Good_ordService() {
		dao = new Good_ordJNDIDAO();
	}
	
	public Good_ordVO addGood_ord(String mem_id, Timestamp good_ord_dat, String good_ord_sta, String good_ord_nam, String good_ord_add) {
		
		Good_ordVO good_ordVO = new Good_ordVO();
		good_ordVO.setMem_id(mem_id);
		good_ordVO.setGood_ord_dat(good_ord_dat);
		good_ordVO.setGood_ord_sta(good_ord_sta);
		good_ordVO.setGood_ord_nam(good_ord_nam);
		good_ordVO.setGood_ord_add(good_ord_add);
		dao.insert(good_ordVO);
		
		return good_ordVO;
	}
	
	public Good_ordVO addGood_ord2(String mem_id, Timestamp good_ord_dat, String good_ord_sta, String good_ord_nam, Integer good_ord_tot, String good_ord_add, List<PointgoodsVO> list) {
		
		Good_ordVO good_ordVO = new Good_ordVO();
		good_ordVO.setMem_id(mem_id);
		good_ordVO.setGood_ord_dat(good_ord_dat);
		good_ordVO.setGood_ord_sta(good_ord_sta);
		good_ordVO.setGood_ord_nam(good_ord_nam);
		good_ordVO.setGood_ord_tot(good_ord_tot);
		good_ordVO.setGood_ord_add(good_ord_add);
		dao.inserOrder(good_ordVO, list);
		
		return good_ordVO;
	}
	
	public Good_ordVO updateGood_ord(String good_ord_id, String mem_id, Timestamp good_ord_dat, String good_ord_sta, String good_ord_nam, Integer good_ord_tot, String good_ord_add) {
		
		Good_ordVO good_ordVO = new Good_ordVO();
		good_ordVO.setGood_ord_id(good_ord_id);
		good_ordVO.setMem_id(mem_id);
		good_ordVO.setGood_ord_dat(good_ord_dat);
		good_ordVO.setGood_ord_sta(good_ord_sta);
		good_ordVO.setGood_ord_nam(good_ord_nam);
		good_ordVO.setGood_ord_tot(good_ord_tot);
		good_ordVO.setGood_ord_add(good_ord_add);
		dao.update(good_ordVO);
		
		return good_ordVO;
	}
	
	public void deleteGood_ord(String good_ord_id) {
		dao.delete(good_ord_id);
	}
	
	public Good_ordVO getOneGood_ord(String good_ord_id) {
		return dao.findByPrimaryKey(good_ord_id);
	}
	
	public List<Good_ordVO> getPartGood_ord(String mem_id) {
		return dao.findByMem_id(mem_id);
	}
	
	public List<Good_ordVO> getAll() {
		return dao.getAll();
	}
}
