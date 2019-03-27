package android.com.goodhouse.ad.model;

import java.util.*;

public interface AdDAO_interface {
	public void insert(AdVO adVO);
	public void update(AdVO adVO);
	public void delete(String ad_id);
	public AdVO findByPrimaryKey(String ad_id);
	public List<AdVO> findByAD(String lan_id,String ad_status);
	
	public List<AdVO> getAll();
	//複合式查詢
	public List<AdVO> getAll(Map<String, String[]> map);
	
}
