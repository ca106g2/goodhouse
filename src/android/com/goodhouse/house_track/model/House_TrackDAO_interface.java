package android.com.goodhouse.house_track.model;

import java.util.*;

public interface House_TrackDAO_interface {

	public void insert(House_TrackVO houTraVO);
	public void update(House_TrackVO houTraVO);
	public void delete(String hou_tra_id);
	public House_TrackVO findByPrimaryKey(String houTraId);
	public List<House_TrackVO> getAll();
	
	
	boolean isHouseIdExist(String hou_id,String mem_id);
	boolean add(House_TrackVO trackVO);
	boolean delete2(String hou_id,String mem_id);
	
	List<House_TrackVO> findByid(String mem_id);
}
