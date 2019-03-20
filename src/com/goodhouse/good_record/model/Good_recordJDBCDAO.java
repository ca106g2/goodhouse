package com.goodhouse.good_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Good_recordJDBCDAO implements Good_recordDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA106";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO GOOD_RECORD (good_rec_id, mem_id, good_rec_des, good_rec_dat) "
			+ "VALUES ('R'||LPAD(to_char(good_record_seq.NEXTVAL), 9, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT good_rec_id, mem_id, good_rec_des, good_rec_dat FROM GOOD_RECORD ORDER BY good_rec_id";
	private static final String GET_PART_STMT =
			"SELECT good_rec_id, mem_id, good_rec_des, good_rec_dat FROM GOOD_RECORD WHERE mem_id = ? ORDER BY mem_id";
	private static final String GET_ONE_STMT = 
			"SELECT good_rec_id, mem_id, good_rec_des, good_rec_dat FROM GOOD_RECORD WHERE good_rec_id = ?";
	private static final String GET_DISTINCT_ID =
			"SELECT DISTINCT mem_id FROM GOOD_RECORD ORDER BY mem_id";
	private static final String DELETE = "DELETE FROM GOOD_RECORD WHERE good_rec_id = ?";
	
	@Override
	public void insert(Good_recordVO good_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, good_recordVO.getMem_id());
			pstmt.setString(2, good_recordVO.getGood_rec_des());
			pstmt.setTimestamp(3, good_recordVO.getGood_rec_dat());
			
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
	public void delete(String good_rec_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, good_rec_id);
			
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
	public Good_recordVO findByPrimaryKey(String good_rec_id) {
		Good_recordVO good_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, good_rec_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_recordVO = new Good_recordVO();
				good_recordVO.setGood_rec_id(rs.getString("good_rec_id"));
				good_recordVO.setMem_id(rs.getString("mem_id"));
				good_recordVO.setGood_rec_des(rs.getString("good_rec_des"));
				good_recordVO.setGood_rec_dat(rs.getTimestamp("good_rec_dat"));
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
		return good_recordVO;
	}

	@Override
	public List<Good_recordVO> getAll() {
		List<Good_recordVO> list = new ArrayList<>();
		Good_recordVO good_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_recordVO = new Good_recordVO();
				good_recordVO.setGood_rec_id(rs.getString("good_rec_id"));
				good_recordVO.setMem_id(rs.getString("mem_id"));
				good_recordVO.setGood_rec_des(rs.getString("good_rec_des"));
				good_recordVO.setGood_rec_dat(rs.getTimestamp("good_rec_dat"));
				list.add(good_recordVO);
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
		return list;
	}
	
	@Override
	public List<Good_recordVO> getPart(String mem_id) {
		List<Good_recordVO> list = new ArrayList<>();
		Good_recordVO good_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_PART_STMT);
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_recordVO = new Good_recordVO();
				good_recordVO.setGood_rec_id(rs.getString("good_rec_id"));
				good_recordVO.setMem_id(rs.getString("mem_id"));
				good_recordVO.setGood_rec_des(rs.getString("good_rec_des"));
				good_recordVO.setGood_rec_dat(rs.getTimestamp("good_rec_dat"));
				list.add(good_recordVO);
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
	
	@Override
	public List<String> getDisId() {
		List<String> list = new ArrayList<>();
		Good_recordVO good_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_DISTINCT_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String mem_id = rs.getString("mem_id");
				list.add(mem_id);
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
