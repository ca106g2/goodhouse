package com.goodhouse.house.model;

import java.util.*;

import com.goodhouse.ele_contract.model.Ele_ContractVO;

public interface HouseDAO_interface {
		public void insert(HouseVO houseVO);
		public void update(HouseVO houseVO);
		public void delete(String hou_id);
		public HouseVO findByPrimaryKey(String hou_id);
		public List<HouseVO> getAll();
		//複合式查詢
		public List<HouseVO> getAll(Map<String, String[]> map);
		//房東物件查詢
		public List<HouseVO> getAllFor_Hou_Lan_id(String lan_id);
		public HouseVO findByLanId(String lan_id);
}
