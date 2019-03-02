package com.goodhouse.rental_message.model;

import java.util.List;

public class RentMessService {

	private RentMessDAO_interface dao;

	public RentMessService() {
		dao = new RentMessDAO();
	}

	public RentMessVO addRentMess(String hou_id, String mem_id, String lan_id, java.sql.Date ren_mes_time,
			String ren_mes_request, String ren_mes_response) {

		RentMessVO rentMessVO = new RentMessVO();

		rentMessVO.setHou_id(hou_id);
		rentMessVO.setMem_id(mem_id);
		rentMessVO.setLan_id(lan_id);
		rentMessVO.setRen_mes_time(ren_mes_time);
		rentMessVO.setRen_mes_request(ren_mes_request);
		rentMessVO.setRen_mes_response(ren_mes_response);
		dao.insert(rentMessVO);

		return rentMessVO;
	}

	public RentMessVO updateRentMess(String ren_mes_id, String hou_id, String mem_id, String lan_id,
			java.sql.Date ren_mes_time, String ren_mes_request, String ren_mes_response) {

		RentMessVO rentMessVO = new RentMessVO();

		rentMessVO.setRen_mes_id(ren_mes_id);
		rentMessVO.setHou_id(hou_id);
		rentMessVO.setMem_id(mem_id);
		rentMessVO.setLan_id(lan_id);
		rentMessVO.setRen_mes_time(ren_mes_time);
		rentMessVO.setRen_mes_request(ren_mes_request);
		rentMessVO.setRen_mes_response(ren_mes_response);
		dao.update(rentMessVO);

		return rentMessVO;
	}

	public void deleteRentMess(String ren_mes_id) {
		dao.delete(ren_mes_id);
	}

	public RentMessVO getOneRentMess(String ren_mes_id) {
		return dao.findByPrimaryKey(ren_mes_id);
	}

	public List<RentMessVO> getAll() {
		return dao.getAll();
	}

}
//git上傳註解用無意義