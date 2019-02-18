package com.goodhouse.apply_conturct.model;

import java.util.List;

import com.goodhouse.keyword.model.KeyWordVO;

public interface Apply_ConturctDAO {
	 public void insert(Apply_ConturctVO appConVO);
     public void update(Apply_ConturctVO appConVO);
     public void delete(String app_con_id);
     public KeyWordVO findByPrimaryKey(String app_con_Id);
     public List<Apply_ConturctVO> getAll();
}
