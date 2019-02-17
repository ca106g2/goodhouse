package com.goodhouse.contract.model;

import java.util.*;
//TEST
public interface ContractDAO_interface {
	public void insert(ContractVO conVO);
	public void update(ContractVO conVO);
	public void delete(String con_Id);
	public ContractVO findByPrimaryKey(String con_Id);
	public List<ContractVO> getAll();
	
	
}