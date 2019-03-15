package com.goodhouse.permission.model;

import java.util.List;

import com.goodhouse.member.model.MemVO;

public class PerService {
	
	private PerDAO_interface dao;
	
	public PerService() {
		dao =new PerDAO();
	}
	
	
	public PerVO addPer(String per_name) {
		
		PerVO perVO =new PerVO();
		perVO.setPer_name(per_name);
		
		dao.insert(perVO);
		return perVO;
		
	}
		public PerVO updatePer(String per_name, String per_id) {
		
		PerVO perVO =new PerVO();
		perVO.setPer_name(per_name);
		perVO.setPer_id(per_id);
		
		dao.update(perVO);
		return perVO;
		}
		public void deletePer(String per_id) {
		dao.delete(per_id);
		}
		public PerVO getOnePer(String per_id) {
		return dao.findByPrimaryKey(per_id);
		}
		public List <PerVO> getAll(){
		return dao.getAll();
		}
		
	
	
}
