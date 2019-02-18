package com.goodhouse.apply_conturct.model;

import java.util.List;

public interface Apply_ConturctDAO_interface {
	public void insert(Apply_ConturctVO kwVO);
    public void update(Apply_ConturctVO kwVO);
    public void delete(Apply_ConturctVO app_con_id);
    public Apply_ConturctVO findByPrimaryKey(String app_con_id);
    public List<Apply_ConturctVO> getAll();
}
