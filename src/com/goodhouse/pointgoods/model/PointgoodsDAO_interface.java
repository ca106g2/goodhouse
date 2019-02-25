package com.goodhouse.pointgoods.model;

import java.util.*;

public interface PointgoodsDAO_interface {
	public void insert(PointgoodsVO pointgoodsVO);
	public void update(PointgoodsVO pointgoodsVO);
	public void delete(String good_id);
	public PointgoodsVO findByPrimaryKey(String good_id);
	public List<PointgoodsVO> getAll();
//	public List<PointgoodsVO> getAll(Map<String, String[]> map);
}
