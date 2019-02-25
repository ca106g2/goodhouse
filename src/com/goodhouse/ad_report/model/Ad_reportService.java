package com.goodhouse.ad_report.model;

import java.sql.Date;
import java.util.List;

public class Ad_reportService {
	private Ad_reportDAO_interface dao;

	public Ad_reportService() {
		dao = new Ad_reportDAO();
	}


	public Ad_reportVO addAd_report(String ad_id, String mem_id, String emp_id, String ad_rep_status,
			String ad_rep_reason, Date ad_rep_date) {

		Ad_reportVO ad_reportVO = new Ad_reportVO();
		ad_reportVO.setAd_id(ad_id);
		ad_reportVO.setEmp_id(emp_id);
		ad_reportVO.setMem_id(mem_id);
		ad_reportVO.setAd_rep_status(ad_rep_status);
		ad_reportVO.setAd_rep_reason(ad_rep_reason);
		ad_reportVO.setAd_rep_date(ad_rep_date);
		dao.insert(ad_reportVO);
		return ad_reportVO;
	}

//struts2
	public void addAd_report(Ad_reportVO ad_reportVO) {
		dao.insert(ad_reportVO);
	}

	public Ad_reportVO updateAd_report(String ad_rep_id, String ad_id, String mem_id, String emp_id, String ad_rep_status,
			String ad_rep_reason, Date ad_rep_date) {

		Ad_reportVO ad_reportVO = new Ad_reportVO();
		ad_reportVO.setAd_rep_id(ad_rep_id);
		ad_reportVO.setAd_id(ad_id);
		ad_reportVO.setEmp_id(emp_id);
		ad_reportVO.setMem_id(mem_id);
		ad_reportVO.setAd_rep_status(ad_rep_status);
		ad_reportVO.setAd_rep_reason(ad_rep_reason);
		//Date ad_rep_date = new Date(System.currentTimeMillis());
		ad_reportVO.setAd_rep_date(ad_rep_date);
		dao.update(ad_reportVO);
		return ad_reportVO;
	}
	
	public void deleteAd_report(String ad_rep_id) {
		dao.delete(ad_rep_id);
	}
	
	public Ad_reportVO getOneAd_report(String ad_rep_id) {
		return dao.findByPrimaryKey(ad_rep_id);
	}
	
	public List<Ad_reportVO> getAll(){
		return dao.getAll();
	}
}
