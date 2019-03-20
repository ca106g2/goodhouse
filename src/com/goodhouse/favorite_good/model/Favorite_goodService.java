package com.goodhouse.favorite_good.model;

import java.util.List;

public class Favorite_goodService {
	
	private Favorite_goodDAO_interface dao;
	
	public Favorite_goodService() {
		dao = new Favorite_goodJNDIDAO();
	}
	
	public Favorite_goodVO addFavorite_good(String mem_id, String good_id) {
		
		Favorite_goodVO favorite_goodVO = new Favorite_goodVO();
		favorite_goodVO.setMem_id(mem_id);
		favorite_goodVO.setGood_id(good_id);
		dao.insert(favorite_goodVO);
		
		return favorite_goodVO;
	}
	
	public void deleteFavorite_good(String mem_id, String good_id) {
		dao.delete(mem_id, good_id);
	}
	
	public Favorite_goodVO getOneFavorite_good(String mem_id, String good_id) {
		return dao.findByPrimaryKey(mem_id, good_id);
	}
	
	public List<Favorite_goodVO> getAll() {
		return dao.getAll();
	}
	
	public List<Favorite_goodVO> getPart(String mem_id) {
		return dao.getPart(mem_id);
	}
	
	public List<String> getDisid() {
		return dao.getDisId();
	}
}
