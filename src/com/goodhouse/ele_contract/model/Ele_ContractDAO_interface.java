package com.goodhouse.ele_contract.model;

import java.sql.Connection;
import java.util.List;

import com.goodhouse.bill.model.BillVO;

public interface Ele_ContractDAO_interface {
	
	public void insert(Ele_ContractVO ecVO);
	public void update(Ele_ContractVO ecVO);
//	public void delete(String ele_con_id);
	public Ele_ContractVO findByPrimaryKey(String ele_con_id);
	public List<Ele_ContractVO> getAll();
	public List<Ele_ContractVO> getAllForEle_ConByMem_id(String mem_id);
	public List<Ele_ContractVO> getAllForEle_ConByLan_id(String lan_id);
//	public void insert(Ele_ContractVO ecVO, List <BillVO> billVOlist);
}
