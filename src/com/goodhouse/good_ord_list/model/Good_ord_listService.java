package com.goodhouse.good_ord_list.model;

import java.sql.Connection;
import java.util.List;

public class Good_ord_listService {
	
	private Good_ord_listDAO_interface dao;
	
	public Good_ord_listService() {
		dao = new Good_ord_listJNDIDAO();
	}
	
	public Good_ord_listVO addGood_ord_list(String good_id, String good_ord_id, Integer good_ord_amo) {
		
		Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
		good_ord_listVO.setGood_id(good_id);
		good_ord_listVO.setGood_ord_id(good_ord_id);
		good_ord_listVO.setGood_ord_amo(good_ord_amo);
		dao.insert(good_ord_listVO);
		
		return good_ord_listVO;
	}
	
	public Good_ord_listVO addGood_ord_list2(Good_ord_listVO good_ord_listVO, Connection con) {

		dao.insert2(good_ord_listVO, con);
		
		return good_ord_listVO;
	}
	
	public Good_ord_listVO updateGood_ord_list(String good_id, String good_ord_id, Integer good_ord_amo) {
		
		Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
		good_ord_listVO.setGood_id(good_id);
		good_ord_listVO.setGood_ord_id(good_ord_id);
		good_ord_listVO.setGood_ord_amo(good_ord_amo);
		dao.update(good_ord_listVO);
		
		return good_ord_listVO;
	}
	
	public void deleteGood_ord_list(String good_id, String good_ord_id) {
		dao.delete(good_id, good_ord_id);
	}
	
	public Good_ord_listVO getOneGood_ord_list(String good_id, String good_ord_id) {
		return dao.findByPrimaryKey(good_id, good_ord_id);
	}
	
	public List<Good_ord_listVO> getAll() {
		return dao.getAll();
	}
	
	public List<Good_ord_listVO> getPart(String good_ord_id) {
		return dao.getPart(good_ord_id);
	}
	
	public List<String> getDisid() {
		return dao.getDisId();
	}
}
