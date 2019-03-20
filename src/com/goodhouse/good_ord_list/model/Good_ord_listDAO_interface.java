package com.goodhouse.good_ord_list.model;

import java.sql.Connection;
import java.util.List;

public interface Good_ord_listDAO_interface {
	public void insert(Good_ord_listVO good_ord_listVO);
	public void insert2(Good_ord_listVO good_ord_listVO, Connection  con);
	public void update(Good_ord_listVO good_ord_listVO);
	public void delete(String good_id,String good_ord_id);
	public Good_ord_listVO findByPrimaryKey(String good_id,String good_ord_id);
	public List<Good_ord_listVO> getAll();
	public List<Good_ord_listVO> getPart(String good_ord_id);
	public List<String> getDisId();
//	public List<Good_ord_listVO> getAll(Map<String, String[]> map);
}
