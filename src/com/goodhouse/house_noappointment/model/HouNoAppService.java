package com.goodhouse.house_noappointment.model;

import java.util.List;

public class HouNoAppService {

	private HouNoAppDAO_interface dao;

	public HouNoAppService() {
		dao = new HouNoAppDAO();
	}

	public HouNoAppVO addHouNoApp(String hou_id, String lan_id, String hou_noapp_time, java.sql.Date hou_noapp_date) {

		HouNoAppVO houNoAppVO = new HouNoAppVO();

		houNoAppVO.setHou_id(hou_id);
		houNoAppVO.setLan_id(lan_id);
		houNoAppVO.setHou_noapp_time(hou_noapp_time);
		houNoAppVO.setHou_noapp_date(hou_noapp_date);
		dao.insert(houNoAppVO);

		return houNoAppVO;
	}

	public HouNoAppVO updateHouNoApp(String hou_noapp_id, String hou_id, String lan_id, String hou_noapp_time,
			java.sql.Date hou_noapp_date) {
		HouNoAppVO houNoAppVO = new HouNoAppVO();

		houNoAppVO.setHou_noapp_id(hou_noapp_id);
		houNoAppVO.setHou_id(hou_id);
		houNoAppVO.setLan_id(lan_id);
		houNoAppVO.setHou_noapp_time(hou_noapp_time);
		houNoAppVO.setHou_noapp_date(hou_noapp_date);

		dao.update(houNoAppVO);
		
		return houNoAppVO;
	}

	public void deleteHouNoApp(String hou_noapp_id) {
		dao.delete(hou_noapp_id);
	}

	public HouNoAppVO getOneHouNoApp(String hou_noapp_id) {
		return dao.findByPrimaryKey(hou_noapp_id);
	}

	public List<HouNoAppVO> getAll() {
		return dao.getAll();
	}

}
//git上傳註解用無意義