package com.goodhouse.pointgoods.model;

import java.io.Serializable;

public class PointgoodsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String good_id;
	private String good_nam;
	private String good_dsc;
	private Integer good_amo;
	private Integer good_pri;
	private String good_sta;
	private Integer good_nee;
	private byte[] good_pic;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((good_id == null) ? 0 : good_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointgoodsVO other = (PointgoodsVO) obj;
		if (good_id == null) {
			if (other.good_id != null)
				return false;
		} else if (!good_id.equals(other.good_id))
			return false;
		return true;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_nam() {
		return good_nam;
	}
	public void setGood_nam(String good_nam) {
		this.good_nam = good_nam;
	}
	public String getGood_dsc() {
		return good_dsc;
	}
	public void setGood_dsc(String good_dsc) {
		this.good_dsc = good_dsc;
	}
	public Integer getGood_amo() {
		return good_amo;
	}
	public void setGood_amo(Integer good_amo) {
		this.good_amo = good_amo;
	}
	public Integer getGood_pri() {
		return good_pri;
	}
	public void setGood_pri(Integer good_pri) {
		this.good_pri = good_pri;
	}
	public String getGood_sta() {
		return good_sta;
	}
	public void setGood_sta(String good_sta) {
		this.good_sta = good_sta;
	}
	public byte[] getGood_pic() {
		return good_pic;
	}
	public void setGood_pic(byte[] good_pic) {
		this.good_pic = good_pic;
	}
	public Integer getGood_nee() {
		return good_nee;
	}
	public void setGood_nee(Integer good_nee) {
		this.good_nee = good_nee;
	}
}
