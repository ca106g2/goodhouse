package com.goodhouse.house_track.model;

import java.util.*;

public interface House_TrackDAO_interface {

	public void insert(House_TrackVO houTraVO);
	public void update(House_TrackVO houTraVO);
	public void delete(String hou_tra_id);
	public House_TrackVO findByPrimaryKey(String houTraId);
	public List<House_TrackVO> getAll();
	public List<House_TrackVO> getListByMemId(String mem_id);
	public House_TrackVO findByHouIdAndMem_id(String hou_id,String mem_id);
}
