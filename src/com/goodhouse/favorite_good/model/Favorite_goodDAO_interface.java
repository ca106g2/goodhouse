package com.goodhouse.favorite_good.model;

import java.util.List;

import com.goodhouse.good_ord_list.model.Good_ord_listVO;

public interface Favorite_goodDAO_interface {
	public void insert(Favorite_goodVO favorite_goodVO);
	public void delete(String mem_id,String good_id);
	public Favorite_goodVO findByPrimaryKey(String mem_id,String good_id);
	public List<Favorite_goodVO> getAll();
	public List<Favorite_goodVO> getPart(String mem_id);
	public List<String> getDisId();
//	public List<Favorite_goodVO> getAll(Map<String, String[]> map);
}
