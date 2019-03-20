package com.goodhouse.good_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Good_recordJNDIDAO implements Good_recordDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO GOOD_RECORD (good_rec_id, mem_id, good_rec_des, good_rec_poi, good_rec_dat) "
			+ "VALUES ('R'||LPAD(to_char(good_record_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT good_rec_id, mem_id, good_rec_des, good_rec_poi, good_rec_dat FROM GOOD_RECORD ORDER BY good_rec_id";
	private static final String GET_PART_STMT =
			"SELECT good_rec_id, mem_id, good_rec_des, good_rec_poi, good_rec_dat FROM GOOD_RECORD WHERE mem_id = ? ORDER BY mem_id";
	private static final String GET_ONE_STMT = 
			"SELECT good_rec_id, mem_id, good_rec_des, good_rec_poi, good_rec_dat FROM GOOD_RECORD WHERE good_rec_id = ?";
	private static final String GET_DISTINCT_ID =
			"SELECT DISTINCT mem_id FROM GOOD_RECORD ORDER BY mem_id";
	private static final String DELETE = "DELETE FROM GOOD_RECORD WHERE good_rec_id = ?";
	
	@Override
	public void insert(Good_recordVO good_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, good_recordVO.getMem_id());
			pstmt.setString(2, good_recordVO.getGood_rec_des());
			pstmt.setInt(3, good_recordVO.getGood_rec_poi());
			pstmt.setTimestamp(4, good_recordVO.getGood_rec_dat());
			
			pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, good_rec_id);
			
			pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, good_rec_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_recordVO = new Good_recordVO();
				good_recordVO.setGood_rec_id(rs.getString("good_rec_id"));
				good_recordVO.setMem_id(rs.getString("mem_id"));
				good_recordVO.setGood_rec_des(rs.getString("good_rec_des"));
				good_recordVO.setGood_rec_poi(rs.getInt("good_rec_poi"));
				good_recordVO.setGood_rec_dat(rs.getTimestamp("good_rec_dat"));
			}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_recordVO = new Good_recordVO();
				good_recordVO.setGood_rec_id(rs.getString("good_rec_id"));
				good_recordVO.setMem_id(rs.getString("mem_id"));
				good_recordVO.setGood_rec_des(rs.getString("good_rec_des"));
				good_recordVO.setGood_rec_poi(rs.getInt("good_rec_poi"));
				good_recordVO.setGood_rec_dat(rs.getTimestamp("good_rec_dat"));
				list.add(good_recordVO);
			}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_STMT);
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_recordVO = new Good_recordVO();
				good_recordVO.setGood_rec_id(rs.getString("good_rec_id"));
				good_recordVO.setMem_id(rs.getString("mem_id"));
				good_recordVO.setGood_rec_des(rs.getString("good_rec_des"));
				good_recordVO.setGood_rec_poi(rs.getInt("good_rec_poi"));
				good_recordVO.setGood_rec_dat(rs.getTimestamp("good_rec_dat"));
				list.add(good_recordVO);
			}
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
	public List<String> getDisId() {
		List<String> list = new ArrayList<>();
		Good_recordVO good_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DISTINCT_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String mem_id = rs.getString("mem_id");
				list.add(mem_id);
			}
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
}
