package com.goodhouse.ad.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class AdService {
	private AdDAO_interface dao;
	
	public AdService() {
		dao = new AdDAO();
	}
	
	public AdVO addAd(String lan_id,String hou_id,Date ad_date,String ad_sort_id,
			String ad_status,String ad_forfree,String ad_statue,String ad_paymethod) {
		AdVO adVO =new AdVO();
		adVO.setLan_id(lan_id);
		adVO.setHou_id(hou_id);
		//Date ad_date = new Date(System.currentTimeMillis());
		adVO.setAd_date(ad_date);
		adVO.setAd_sort_id(ad_sort_id);
		adVO.setAd_status(ad_status);
		adVO.setAd_forfree(ad_forfree);
		adVO.setAd_statue(ad_statue);
		adVO.setAd_paymethod(ad_paymethod);
		dao.insert(adVO);
		return adVO;
		
	}
	public AdVO updateAd(String ad_id, String lan_id, String hou_id,
			Date ad_date,String ad_sort_id,String ad_status, 
			String ad_forfree, String ad_statue, String ad_paymethod) {
		AdVO adVO = new AdVO();
		adVO.setAd_id(ad_id);
		adVO.setLan_id(lan_id);
		adVO.setHou_id(hou_id);
		adVO.setAd_date(ad_date);
		adVO.setAd_sort_id(ad_sort_id);
		adVO.setAd_status(ad_status);
		adVO.setAd_forfree(ad_forfree);
		adVO.setAd_statue(ad_statue);
		adVO.setAd_paymethod(ad_paymethod);
		dao.update(adVO);
		return adVO;
	}
	public void deleteAd(String ad_id) {
		dao.delete(ad_id);
	}
	public AdVO getOneAD(String ad_id) {
		return dao.findByPrimaryKey(ad_id);
	}
	public List<AdVO> getAll(){
		return dao.getAll();
	}
	public List<AdVO> getAll(Map<String, String[]>map){
		return dao.getAll(map);
	}
} 
