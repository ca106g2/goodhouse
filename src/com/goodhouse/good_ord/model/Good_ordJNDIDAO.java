package com.goodhouse.good_ord.model;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.goodhouse.good_ord_list.model.Good_ord_listJNDIDAO;
import com.goodhouse.good_ord_list.model.Good_ord_listService;
import com.goodhouse.good_ord_list.model.Good_ord_listVO;
import com.goodhouse.pointgoods.model.PointgoodsVO;

public class Good_ordJNDIDAO implements Good_ordDAO_interface{

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
			"INSERT INTO GOOD_ORD (good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_tot, good_ord_add) "
			+ "VALUES ('O'||LPAD(to_char(good_ord_seq.NEXTVAL), 9, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_tot, good_ord_add FROM GOOD_ORD ORDER BY good_ord_id";
	private static final String GET_ONE_STMT = 
			"SELECT good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_tot, good_ord_add FROM GOOD_ORD "
			+ "WHERE good_ord_id = ?";
	private static final String GET_PART_STMT = 
			"SELECT good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_tot, good_ord_add FROM GOOD_ORD "
			+ "WHERE mem_id = ? ORDER BY good_ord_id";
	private static final String DELETE = "DELETE FROM GOOD_ORD WHERE good_ord_id = ?";
	private static final String UPDATE = 
			"UPDATE GOOD_ORD SET mem_id=?, good_ord_dat=?, good_ord_sta=?, good_ord_nam=?, good_ord_tot=?, good_ord_add=? "
			+ "WHERE good_ord_id = ?";
	@Override
	public void insert(Good_ordVO good_ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, good_ordVO.getMem_id());
			pstmt.setTimestamp(2, good_ordVO.getGood_ord_dat());
			pstmt.setString(3, good_ordVO.getGood_ord_sta());
			pstmt.setString(4, good_ordVO.getGood_ord_nam());
			pstmt.setInt(5, good_ordVO.getGood_ord_tot());
			pstmt.setString(6, good_ordVO.getGood_ord_add());
			
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
	public void update(Good_ordVO good_ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, good_ordVO.getMem_id());
			pstmt.setTimestamp(2, good_ordVO.getGood_ord_dat());
			pstmt.setString(3, good_ordVO.getGood_ord_sta());
			pstmt.setString(4, good_ordVO.getGood_ord_nam());
			pstmt.setInt(5, good_ordVO.getGood_ord_tot());
			pstmt.setString(6, good_ordVO.getGood_ord_add());
			pstmt.setString(7, good_ordVO.getGood_ord_id());
			
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
	public void delete(String good_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, good_ord_id);
			
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
	public Good_ordVO findByPrimaryKey(String good_ord_id) {
		Good_ordVO good_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
				good_ordVO.setGood_ord_tot(rs.getInt("good_ord_tot"));
				good_ordVO.setGood_ord_add(rs.getString("good_ord_add"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ordVO = new Good_ordVO();
				good_ordVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ordVO.setMem_id(rs.getString("mem_id"));
				good_ordVO.setGood_ord_dat(rs.getTimestamp("good_ord_dat"));
				good_ordVO.setGood_ord_sta(rs.getString("good_ord_sta"));
				good_ordVO.setGood_ord_nam(rs.getString("good_ord_nam"));
				good_ordVO.setGood_ord_tot(rs.getInt("good_ord_tot"));
				good_ordVO.setGood_ord_add(rs.getString("good_ord_add"));
				list.add(good_ordVO);
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
	public void inserOrder(Good_ordVO good_ordVO, List<PointgoodsVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);
			
			String cols[] = {"GOOD_ORD_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, good_ordVO.getMem_id());
			pstmt.setTimestamp(2, good_ordVO.getGood_ord_dat());
			pstmt.setString(3, good_ordVO.getGood_ord_sta());
			pstmt.setString(4, good_ordVO.getGood_ord_nam());
			pstmt.setInt(5, good_ordVO.getGood_ord_tot());
			pstmt.setString(6, good_ordVO.getGood_ord_add());
			
			pstmt.executeUpdate();
			
			// 掘取對應的自增主鍵
			String next_good_ord_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_good_ord_id = rs.getString(1);
				System.out.println(next_good_ord_id + "新增成功的主鍵");
			} else {
				System.out.println("未取得自增主鍵");
			}
			rs.close();
			Good_ord_listService good_ord_listSvc = new Good_ord_listService();
			for(PointgoodsVO pointgoodsVO : list) {
				Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
				good_ord_listVO.setGood_id(pointgoodsVO.getGood_id());
				good_ord_listVO.setGood_ord_id(next_good_ord_id);
				good_ord_listVO.setGood_ord_amo(pointgoodsVO.getGood_nee());
				good_ord_listSvc.addGood_ord_list2(good_ord_listVO, con);
			}
			
			// 2.設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("list.size()" + list.size());
//			System.out.println("新增訂單編號 " + next_good_ord_id + "共有" + list.size() + "筆明細");
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(con != null) {
				try {
					System.err.println("Transaction is being ");
					System.err.println("rolled back-由-good_ord");
					con.rollback();
				} catch (SQLException excep ) {
					throw new RuntimeException("roll back error occured. " + excep.getMessage());
				}
			}
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
	public List<Good_ordVO> findByMem_id(String mem_id) {
		List<Good_ordVO> list = new ArrayList<>();
		Good_ordVO good_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_STMT);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				good_ordVO = new Good_ordVO();
				good_ordVO.setGood_ord_id(rs.getString("good_ord_id"));
				good_ordVO.setMem_id(rs.getString("mem_id"));
				good_ordVO.setGood_ord_dat(rs.getTimestamp("good_ord_dat"));
				good_ordVO.setGood_ord_sta(rs.getString("good_ord_sta"));
				good_ordVO.setGood_ord_nam(rs.getString("good_ord_nam"));
				good_ordVO.setGood_ord_tot(rs.getInt("good_ord_tot"));
				good_ordVO.setGood_ord_add(rs.getString("good_ord_add"));
				list.add(good_ordVO);
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
