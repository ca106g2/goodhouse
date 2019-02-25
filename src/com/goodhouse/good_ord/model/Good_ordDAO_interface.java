package com.goodhouse.good_ord.model;

import java.util.List;

public interface Good_ordDAO_interface {
	public void insert(Good_ordVO good_ordVO);
	public void update(Good_ordVO good_ordVO);
	public void delete(String good_ord_id);
	public Good_ordVO findByPrimaryKey(String good_ord_id);
	public List<Good_ordVO> getAll();
//	public List<Good_ordVO> getAll(Map<String, String[]> map);
}
