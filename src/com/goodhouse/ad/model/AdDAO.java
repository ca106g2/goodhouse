package com.goodhouse.ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcutil_CompositeQuery_Ad;

public class AdDAO implements AdDAO_interface {
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

//	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "goodhouse";
//	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO ad(ad_id, lan_id, hou_id, ad_date, ad_sort_id, ad_status, ad_forfree, ad_statue, ad_paymethod) "
			+ "VALUES ('AD'||LPAD(to_char(AD_SEQ.nextval),8,'0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE ad set ad_date = ?,ad_sort_id = ?,ad_status = ?,"
			+ "ad_forfree = ?,ad_statue = ?,ad_paymethod = ? where ad_id = ?";
	private static final String DELETE = "DELETE FROM ad WHERE ad_id=?";
	private static final String GET_ALL_STMT = "SELECT ad_id, lan_id, hou_id, ad_date, ad_sort_id, ad_status, ad_forfree, ad_statue, ad_paymethod "
			+ "FROM ad order by ad_id";

	private static final String GET_ONE_STMT = "SELECT ad_id, lan_id, hou_id, ad_date, ad_sort_id, ad_status, ad_forfree, ad_statue, ad_paymethod "
			+ "FROM ad where ad_id = ?";

	@Override
	public void insert(AdVO adVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, adVO.getAd_id());//�]���O�ۼW�D�ةҥH����set
			pstmt.setString(1, adVO.getLan_id());
			pstmt.setString(2, adVO.getHou_id());
			pstmt.setDate(3, adVO.getAd_date());
			pstmt.setString(4, adVO.getAd_sort_id());
			pstmt.setString(5, adVO.getAd_status());
			pstmt.setString(6, adVO.getAd_forfree());
			pstmt.setString(7, adVO.getAd_statue());
			pstmt.setString(8, adVO.getAd_paymethod());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
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
	public void update(AdVO adVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1, adVO.getAd_date());
			pstmt.setString(2, adVO.getAd_sort_id());
			pstmt.setString(3, adVO.getAd_status());
			pstmt.setString(4, adVO.getAd_forfree());
			pstmt.setString(5, adVO.getAd_statue());
			pstmt.setString(6, adVO.getAd_paymethod());
			pstmt.setString(7, adVO.getAd_id());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
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
	public void delete(String ad_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, ad_id);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
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
	public AdVO findByPrimaryKey(String ad_id) {
		// TODO Auto-generated method stub

		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//			try {
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ONE_STMT);
		try {
//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ad_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVO = new AdVO();
				adVO.setAd_id(rs.getString("ad_id"));
				adVO.setLan_id(rs.getString("lan_id"));
				adVO.setHou_id(rs.getString("hou_id"));
				adVO.setAd_date(rs.getDate("ad_date"));
				adVO.setAd_sort_id(rs.getString("ad_sort_id"));
				adVO.setAd_status(rs.getString("ad_status"));
				adVO.setAd_forfree(rs.getString("ad_forfree"));
				adVO.setAd_statue(rs.getString("ad_statue"));
				adVO.setAd_paymethod(rs.getString("ad_paymethod"));
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		// TODO Auto-generated method stub
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVO = new AdVO();
				adVO.setAd_id(rs.getString("ad_id"));
				adVO.setLan_id(rs.getString("lan_id"));
				adVO.setHou_id(rs.getString("hou_id"));
				adVO.setAd_date(rs.getDate("ad_date"));
				adVO.setAd_sort_id(rs.getString("ad_sort_id"));
				adVO.setAd_status(rs.getString("ad_status"));
				adVO.setAd_forfree(rs.getString("ad_forfree"));
				adVO.setAd_statue(rs.getString("ad_statue"));
				adVO.setAd_paymethod(rs.getString("ad_paymethod"));
				list.add(adVO);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return list;
	}


	@Override
	public List<AdVO> getAll(Map<String, String[]>map){
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

			
		try {
			con = ds.getConnection();
			String finalSQL="select * from ad"
					+ jdbcutil_CompositeQuery_Ad.get_WhereCondition(map)
					+"order by ad_id";
			pstmt = con.prepareStatement(finalSQL);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAd_id(rs.getString("ad_id"));
				adVO.setLan_id(rs.getString("lan_id"));
				adVO.setHou_id(rs.getString("hou_id"));
				adVO.setAd_sort_id(rs.getString("ad_sort_id"));
				adVO.setAd_status(rs.getString("ad_status"));
				adVO.setAd_forfree(rs.getString("ad_forfree"));
				adVO.setAd_statue(rs.getString("ad_statue"));
				adVO.setAd_paymethod(rs.getString("ad_paymethod"));
				list.add(adVO);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}

}
