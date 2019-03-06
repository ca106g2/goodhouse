package com.goodhouse.house.model;

import java.util.*;

public interface HouseDAO_interface {
		public void insert(HouseVO houseVO);
		public void update(HouseVO houseVO);
		public void delete(String hou_id);
		public HouseVO findByPrimaryKey(String hou_id);
		public List<HouseVO> getAll();
		//複合式查詢
		public List<HouseVO> getAll(Map<String, String[]> map);
}
