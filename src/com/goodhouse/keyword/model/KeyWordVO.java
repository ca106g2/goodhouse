package com.goodhouse.keyword.model;


//資料庫對應：關鍵字
public class KeyWordVO implements java.io.Serializable{
	
	private String kw_id;//關鍵字編號
	private String kw_keyword;//關鍵字
	private Integer kw_count;//關鍵字被搜尋次數
	
	public String getKw_id() {
		return kw_id;
	}
	public void setKw_id(String kw_id) {
		this.kw_id = kw_id;
	}
	public String getKw_keyword() {
		return kw_keyword;
	}
	public void setKw_keyword(String kw_keyword) {
		this.kw_keyword = kw_keyword;
	}
	public Integer getKw_count() {
		return kw_count;
	}
	public void setKw_count(Integer kw_count) {
		this.kw_count = kw_count;
	}
	
	
	
}
