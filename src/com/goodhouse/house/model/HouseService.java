package com.goodhouse.house.model;

import java.io.*;
import java.util.List;

public class HouseService {
	private HouseDAO_interface dao;
	public HouseService() {
		dao = new HouseDAO();
	}

	
	public HouseVO addHouse( String hou_name, String hou_type, 
			String hou_size, String hou_property, String hou_parkspace,
			String hou_cook, String hou_managefee, String hou_address, String lan_id,
			Integer hou_rent, byte[] hou_f_picture, byte[] hou_s_picture, byte[] hou_t_picture ,String hou_note) {
		HouseVO ar1 = new HouseVO();
		
		ar1.setHou_name(hou_name);
		ar1.setHou_type(hou_type);
		ar1.setHou_size(hou_size);
		ar1.setHou_property(hou_property);
		ar1.setHou_parkspace(hou_parkspace);
		ar1.setHou_cook(hou_cook);
		ar1.setHou_managefee(hou_managefee);
		ar1.setHou_address(hou_address);
		ar1.setLan_id(lan_id);
		ar1.setHou_rent(hou_rent);
		ar1.setHou_f_picture(hou_f_picture);
		ar1.setHou_s_picture(hou_s_picture);
		ar1.setHou_t_picture(hou_t_picture);
		ar1.setHou_note(hou_note);

		dao.insert(ar1);
		
		return ar1;
	}
	
	public HouseVO updateHouse(String hou_id ,String hou_name, String hou_type, 
			String hou_size, String hou_property, String hou_parkspace,
			String hou_cook, String hou_managefee, String hou_address,
			Integer hou_rent ,String hou_note, byte[] hou_f_picture, byte[] hou_s_picture, byte[] hou_t_picture
			) {
		HouseVO ar2 = new HouseVO();
		ar2.setHou_id(hou_id);
		ar2.setHou_name(hou_name);
		ar2.setHou_type(hou_type);
		ar2.setHou_size(hou_size);
		ar2.setHou_property(hou_property);
		ar2.setHou_parkspace(hou_parkspace);
		ar2.setHou_cook(hou_cook);
		ar2.setHou_managefee(hou_managefee);
		ar2.setHou_address(hou_address);
		ar2.setHou_rent(hou_rent);
		ar2.setHou_note(hou_note);
		ar2.setHou_f_picture(hou_f_picture);
		ar2.setHou_s_picture(hou_s_picture);
		ar2.setHou_t_picture(hou_t_picture);

		dao.update(ar2);
		
		return ar2;
	}
	
	//預留給 Struts 2 用的
	public void update(HouseVO houVO) {
		dao.update(houVO);
	}

	
	public void deleteHouse(String hou_id){
		dao.delete(hou_id);
	}
	
	public HouseVO getOneHouse(String hou_id) {
		return dao.findByPrimaryKey(hou_id);
	}
	
	public List<HouseVO> getAll(){
		return dao.getAll();
	}
	
	public HouseVO getOneByLanId(String lan_id) {
		return dao.findByLanId(lan_id);
	}
}
