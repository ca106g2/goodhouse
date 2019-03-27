package android.com.goodhouse.house_noappointment.model;

import java.util.*;

public interface HouNoAppDAO_interface {

	public void insert(HouNoAppVO houNoAppVO);

	public void update(HouNoAppVO houNoAppVO);

	public void delete(String hou_noapp_id);

	public HouNoAppVO findByPrimaryKey(String hou_noapp_id);

	public List<HouNoAppVO> getAll();

	public List<HouNoAppVO> findhoucanapp(String hou_id,String lan_id);
//	boolean cannotapp(String hou_id,String mem_id,Date hou_noapp_date);
}
