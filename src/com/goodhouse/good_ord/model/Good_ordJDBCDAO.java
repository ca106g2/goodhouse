package com.goodhouse.good_ord.model;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Good_ordJDBCDAO implements Good_ordDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA106";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO GOOD_ORD (good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_add) "
			+ "VALUES ('O'||LPAD(to_char(good_ord_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_add FROM GOOD_ORD ORDER BY good_ord_id";
	private static final String GET_ONE_STMT = 
			"SELECT good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_add FROM GOOD_ORD "
			+ "WHERE good_ord_id = ?";
	private static final String DELETE = "DELETE FROM GOOD_ORD WHERE good_ord_id = ?";
	private static final String UPDATE = 
			"UPDATE GOOD_ORD SET good_ord_dat=?, good_ord_sta=?, good_ord_nam=? ,good_ord_add=? "
			+ "WHERE good_ord_id = ?";
	@Override
	public void insert(Good_ordVO good_ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, good_ordVO.getMem_id());
			pstmt.setTimestamp(2, good_ordVO.getGood_ord_dat());
			pstmt.setString(3, good_ordVO.getGood_ord_sta());
			pstmt.setString(4, good_ordVO.getGood_ord_nam());
			pstmt.setString(5, good_ordVO.getGood_ord_add());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Good_ordVO good_ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1, good_ordVO.getGood_ord_dat());
			pstmt.setString(2, good_ordVO.getGood_ord_sta());
			pstmt.setString(3, good_ordVO.getGood_ord_nam());
			pstmt.setString(4, good_ordVO.getGood_ord_add());
			pstmt.setString(5, good_ordVO.getGood_ord_id());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String good_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, good_ord_id);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
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
	public Good_ordVO findByPrimaryKey(String good_ord_id) {
		Good_ordVO good_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, good_ord_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ordVO = new Good_ordVO();
				good_ordVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ordVO.setMem_id(rs.getString("mem_id"));
				good_ordVO.setGood_ord_dat(rs.getTimestamp("good_ord_dat"));
				good_ordVO.setGood_ord_sta(rs.getString("good_ord_sta"));
				good_ordVO.setGood_ord_nam(rs.getString("good_ord_nam"));
				good_ordVO.setGood_ord_add(rs.getString("good_ord_add"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return good_ordVO;
	}

	@Override
	public List<Good_ordVO> getAll() {
		List<Good_ordVO> list = new ArrayList<>();
		Good_ordVO good_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ordVO = new Good_ordVO();
				good_ordVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ordVO.setMem_id(rs.getString("mem_id"));
				good_ordVO.setGood_ord_dat(rs.getTimestamp("good_ord_dat"));
				good_ordVO.setGood_ord_sta(rs.getString("good_ord_sta"));
				good_ordVO.setGood_ord_nam(rs.getString("good_ord_nam"));
				good_ordVO.setGood_ord_add(rs.getString("good_ord_add"));
				list.add(good_ordVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
}
