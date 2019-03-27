package android.com.goodhouse.appointment.model;

import java.util.*;
import java.sql.Date;
public interface AppointDAO_interface {
	public void insert(AppointVO appointVO);
	public void update(AppointVO appointVO);
	public void delete(String appoint_id);
	public AppointVO findByPrimaryKey(String appoint_id);
	public List<AppointVO> getAll();
	
//	boolean isUserIdExist(String mem_id,String lan_id,Date hou_app_date);
	boolean updatestatud(String mem_id,String app_status,String lan_id,String hou_app_date);
	boolean insert2(AppointVO appointVO);
	
	
	
	public List<AppointVO> findByLanid(String lan_id,String start,String end);
	public List<AppointVO> findByMemid(String mem_id,String start,String end);
	
	boolean isUserIdExist(String mem_id, String lan_id, String hou_app_date,String app_status);
	boolean isUserIdExist2(String mem_id, String hou_id, String hou_app_date);
	                       

}
