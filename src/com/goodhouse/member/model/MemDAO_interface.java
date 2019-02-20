package com.goodhouse.member.model;

import java.util.List;

public interface MemDAO_interface {
	public void insert(MemVO memVo);
	public void update(MemVO memVo);
	public void delete(String mem_id);
	public MemVO findByPrimaryKey(String mem_id);
	public List<MemVO>getAll();
	
}
