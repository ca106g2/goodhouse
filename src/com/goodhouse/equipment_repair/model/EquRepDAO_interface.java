package com.goodhouse.equipment_repair.model;

import java.util.List;

public interface EquRepDAO_interface {
	public void insert(EquRepVO equRepVO);
	public void update(EquRepVO equRepVO);
	public void delete(String equ_rep_id);
	public EquRepVO findByPrimaryKey(String equ_rep_id);
	public List<EquRepVO> getAll();
	

}
//git上傳註解用無意義