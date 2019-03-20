package com.goodhouse.good_ord_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Good_ord_listJDBCDAO implements Good_ord_listDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA106";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO GOOD_ORD_LIST (good_id, good_ord_id, good_ord_amo) "
			+ "VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT good_id, good_ord_id, good_ord_amo FROM GOOD_ORD_LIST ORDER BY good_id";
	private static final String GET_PART_STMT = "SELECT good_id, good_ord_id, good_ord_amo "
			+ "FROM GOOD_ORD_LIST WHERE good_ord_id = ? ORDER BY good_id";
	private static final String GET_ONE_STMT = 
			"SELECT good_id, good_ord_id, good_ord_amo FROM GOOD_ORD_LIST "
			+ "WHERE good_id = ? and good_ord_id = ?";
	private static final String GET_DISTINCT_ID = "SELECT DISTINCT good_id FROM GOOD_ORD_LIST ORDER BY good_id";
	private static final String DELETE = "DELETE FROM GOOD_ORD_LIST WHERE good_id = ? and good_ord_id = ?";
	private static final String UPDATE = 
			"UPDATE GOOD_ORD_LIST SET good_ord_amo = ?"
			+ "WHERE good_id = ? and good_ord_id = ?";
	@Override
	public void insert(Good_ord_listVO good_ord_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, good_ord_listVO.getGood_id());
			pstmt.setString(2, good_ord_listVO.getGood_ord_id());
			pstmt.setInt(3, good_ord_listVO.getGood_ord_amo());
			
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
	public void update(Good_ord_listVO good_ord_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, good_ord_listVO.getGood_ord_amo());
			pstmt.setString(2, good_ord_listVO.getGood_id());
			pstmt.setString(3,good_ord_listVO.getGood_ord_id());
			
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
	public void delete(String good_id,String good_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, good_id);
			pstmt.setString(2, good_ord_id);
			
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
	public Good_ord_listVO findByPrimaryKey(String good_id, String good_ord_id) {
		Good_ord_listVO good_ord_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, good_id);
			pstmt.setString(2, good_ord_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ord_listVO = new Good_ord_listVO();
				good_ord_listVO.setGood_id(rs.getString("good_id"));
				good_ord_listVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ord_listVO.setGood_ord_amo(rs.getInt("good_ord_amo"));
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
		return good_ord_listVO;
	}

	@Override
	public List<Good_ord_listVO> getAll() {
		List<Good_ord_listVO> list = new ArrayList<>();
		Good_ord_listVO good_ord_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ord_listVO = new Good_ord_listVO();
				good_ord_listVO.setGood_id(rs.getString("good_id"));
				good_ord_listVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ord_listVO.setGood_ord_amo(rs.getInt("good_ord_amo"));
				list.add(good_ord_listVO);
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
	public List<Good_ord_listVO> getPart(String good_ord_id) {
		List<Good_ord_listVO> list = new ArrayList<>();
		Good_ord_listVO good_ord_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			con = ds.getConnection();
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_PART_STMT);
			pstmt.setString(1, good_ord_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ord_listVO = new Good_ord_listVO();
				good_ord_listVO.setGood_id(rs.getString("good_id"));
				good_ord_listVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ord_listVO.setGood_ord_amo(rs.getInt("good_ord_amo"));
				list.add(good_ord_listVO);
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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_DISTINCT_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String good_id = rs.getString("good_id");
				list.add(good_id);
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
	public void insert2(Good_ord_listVO good_ord_listVO, Connection con) {
		// TODO Auto-generated method stub
		
	}
}
