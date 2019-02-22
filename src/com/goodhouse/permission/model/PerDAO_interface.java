package com.goodhouse.permission.model;

import java.util.List;

public interface PerDAO_interface {
	public void insert(PerVO perVo);
	public void update(PerVO perVo);
    public void delete(String per_id);
    public PerVO findByPrimaryKey(String per_id);
    public List<PerVO> getAll();
}
