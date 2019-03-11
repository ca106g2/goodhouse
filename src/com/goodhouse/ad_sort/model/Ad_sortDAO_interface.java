package com.goodhouse.ad_sort.model;

import java.util.*;

import com.goodhouse.house.model.HouseVO;

public interface Ad_sortDAO_interface {
		public void insert(Ad_sortVO ad_sortVO);
		public void update(Ad_sortVO ad_sortVO);
		public void delete(String ad_sort_id);
		public Ad_sortVO findByPrimaryKey(String ad_sort_id);
		public List<Ad_sortVO> getAll();
}
