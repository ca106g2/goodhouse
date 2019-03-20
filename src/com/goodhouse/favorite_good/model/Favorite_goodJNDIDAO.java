package com.goodhouse.favorite_good.model;

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

public class Favorite_goodJNDIDAO implements Favorite_goodDAO_interface{
	
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
			"INSERT INTO FAVORITE_GOOD (mem_id, good_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT mem_id, good_id FROM FAVORITE_GOOD ORDER BY mem_id";
	private static final String GET_PART_STMT = 
			"SELECT mem_id, good_id FROM FAVORITE_GOOD WHERE mem_id = ? ORDER BY mem_id";
	private static final String GET_ONE_STMT = 
			"SELECT mem_id, good_id FROM FAVORITE_GOOD WHERE mem_id = ? and good_id = ?";
	private static final String GET_DISTINCT_ID = "SELECT DISTINCT mem_id FROM FAVORITE_GOOD ORDER BY mem_id";
	private static final String DELETE = "DELETE FROM FAVORITE_GOOD WHERE mem_id = ? and good_id = ?";

	@Override
	public void insert(Favorite_goodVO favorite_goodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, favorite_goodVO.getMem_id());
			pstmt.setString(2, favorite_goodVO.getGood_id());
			
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
	public void delete(String mem_id,String good_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mem_id);
			pstmt.setString(2, good_id);
			
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
	public Favorite_goodVO findByPrimaryKey(String mem_id, String good_id) {
		Favorite_goodVO favorite_goodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, mem_id);
			pstmt.setString(2, good_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favorite_goodVO = new Favorite_goodVO();
				favorite_goodVO.setMem_id(rs.getString("mem_id"));
				favorite_goodVO.setGood_id(rs.getString("good_id"));
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
		return favorite_goodVO;
	}

	@Override
	public List<Favorite_goodVO> getAll() {
		List<Favorite_goodVO> list = new ArrayList<>();
		Favorite_goodVO favorite_goodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favorite_goodVO = new Favorite_goodVO();
				favorite_goodVO.setMem_id(rs.getString("mem_id"));
				favorite_goodVO.setGood_id(rs.getString("good_id"));
				list.add(favorite_goodVO);
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
	public List<Favorite_goodVO> getPart(String mem_id) {
		List<Favorite_goodVO> list = new ArrayList<>();
		Favorite_goodVO favorite_goodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_STMT);
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favorite_goodVO = new Favorite_goodVO();
				favorite_goodVO.setMem_id(rs.getString("mem_id"));
				favorite_goodVO.setGood_id(rs.getString("good_id"));
				list.add(favorite_goodVO);
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
		Favorite_goodVO favorite_goodVO = null;
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
