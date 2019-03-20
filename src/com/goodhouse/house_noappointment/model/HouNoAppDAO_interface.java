package com.goodhouse.house_noappointment.model;

import java.util.*;

public interface HouNoAppDAO_interface {

	public void insert(HouNoAppVO houNoAppVO);

	public void update(HouNoAppVO houNoAppVO);

	public void delete(String hou_noapp_id);

	public HouNoAppVO findByPrimaryKey(String hou_noapp_id);

	public List<HouNoAppVO> getAll();
	
	public List<HouNoAppVO> getPart(String lan_id);

}
//git上傳註解用無意義