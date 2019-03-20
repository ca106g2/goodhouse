package com.goodhouse.good_record.model;

import java.util.List;

public interface Good_recordDAO_interface {
	public void insert(Good_recordVO good_recordVO);
	public void delete(String good_rec_id);
	public Good_recordVO findByPrimaryKey(String good_rec_id);
	public List<Good_recordVO> getAll();
	public List<Good_recordVO> getPart(String mem_id);
	public List<String> getDisId();
//	public List<Good_recordVO> getAll(Map<String, String[]> map);
}
