package com.goodhouse.pointgoods.model;

import java.util.List;

public class PointgoodsService {

	private PointgoodsDAO_interface dao;
	
	public PointgoodsService() {
		dao = new PointgoodsDAO();
	}
	
	public PointgoodsVO addPointgoods(String good_nam, String good_dsc, Integer good_amo, Integer good_pri, String good_sta, byte[] good_pic) {
		
		PointgoodsVO pointgoodsVO = new PointgoodsVO();
		pointgoodsVO.setGood_nam(good_nam);
		pointgoodsVO.setGood_dsc(good_dsc);
		pointgoodsVO.setGood_amo(good_amo);
		pointgoodsVO.setGood_pri(good_pri);
		pointgoodsVO.setGood_sta(good_sta);
		pointgoodsVO.setGood_pic(good_pic);
		dao.insert(pointgoodsVO);
		
		return pointgoodsVO;
	}
	
	public PointgoodsVO updatePointgoods(String good_id, String good_nam, String good_dsc, Integer good_amo, Integer good_pri, String good_sta, byte[] good_pic) {
		
		PointgoodsVO pointgoodsVO = new PointgoodsVO();
		pointgoodsVO.setGood_id(good_id);
		pointgoodsVO.setGood_nam(good_nam);
		pointgoodsVO.setGood_dsc(good_dsc);
		pointgoodsVO.setGood_amo(good_amo);
		pointgoodsVO.setGood_pri(good_pri);
		pointgoodsVO.setGood_sta(good_sta);
		pointgoodsVO.setGood_pic(good_pic);
		dao.update(pointgoodsVO);
		
		return pointgoodsVO;
	}
	
	public void deletePointgoods(String good_id) {
		
		dao.delete(good_id);
	}
	
	public PointgoodsVO getOnePointgoods(String good_id) {
		return dao.findByPrimaryKey(good_id);
	}
	
	public List<PointgoodsVO> getAll() {
		return dao.getAll();
	}
}
