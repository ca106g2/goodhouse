package com.goodhouse.ele_contract.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodhouse.keyword.model.KeyWordVO;

public class Ele_ContractJDBCDAO implements Ele_ContractDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GOODHOUSE";
	String passwd = "123456";
	
	private static final String INSERT_STMT = //新增指令
			"INSERT INTO ELE_CONTRACT (ELE_CON_ID,CON_ID,MEM_ID,MEM_IDNUMBER,LAN_ID,LAN_IDNUMBER,HOU_ID," + 
			"ELE_RENT_MONEY,ELE_DEPOSIT_MONEY,ELE_RENT_TIME,ELE_RENT_F_DAY,ELE_RENT_L_DAY,ELE_SINGDATE," + 
			"ELE_CON_STATUS,BILL_PAYMENTTYPE,ELE_CON_NOTE) VALUES ('ECON'||LPAD(ELE_CON_SEQ.NEXTVAL,6,0)," + 
			"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = //修改指令
			"UPDATE ELE_CONTRACT SET CON_ID = ? , MEM_ID = ? , MEM_IDNUMBER = ? , LAN_ID = ? , LAN_IDNUMBER = ? , " +
			"HOU_ID = ? , ELE_RENT_MONEY = ? , ELE_DEPOSIT_MONEY = ? , ELE_RENT_TIME = ? , ELE_RENT_F_DAY = ? , " +
			" ELE_RENT_L_DAY = ? , ELE_SINGDATE = ? , ELE_CON_STATUS = ?, BILL_PAYMENTTYPE = ? , ELE_CON_NOTE = ?  WHERE ELE_CON_ID = ?";
	private static final String DELETE = //刪除指令
			"DELETE FROM ELE_CONTRACT WHERE ELE_CON_ID = ? ";
	private static final String GET_ONE_STMT = //查詢指令
			"SELECT ELE_CON_ID,CON_ID,MEM_ID,MEM_IDNUMBER,LAN_ID,LAN_IDNUMBER,HOU_ID," + 
			" ELE_RENT_MONEY,ELE_DEPOSIT_MONEY,ELE_RENT_TIME,ELE_RENT_F_DAY,ELE_RENT_L_DAY,ELE_SINGDATE, " + 
			" ELE_CON_STATUS,BILL_PAYMENTTYPE,ELE_CON_NOTE FROM ELE_CONTRACT WHERE ELE_CON_ID=?";
	private static final String GET_ALL_STMT = //查詢指令
			"SELECT ELE_CON_ID,CON_ID,MEM_ID,MEM_IDNUMBER,LAN_ID,LAN_IDNUMBER,HOU_ID," + 
			" ELE_RENT_MONEY,ELE_DEPOSIT_MONEY,ELE_RENT_TIME,ELE_RENT_F_DAY,ELE_RENT_L_DAY,ELE_SINGDATE, " + 
			" ELE_CON_STATUS,BILL_PAYMENTTYPE,ELE_CON_NOTE FROM ELE_CONTRACT ORDER BY ELE_CON_ID";
	private static final String GET_ONE_MEM_STMT =
			"SELECT * FROM ELE_CONTRACT WHERE MEM_ID = ?";
	@Override//新增
	public void insert(Ele_ContractVO ecVO) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			/*
			"INSERT INTO ELE_CONTRACT (ELE_CON_ID,CON_ID,MEM_ID,MEM_IDNUMBER,LAN_ID,LAN_IDNUMBER,HOU_ID," + 
			"ELE_RENT_MONEY,ELE_DEPOSIT_MONEY,ELE_RENT_TIME,ELE_RENT_F_DAY,ELE_RENT_L_DAY,ELE_SINGDATE," + 
			"ELE_CON_STATUS,BILL_PAYMENTTYPE,ELE_CON_NOTE) VALUES ('ECON'||LPAD(ELE_CON_SEQ.NEXTVAL,6,0)," + 
			"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			*/
			pstmt.setString(1, ecVO.getCon_id());
			pstmt.setString(2, ecVO.getMem_id());
			pstmt.setString(3, ecVO.getMem_idnumber());
			pstmt.setString(4, ecVO.getLan_id());
			pstmt.setString(5, ecVO.getLan_idnumber());
			pstmt.setString(6, ecVO.getHou_id());
			pstmt.setInt(7, ecVO.getEle_rent_money());
			pstmt.setInt(8, ecVO.getEle_deposit_money());
			pstmt.setInt(9, ecVO.getEle_rent_time());
			pstmt.setDate(10, ecVO.getEle_rent_f_day());
			pstmt.setDate(11, ecVO.getEle_rent_l_day());
			pstmt.setDate(12, ecVO.getEle_singdate());
			pstmt.setString(13, ecVO.getEle_con_status());
			pstmt.setString(14, ecVO.getBill_paymenttype());
			pstmt.setString(15, ecVO.getEle_con_note());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(Ele_ContractVO ecVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ecVO.getCon_id());
			pstmt.setString(2, ecVO.getMem_id());
			pstmt.setString(3, ecVO.getMem_idnumber());
			pstmt.setString(4, ecVO.getLan_id());
			pstmt.setString(5, ecVO.getLan_idnumber());
			pstmt.setString(6, ecVO.getHou_id());
			pstmt.setInt(7, ecVO.getEle_rent_money());
			pstmt.setInt(8, ecVO.getEle_deposit_money());
			pstmt.setInt(9, ecVO.getEle_rent_time());
			pstmt.setDate(10, ecVO.getEle_rent_f_day());
			pstmt.setDate(11, ecVO.getEle_rent_l_day());
			pstmt.setDate(12, ecVO.getEle_singdate());
			pstmt.setString(13, ecVO.getEle_con_status());
			pstmt.setString(14, ecVO.getBill_paymenttype());
			pstmt.setString(15, ecVO.getEle_con_note());
			pstmt.setString(16, ecVO.getEle_con_id());
			/*
			"UPDATE ELE_CONTRACT SET CON_ID = ? , MEM_ID = ? , MEM_IDNUMBER = ? , LAN_ID = ? , LAN_IDNUMBER = ? , " +
			"HOU_ID = ? , ELE_RENT_MONEY = ? , ELE_DEPOSIT_MONEY = ? , ELE_RENT_TIME = ? , ELE_RENT_F_DAY = ? , " +
			" ELE_RENT_L_DAY = ? , ELE_SINGDATE = ? , ELE_CON_STATUS = ?, BILL_PAYMENTTYPE = ? , ELE_CON_NOTE = ?  WHERE ELE_CON_ID = ?";
			*/
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(String ele_con_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			//DELETE FROM ELE_CONTRACT WHERE ELE_CON_ID = ?  
			
			pstmt.setString(1, ele_con_id);
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override//單一查詢
	public Ele_ContractVO findByPrimaryKey(String ele_con_id) {
		
		Ele_ContractVO ecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, ele_con_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ecVO = new Ele_ContractVO();
				ecVO.setEle_con_id(rs.getString("ELE_CON_ID"));
				ecVO.setCon_id(rs.getString("CON_ID"));
				ecVO.setMem_id(rs.getString("MEM_ID"));
				ecVO.setMem_idnumber(rs.getString("MEM_IDNUMBER"));
				ecVO.setLan_id(rs.getString("LAN_ID"));
				ecVO.setLan_idnumber(rs.getString("LAN_IDNUMBER"));
				ecVO.setHou_id(rs.getString("HOU_ID"));
				ecVO.setEle_rent_money(rs.getInt("ELE_RENT_MONEY"));
				ecVO.setEle_deposit_money(rs.getInt("ELE_DEPOSIT_MONEY"));
				ecVO.setEle_rent_time(rs.getInt("ELE_RENT_TIME"));
				ecVO.setEle_rent_f_day(rs.getDate("ELE_RENT_F_DAY"));
				ecVO.setEle_rent_l_day(rs.getDate("ELE_RENT_L_DAY"));
				ecVO.setEle_singdate(rs.getDate("ELE_SINGDATE"));
				ecVO.setEle_con_status(rs.getString("ELE_CON_STATUS"));
				ecVO.setBill_paymenttype(rs.getString("BILL_PAYMENTTYPE"));
				ecVO.setEle_con_note(rs.getString("ELE_CON_NOTE"));
				
				/*
				 "SELECT ELE_CON_ID,CON_ID,MEM_ID,MEM_IDNUMBER,LAN_ID,LAN_IDNUMBER,HOU_ID," + 
				 " ELE_RENT_MONEY,ELE_DEPOSIT_MONEY,ELE_RENT_TIME,ELE_RENT_F_DAY,ELE_RENT_L_DAY,ELE_SINGDATE, " + 
				 " ELE_CON_STATUS,BILL_PAYMENTTYPE,ELE_CON_NOTE FROM ELE_CONTRACT WHERE ELE_CON_ID=?";
				 */
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return ecVO;
	}
	@Override//查詢全部
	public List<Ele_ContractVO> getAll() {
		
		List<Ele_ContractVO> list = new ArrayList();
		Ele_ContractVO ecVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ecVO = new Ele_ContractVO();
				ecVO.setEle_con_id(rs.getString("ELE_CON_ID"));
				ecVO.setCon_id(rs.getString("CON_ID"));
				ecVO.setMem_id(rs.getString("MEM_ID"));
				ecVO.setMem_idnumber(rs.getString("MEM_IDNUMBER"));
				ecVO.setLan_id(rs.getString("LAN_ID"));
				ecVO.setLan_idnumber(rs.getString("LAN_IDNUMBER"));
				ecVO.setHou_id(rs.getString("HOU_ID"));
				ecVO.setEle_rent_money(rs.getInt("ELE_RENT_MONEY"));
				ecVO.setEle_deposit_money(rs.getInt("ELE_DEPOSIT_MONEY"));
				ecVO.setEle_rent_time(rs.getInt("ELE_RENT_TIME"));
				ecVO.setEle_rent_f_day(rs.getDate("ELE_RENT_F_DAY"));
				ecVO.setEle_rent_l_day(rs.getDate("ELE_RENT_L_DAY"));
				ecVO.setEle_singdate(rs.getDate("ELE_SINGDATE"));
				ecVO.setEle_con_status(rs.getString("ELE_CON_STATUS"));
				ecVO.setBill_paymenttype(rs.getString("BILL_PAYMENTTYPE"));
				ecVO.setEle_con_note(rs.getString("ELE_CON_NOTE"));
				/*
				 "SELECT ELE_CON_ID,CON_ID,MEM_ID,MEM_IDNUMBER,LAN_ID,LAN_IDNUMBER,HOU_ID," + 
			     " ELE_RENT_MONEY,ELE_DEPOSIT_MONEY,ELE_RENT_TIME,ELE_RENT_F_DAY,ELE_RENT_L_DAY,ELE_SINGDATE, " + 
				 " ELE_CON_STATUS,BILL_PAYMENTTYPE,ELE_CON_NOTE FROM ELE_CONTRACT ORDER BY ELE_CON_ID";
				 */
				list.add(ecVO);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<Ele_ContractVO> findByOther(String mem_id) {
		
		List<Ele_ContractVO> list = new ArrayList();
		Ele_ContractVO ecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEM_STMT );
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ecVO = new Ele_ContractVO();
				ecVO.setEle_con_id(rs.getString("ELE_CON_ID"));
				ecVO.setCon_id(rs.getString("CON_ID"));
				ecVO.setMem_id(rs.getString("MEM_ID"));
				ecVO.setMem_idnumber(rs.getString("MEM_IDNUMBER"));
				ecVO.setLan_id(rs.getString("LAN_ID"));
				ecVO.setLan_idnumber(rs.getString("LAN_IDNUMBER"));
				ecVO.setHou_id(rs.getString("HOU_ID"));
				ecVO.setEle_rent_money(rs.getInt("ELE_RENT_MONEY"));
				ecVO.setEle_deposit_money(rs.getInt("ELE_DEPOSIT_MONEY"));
				ecVO.setEle_rent_time(rs.getInt("ELE_RENT_TIME"));
				ecVO.setEle_rent_f_day(rs.getDate("ELE_RENT_F_DAY"));
				ecVO.setEle_rent_l_day(rs.getDate("ELE_RENT_L_DAY"));
				ecVO.setEle_singdate(rs.getDate("ELE_SINGDATE"));
				ecVO.setEle_con_status(rs.getString("ELE_CON_STATUS"));
				ecVO.setBill_paymenttype(rs.getString("BILL_PAYMENTTYPE"));
				ecVO.setEle_con_note(rs.getString("ELE_CON_NOTE"));
				
				/*
				 "SELECT * FROM ELE_CONTRACT WHERE MEM_ID = ?";
				 */
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
}
