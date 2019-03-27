package android.com.goodhouse.landlord.model;

import java.util.List;

public interface LanDAO_interface {
	public void insert(LanVO lanVo);
	public void update(LanVO  lanVo);
	public void delete(String lan_id);
	public LanVO findByPrimaryKey(String lan_id);
	public List<LanVO>getAll();
	public LanVO findByMem_id(String mem_id);
	
	boolean isLandlord(String mem_id);
	
	LanVO findByMemid(String mem_id);
}
