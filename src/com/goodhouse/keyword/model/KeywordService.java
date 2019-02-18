package com.goodhouse.keyword.model;

import java.util.List;

public class KeywordService {

	private KeyWordDAO_interface dao;
	
	public KeywordService() {
		dao = new KeyWordJDBCDAO();
	}
	
	//新增
	public KeyWordVO addKW(String kw_keyword ,Integer kw_count) {
		
		KeyWordVO kwVO = new KeyWordVO();
		
		kwVO.setKw_keyword(kw_keyword);
		kwVO.setKw_count(kw_count);
		dao.insert(kwVO);
		
		return kwVO;
	}
	//修改
	public KeyWordVO updateKW(String kw_id,String kw_keyword,Integer kw_count) {
		
		KeyWordVO kwVO = new KeyWordVO();
		
		kwVO.setKw_id(kw_id);
		kwVO.setKw_keyword(kw_keyword);
		kwVO.setKw_count(kw_count);
		dao.update(kwVO);
		
		return kwVO;
	}
	//刪除
	public void deleteKW(String kw_id) {
		 dao.delete(kw_id);
	}
	//單一查詢
	public KeyWordVO getOneKW(String kw_id) {
		return dao.findByPrimaryKey(kw_id);
	}
	//查詢全部
	public List<KeyWordVO> getAll(){
		return dao.getAll();
	}
}
