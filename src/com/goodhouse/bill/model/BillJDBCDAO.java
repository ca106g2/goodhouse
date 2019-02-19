package com.goodhouse.bill.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BillJDBCDAO implements BillDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GOODHOUSE";
	String passwd = "123456";
	
	private static final String INSERT_STMT = //新增
			"INSERT INTO BILL (BILL_ID,ELE_CON_ID,EMP_ID,BILL_PAY,BILL_DATE,BILL_PRODUCETIME," + 
			"BILL_STATUS,BILL_PAYMETHOD,BILL_PAYMENTTYPE) VALUES (to_char(sysdate,'yyyymmdd')||'-B'||LPAD(BILL_SEQ.NEXTVAL,5,0)," + 
			"?,?,?,?,?,?,?,?)";
	private static final String UPDATE = //修改
			"UPDATE BILL SET ELE_CON_ID = ? , EMP_ID = ? , BILL_PAY = ? , BILL_DATE = ?  , BILL_PRODUCETIME = ? , BILL_STATUS = ? , BILL_PAYMETHOD = ? , BILL_PAYMENTTYPE = ? WHERE BILL_ID = ?";
	private static final String DELETE = //刪除
			"DELETE FROM BILL WHERE BILL_ID = ? ";
	private static final String GET_ONE_STMT = //單一查詢
			"SELECT BILL_ID , ELE_CON_ID , EMP_ID , BILL_PAY , BILL_DATE , BILL_PRODUCETIME , BILL_STATUS , BILL_PAYMETHOD , BILL_PAYMENTTYPE FROM BILL WHERE BILL_ID=?";
	private static final String GET_ALL_STMT = //查詢全部
			"SELECT BILL_ID , ELE_CON_ID , EMP_ID , BILL_PAY , BILL_DATE , BILL_PRODUCETIME , BILL_STATUS , BILL_PAYMETHOD , BILL_PAYMENTTYPE FROM BILL ORDER BY BILL_ID";
	
	@Override//新增
	public void insert(BillVO bVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bVO.getEle_con_id());
			pstmt.setString(2, bVO.getEmp_id());
			pstmt.setInt(3, bVO.getBill_pay());
			pstmt.setDate(4, bVO.getBill_date());
			pstmt.setDate(5, bVO.getBill_producetime());
			pstmt.setString(6, bVO.getBill_status());
			pstmt.setString(7, bVO.getBill_paymethod());
			pstmt.setString(8, bVO.getBill_paymenttype());
			
			/*
			 "INSERT INTO BILL (BILL_ID,ELE_CON_ID,EMP_ID,BILL_PAY,BILL_DATE,BILL_PRODUCETIME," + 
			 "BILL_STATUS,BILL_PAYMETHOD,BILL_PAYMENTTYPE) VALUES (to_char(sysdate,'yyyymmdd')||'-B'||LPAD(BILL_SEQ.NEXTVAL,5,0)," + 
			 "?,?,?,?,?,?,?,?)"
			 */
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e){
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
	
	@Override//修改
	public void update(BillVO bVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bVO.getEle_con_id());
			pstmt.setString(2, bVO.getEmp_id());
			pstmt.setInt(3, bVO.getBill_pay());
			pstmt.setDate(4, bVO.getBill_date());
			pstmt.setDate(5, bVO.getBill_producetime());
			pstmt.setString(6, bVO.getBill_status());
			pstmt.setString(7, bVO.getBill_paymethod());
			pstmt.setString(8, bVO.getBill_paymenttype());
			pstmt.setString(9, bVO.getBill_id());
			
			//"UPDATE BILL SET ELE_CON_ID = ? , EMP_ID = ? , BILL_PAY = ? , BILL_DATE = ?  , BILL_PRODUCETIME = ? , BILL_STATUS = ? , BILL_PAYMETHOD = ? , BILL_PAYMENTTYPE = ? WHERE BILL_ID = ?"
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
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
	
	@Override//刪除
	public void delete(String bill_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, bill_id);
			
			//DELETE FROM BILL WHERE BILL_ID = ? 
			pstmt.executeUpdate();
			
			
		}  catch (ClassNotFoundException e) {
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
	public BillVO findByPrimaryKey(String bill_id) {
		
		BillVO bVO = new BillVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, bill_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				bVO = new BillVO();
				bVO.setBill_id(rs.getString("BILL_ID"));
				bVO.setEle_con_id(rs.getString("ELE_CON_ID"));
				bVO.setEmp_id(rs.getString("EMP_ID"));
				bVO.setBill_pay(rs.getInt("BILL_PAY"));
				bVO.setBill_date(rs.getDate("BILL_DATE"));
				bVO.setBill_producetime(rs.getDate("BILL_PRODUCETIME"));
				bVO.setBill_status(rs.getString("BILL_STATUS"));
				bVO.setBill_paymethod(rs.getString("BILL_PAYMETHOD"));
				bVO.setBill_paymenttype(rs.getString("BILL_PAYMENTTYPE"));
				
			}
		//SELECT BILL_ID , ELE_CON_ID , EMP_ID , BILL_PAY , BILL_DATE , BILL_PRODUCETIME , BILL_STATUS , BILL_PAYMETHOD , BILL_PAYMENTTYPE FROM BILL WHERE BILL_ID=?
		
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
		
		return bVO;
	}
	
	@Override//全部查詢
	public List<BillVO> getAll(){
		
		List<BillVO> list = new ArrayList();
		BillVO bVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				bVO = new BillVO();
				bVO.setBill_id(rs.getString("BILL_ID"));
				bVO.setEle_con_id(rs.getString("ELE_CON_ID"));
				bVO.setEmp_id(rs.getString("EMP_ID"));
				bVO.setBill_pay(rs.getInt("BILL_PAY"));
				bVO.setBill_date(rs.getDate("BILL_DATE"));
				bVO.setBill_producetime(rs.getDate("BILL_PRODUCETIME"));
				bVO.setBill_status(rs.getString("BILL_STATUS"));
				bVO.setBill_paymethod(rs.getString("BILL_PAYMETHOD"));
				bVO.setBill_paymenttype(rs.getString("BILL_PAYMENTTYPE"));
				
				list.add(bVO);
			}
		
		}  catch (ClassNotFoundException e) {
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
	
}
