package com.goodhouse.bill.model;

import java.util.List;


public interface BillDAO_interface {
	
	public void insert(BillVO bVO);
    public void update(BillVO bVO);
    public void delete(String bill_id);
    public BillVO findByPrimaryKey(String bill_id);
    public List<BillVO> getAll();
}
