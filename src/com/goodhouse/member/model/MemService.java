package com.goodhouse.member.model;

import java.sql.Date;
import java.util.List;

import com.goodhouse.employee.model.EmpVO;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem( String mem_name, Date mem_birthday, String mem_password, String  mem_address,String mem_zipcode,Integer mem_telephone,Integer  mem_phone, String mem_email,String mem_status,byte[] mem_picture,Integer good_total,String mem_sex) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_name( mem_name );
		memVO.setMem_birthday(mem_birthday);
		memVO.setMem_password(mem_password);
		memVO.setMem_address(mem_address);
		memVO.setMem_zipcode(mem_zipcode);
		memVO.setMem_telephone(mem_telephone);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_email(mem_email);
		memVO.setMem_status(mem_status);
		memVO.setMem_picture(mem_picture);
		memVO.setGood_total(good_total);
		memVO.setMem_sex(mem_sex);
		dao.insert(memVO);
		
		return memVO;
		}
	
	public MemVO updateMem(  String mem_name, Date mem_birthday, String mem_password, String  mem_address,String mem_zipcode,Integer mem_telephone,Integer  mem_phone, String mem_email,String mem_status,byte[] mem_picture,Integer good_total,String mem_sex,String mem_id ) {
		
		MemVO memVO = new MemVO();
		
		
		memVO.setMem_name( mem_name );
		memVO.setMem_birthday(mem_birthday);
		memVO.setMem_password(mem_password);
		memVO.setMem_address(mem_address);
		memVO.setMem_zipcode(mem_zipcode);
		memVO.setMem_telephone(mem_telephone);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_email(mem_email);
		memVO.setMem_status(mem_status);
		memVO.setMem_picture(mem_picture);
		memVO.setGood_total(good_total);
		memVO.setMem_sex(mem_sex);
		memVO.setMem_id( mem_id );
		
		dao.update(memVO);
		return memVO;
		
	}
	public void updatePointTot(String mem_id, Integer good_total) {
		dao.updatePointTot(mem_id, good_total);
	}
	public void deleteMem(String mem_id) {
		dao.delete(mem_id);
		}
	public MemVO getOneMem(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
		}
	
	public MemVO getOneMem(String mem_email, String mem_password) {
		return dao.findByEmail(mem_email, mem_password);
		}
	
	public List <MemVO> getAll(){
		return dao.getAll();
	}
	
	
}
