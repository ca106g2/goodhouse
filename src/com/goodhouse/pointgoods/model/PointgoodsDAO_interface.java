package com.goodhouse.pointgoods.model;

import java.util.*;

public interface PointgoodsDAO_interface {
	public void insert(PointgoodsVO pointgoodsVO);
	public void update(PointgoodsVO pointgoodsVO);
	public void updateamo(String good_id, Integer good_amo);
	public void updatesta(String good_id, String good_sta);
	public void delete(String good_id);
	public PointgoodsVO findByPrimaryKey(String good_id);
	public List<PointgoodsVO> getAll();
//	public List<PointgoodsVO> getAll(Map<String, String[]> map);
}
