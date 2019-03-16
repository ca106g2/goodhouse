package com.goodhouse.member.model;

import java.util.List;

public interface MemDAO_interface {
	public void insert(MemVO memVo);
	public void update(MemVO memVo);
	public void updatePointTot(String mem_id, Integer good_total);
	public void delete(String mem_id);
	public MemVO findByPrimaryKey(String mem_id);
	public MemVO findByEmail(String mem_email, String mem_password);
	public List<MemVO>getAll();
	
}
