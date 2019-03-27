package android.com.goodhouse.member.model;

import java.util.List;

public interface MemDAO_interface {
	boolean isMember(String mem_email,String mem_password);
	boolean isUserIdExist(String mem_email);
	boolean insert(MemVO memVO);
	boolean update(MemVO memVO);
	boolean delete(MemVO memVO);
	MemVO findByEmail(String mem_email);
	List<MemVO>getALL();
	
	
	public MemVO findByPrimaryKey(String mem_id);
	public List<MemVO>getAll();
	
	
	
	
	
	
	
//	public void insert(MemVO memVo);
//	public void update(MemVO memVo);
//	public void delete(String mem_id);
	
	
	
}
