package com.goodhouse.member.model;

import java.sql.Date;
import java.util.List;



public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(  String MEM_NAME, Date MEM_BIRTHDAY, String MEM_PASSWORD, String  MEM_ADDRESS,String MEM_ZIPCODE,Integer MEM_TELEPHONE,Integer  MEM_PHONE, String MEM_EMAIL,String MEM_STATUS,byte[] MEM_PICTURE,Integer GOOD_TOTAL,String MEM_SEX) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_name( MEM_NAME );
		memVO.setMem_birthday(MEM_BIRTHDAY);
		memVO.setMem_password(MEM_PASSWORD);
		memVO.setMem_address(MEM_ADDRESS);
		memVO.setMem_zipcode(MEM_ZIPCODE);
		memVO.setMem_telephone(MEM_TELEPHONE);
		memVO.setMem_phone(MEM_PHONE);
		memVO.setMem_email(MEM_EMAIL);
		memVO.setMem_status(MEM_STATUS);
		memVO.setMem_picture(MEM_PICTURE);
		memVO.setGood_total(GOOD_TOTAL);
		memVO.setMem_sex(MEM_SEX);
		dao.insert(memVO);
		
		return memVO;
		}
	
	public MemVO updateMem(  String MEM_NAME, Date MEM_BIRTHDAY, String MEM_PASSWORD, String  MEM_ADDRESS,String MEM_ZIPCODE,Integer MEM_TELEPHONE,Integer  MEM_PHONE, String MEM_EMAIL,String MEM_STATUS,byte[] MEM_PICTURE,Integer GOOD_TOTAL,String MEM_SEX,String MEM_ID ) {
		
		MemVO memVO = new MemVO();
		
		
		memVO.setMem_name( MEM_NAME );
		memVO.setMem_birthday(MEM_BIRTHDAY);
		memVO.setMem_password(MEM_PASSWORD);
		memVO.setMem_address(MEM_ADDRESS);
		memVO.setMem_zipcode(MEM_ZIPCODE);
		memVO.setMem_telephone(MEM_TELEPHONE);
		memVO.setMem_phone(MEM_PHONE);
		memVO.setMem_email(MEM_EMAIL);
		memVO.setMem_status(MEM_STATUS);
		memVO.setMem_picture(MEM_PICTURE);
		memVO.setGood_total(GOOD_TOTAL);
		memVO.setMem_sex(MEM_SEX);
		memVO.setMem_id( MEM_ID );
		
		dao.update(memVO);
		return memVO;
		
	}
	public void deleteMem(String MEM_ID) {
		dao.delete(MEM_ID);
		}
	public MemVO getOneMem(String MEM_ID) {
		return dao.findByPrimaryKey(MEM_ID);
		}
	
	public List <MemVO> getAll(){
		return dao.getAll();
	}
	
	
}
