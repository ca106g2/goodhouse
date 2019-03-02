package com.goodhouse.appointment.model;

import java.util.List;

public class AppointService {

	private AppointDAO_interface dao;

	public AppointService() {
		dao = new AppointDAO();
	}

	public AppointVO addAppoint(String mem_id, String lan_id, String hou_id, String hou_app_time, java.sql.Date hou_app_date,
			String app_status, String app_remind) {

		AppointVO appointVO = new AppointVO();

		appointVO.setMem_id(mem_id);
		appointVO.setLan_id(lan_id);
		appointVO.setHou_id(hou_id);
		appointVO.setHou_app_time(hou_app_time);
		appointVO.setHou_app_date(hou_app_date);
		appointVO.setApp_status(app_status);
		appointVO.setApp_remind(app_remind);
		dao.insert(appointVO);

		return appointVO;
	}

	public AppointVO updateAppoint(String appoint_id, String mem_id, String lan_id, String hou_id,
			String hou_app_time, java.sql.Date hou_app_date, String app_status, String app_remind) {

		AppointVO appointVO = new AppointVO();

		appointVO.setAppoint_id(appoint_id);
		appointVO.setMem_id(mem_id);
		appointVO.setLan_id(lan_id);
		appointVO.setHou_id(hou_id);
		appointVO.setHou_app_time(hou_app_time);
		appointVO.setHou_app_date(hou_app_date);
		appointVO.setApp_status(app_status);
		appointVO.setApp_remind(app_remind);
		dao.update(appointVO);

		return appointVO;
	}

	public void deleteAppoint(String appoint_id) {
		dao.delete(appoint_id);
	}

	public AppointVO getOneAppoint(String appoint_id) {
		return dao.findByPrimaryKey(appoint_id);
	}

	public List<AppointVO> getAll() {
		return dao.getAll();
	}

}
//git上傳註解用無意義