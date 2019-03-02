package com.goodhouse.equipment_repair.model;

import java.util.List;

public class EquRepService {

	private EquRepDAO_interface dao;

	public EquRepService() {
		dao = new EquRepDAO();
	}

	public EquRepVO addEquRep(String hou_id, String mem_id, String lan_id, java.sql.Date equ_rep_accetime,
			String equ_rep_staff, String equ_rep_staffphone, String equ_rep_event, byte[] equ_rep_picture,
			String equ_rep_descri, String equ_rep_status, java.sql.Date equ_rep_expectime,
			java.sql.Date equ_rep_finish) {

		EquRepVO equRepVO = new EquRepVO();

		equRepVO.setHou_id(hou_id);
		equRepVO.setMem_id(mem_id);
		equRepVO.setLan_id(lan_id);
		equRepVO.setEqu_rep_accetime(equ_rep_accetime);
		equRepVO.setEqu_rep_staff(equ_rep_staff);
		equRepVO.setEqu_rep_staffphone(equ_rep_staffphone);
		equRepVO.setEqu_rep_event(equ_rep_event);
		equRepVO.setEqu_rep_picture(equ_rep_picture);
		equRepVO.setEqu_rep_descri(equ_rep_descri);
		equRepVO.setEqu_rep_status(equ_rep_status);
		equRepVO.setEqu_rep_expectime(equ_rep_expectime);
		equRepVO.setEqu_rep_finish(equ_rep_finish);

		dao.insert(equRepVO);

		return equRepVO;
	}

	public EquRepVO updateEquRep(String equ_rep_id, String hou_id, String mem_id, String lan_id,
			java.sql.Date equ_rep_accetime, String equ_rep_staff, String equ_rep_staffphone, String equ_rep_event,
			byte[] equ_rep_picture, String equ_rep_descri, String equ_rep_status, java.sql.Date equ_rep_expectime,
			java.sql.Date equ_rep_finish) {

		EquRepVO equRepVO = new EquRepVO();

		equRepVO.setEqu_rep_id(equ_rep_id);
		equRepVO.setHou_id(hou_id);
		equRepVO.setMem_id(mem_id);
		equRepVO.setLan_id(lan_id);
		equRepVO.setEqu_rep_accetime(equ_rep_accetime);
		equRepVO.setEqu_rep_staff(equ_rep_staff);
		equRepVO.setEqu_rep_staffphone(equ_rep_staffphone);
		equRepVO.setEqu_rep_event(equ_rep_event);
		equRepVO.setEqu_rep_picture(equ_rep_picture);
		equRepVO.setEqu_rep_descri(equ_rep_descri);
		equRepVO.setEqu_rep_status(equ_rep_status);
		equRepVO.setEqu_rep_expectime(equ_rep_expectime);
		equRepVO.setEqu_rep_finish(equ_rep_finish);
		dao.update(equRepVO);

		return equRepVO;
	}

	public void deleteEquRep(String equ_rep_id) {
		dao.delete(equ_rep_id);
	}

	public EquRepVO getOneEquRep(String equ_rep_id) {
		return dao.findByPrimaryKey(equ_rep_id);
	}

	public List<EquRepVO> getAll() {
		return dao.getAll();
	}

}
//git上傳註解用無意義