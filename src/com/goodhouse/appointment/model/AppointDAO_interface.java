package com.goodhouse.appointment.model;

import java.util.*;

public interface AppointDAO_interface {
	public void insert(AppointVO appointVO);
	public void update(AppointVO appointVO);
	public void delete(String appoint_id);
	public AppointVO findByPrimaryKey(String appoint_id);
	public List<AppointVO> getAll();
	

	                       

}
//git上傳註解用無意義