package com.goodhouse.keyword.model;

import java.util.List;

public interface KeyWordDAO_interface {
	 public void insert(KeyWordVO kwVO);
     public void update(KeyWordVO kwVO);
     public void delete(String kw_id);
     public KeyWordVO findByPrimaryKey(String kw_id);
     public List<KeyWordVO> getAll();
}
