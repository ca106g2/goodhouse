package com.goodhouse.house_evaluate.model;

import java.util.List;

public interface House_EvaluateDAO_interface {
	public void insert(House_EvaluateVO heVO);
	public void update(House_EvaluateVO heVO);
	public void delete(String hou_eva_id);
	public House_EvaluateVO findByPrimmaryKey(String hou_eva_id);
	public List<House_EvaluateVO> getAll();
}
