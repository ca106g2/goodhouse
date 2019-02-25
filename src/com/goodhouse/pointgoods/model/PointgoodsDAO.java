package com.goodhouse.pointgoods.model;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PointgoodsDAO implements PointgoodsDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO POINTGOODS (good_id, good_nam, good_dsc, good_amo, good_pri, good_sta, good_pic) "
			+ "VALUES ('P'||LPAD(to_char(pointgoods_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT good_id, good_nam, good_dsc, good_amo, good_pri, good_sta, good_pic FROM POINTGOODS ORDER BY good_id";
	private static final String GET_ONE_STMT = 
			"SELECT good_id, good_nam, good_dsc, good_amo, good_pri, good_sta, good_pic FROM POINTGOODS "
			+ "WHERE good_id = ?";
	private static final String DELETE = "DELETE FROM POINTGOODS WHERE good_id = ?";
	private static final String UPDATE = 
			"UPDATE POINTGOODS SET good_nam=?, good_dsc=?, good_amo=? ,good_pri=?, good_sta=?, good_pic=? "
			+ "WHERE good_id = ?";
	@Override
	public void insert(PointgoodsVO pointgoodsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pointgoodsVO.getGood_nam());
			pstmt.setString(2, pointgoodsVO.getGood_dsc());
			pstmt.setInt(3, pointgoodsVO.getGood_amo());
			pstmt.setInt(4, pointgoodsVO.getGood_pri());
			pstmt.setString(5, pointgoodsVO.getGood_sta());
			pstmt.setBytes(6, pointgoodsVO.getGood_pic());
			
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
	public void update(PointgoodsVO pointgoodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, pointgoodsVO.getGood_nam());
			pstmt.setString(2, pointgoodsVO.getGood_dsc());
			pstmt.setInt(3, pointgoodsVO.getGood_amo());
			pstmt.setInt(4, pointgoodsVO.getGood_pri());
			pstmt.setString(5, pointgoodsVO.getGood_sta());
			pstmt.setBytes(6, pointgoodsVO.getGood_pic());
			pstmt.setString(7, pointgoodsVO.getGood_id());
			
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
	public void delete(String good_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, good_id);
			
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
	public PointgoodsVO findByPrimaryKey(String good_id) {
		
		PointgoodsVO pointgoodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, good_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsVO = new PointgoodsVO();
				pointgoodsVO.setGood_id(rs.getString("good_id"));
				pointgoodsVO.setGood_nam(rs.getString("good_nam"));
				pointgoodsVO.setGood_dsc(rs.getString("good_dsc"));
				pointgoodsVO.setGood_amo(rs.getInt("good_amo"));
				pointgoodsVO.setGood_pri(rs.getInt("good_pri"));
				pointgoodsVO.setGood_sta(rs.getString("good_sta"));
				pointgoodsVO.setGood_pic(rs.getBytes("good_pic"));
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
		return pointgoodsVO;
	}
	@Override
	public List<PointgoodsVO> getAll() {
		List<PointgoodsVO> list = new ArrayList<>();
		PointgoodsVO pointgoodsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsVO = new PointgoodsVO();
				pointgoodsVO.setGood_id(rs.getString("good_id"));
				pointgoodsVO.setGood_nam(rs.getString("good_nam"));
				pointgoodsVO.setGood_dsc(rs.getString("good_dsc"));
				pointgoodsVO.setGood_amo(rs.getInt("good_amo"));
				pointgoodsVO.setGood_pri(rs.getInt("good_pri"));
				pointgoodsVO.setGood_sta(rs.getString("good_sta"));
				pointgoodsVO.setGood_pic(rs.getBytes("good_pic"));
				list.add(pointgoodsVO);
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