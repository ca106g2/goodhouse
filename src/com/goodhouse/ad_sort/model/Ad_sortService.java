package com.goodhouse.ad_sort.model;

import java.util.List;
import java.util.Map;

import com.goodhouse.house.model.HouseVO;

/*	private String ad_sort_id;
	private String ad_forfree;
	private String ad_chargetype;
	private Integer ad_charge;
	*/
public class Ad_sortService {
	 private Ad_sortDAO_interface dao;
	 
	 public Ad_sortService() {
		 dao = new Ad_sortDAO();
	 }
	 
	 public Ad_sortVO addAd_sort(String ad_forfree, String ad_chargetype,
			 Integer ad_charge) {
		 Ad_sortVO ad_sortVO = new Ad_sortVO();
		 ad_sortVO.setAd_forfree(ad_forfree);
		 ad_sortVO.setAd_chargetype(ad_chargetype);
		 ad_sortVO.setAd_charge(ad_charge);
		 dao.insert(ad_sortVO);
		 return ad_sortVO;
	 }
	 
	 public Ad_sortVO updateAd_sort(String ad_sort_id, String ad_forfree,
			 String ad_chargetype, Integer ad_charge) {
		 Ad_sortVO ad_sortVO = new Ad_sortVO();
		 ad_sortVO.setAd_sort_id(ad_sort_id);
		 ad_sortVO.setAd_forfree(ad_forfree);
		 ad_sortVO.setAd_chargetype(ad_chargetype);
		 ad_sortVO.setAd_charge(ad_charge);
		 dao.update(ad_sortVO);
		 return ad_sortVO;
		 
	 }
	 public void deleteAd_sort(String ad_sort_id) {
		  dao.delete(ad_sort_id);
	 }
	 
	 public Ad_sortVO getOneAd_sort(String ad_sort_id) {
		 return dao.findByPrimaryKey(ad_sort_id);
	 }
	 
	 public List<Ad_sortVO> getAll(){
		 return dao.getAll();
	 }

}
