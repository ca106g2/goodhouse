package com.goodhouse.rental_message.model;

import java.util.*;

public interface RentMessDAO_interface {
	public void insert(RentMessVO rentMessVO);
	public void update(RentMessVO rentMessVO);
	public void delete(String ren_mes_id);
	public RentMessVO findByPrimaryKey(String ren_mes_id);
	public List<RentMessVO> getPart(String hou_id);
	public List<RentMessVO> getAll();
//	public List<RentMessVO> getAllByHouId(String hou_id);
//	public List<RentMessVO> getPart(String hou_id);
	
	
	

}
//git上傳註解用無意義