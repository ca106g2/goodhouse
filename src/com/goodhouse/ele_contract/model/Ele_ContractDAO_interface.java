package com.goodhouse.ele_contract.model;

import java.util.List;

public interface Ele_ContractDAO_interface {
	
	public void insert(Ele_ContractVO ecVO);
	public void update(Ele_ContractVO ecVO);
	public void delete(String ele_con_id);
	public Ele_ContractVO findByPrimaryKey(String ele_con_id);
	public List<Ele_ContractVO> getAll();
	
	
}
