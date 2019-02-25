package com.goodhouse.ad_report.model;

import java.util.*;

public interface Ad_reportDAO_interface {
		public void insert(Ad_reportVO ad_reportVO);
		public void update(Ad_reportVO ad_reportVO);
		public void delete(String ad_rep_id);
		public Ad_reportVO findByPrimaryKey(String ad_rep_id);
		public List<Ad_reportVO> getAll();
}
