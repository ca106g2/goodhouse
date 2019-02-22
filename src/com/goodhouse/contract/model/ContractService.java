package com.goodhouse.contract.model;

import java.util.List;

public class ContractService {
	private ContractDAO_interface dao;
	public ContractService() {
		dao = new ContractJNDIDAO();
	}
	
	//新增
	public ContractVO addCon(String con_name,String con_content,String con_status) {
		
		ContractVO conVO = new ContractVO();
		
		conVO.setCon_name(con_name);
		conVO.setCon_content(con_content);
		conVO.setCon_content(con_status);
		
		dao.insert(conVO);
		return conVO;
	}
	
	//預留給 Struts 2 用的
	public void addCon(ContractVO conVO) {
		dao.insert(conVO);
	}
	
	//修改
	public ContractVO updateCon(String con_id,String con_name,String con_content,String con_status) {
		
		ContractVO conVO = new ContractVO();
		
		conVO.setCon_id(con_id);;
		conVO.setCon_name(con_name);
		conVO.setCon_content(con_content);
		conVO.setCon_status(con_status);
		
		dao.update(conVO);
		
		return conVO;
	}
	
	//預留給 Struts 2 用的
	public void updateCon(ContractVO conVO) {
		dao.update(conVO);
	}
	
	public void deleteCon(String con_id) {
		dao.delete(con_id);
	}
	
	public ContractVO getOneCon(String con_id) {
		return dao.findByPrimaryKey(con_id);
	}
	
	public List<ContractVO> getAll(){
		return dao.getAll();
	}
	
}
