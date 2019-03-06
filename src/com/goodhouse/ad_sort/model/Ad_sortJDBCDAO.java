package com.goodhouse.ad_sort.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.goodhouse.ad_report.model.Ad_reportVO;

public class Ad_sortJDBCDAO implements Ad_sortDAO_interface {

	private static DataSource ds = null;

//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "goodhouse";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO ad_sort(ad_sort_id, ad_forfree, ad_chargetype, ad_charge) "
			+ "VALUES ('ADS'||LPAD(to_char(AD_SORT_SEQ.nextval),7,'0'),?,?,?)";
	private static final String UPDATE =
			"UPDATE ad_sort set ad_forfree = ?,ad_chargetype = ?,ad_charge =? where ad_sort_id =?";
	private static final String DELETE =
			"DELETE FROM AD_SORT WHERE ad_sort_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT ad_sort_id, ad_forfree, ad_chargetype, ad_charge "
			+ "FROM ad_sort order by ad_sort_id";
	private static final String GET_ONE_STMT =
			"SELECT ad_sort_id, ad_forfree, ad_chargetype, ad_charge "
			+ "FROM ad_sort where ad_sort_id = ?";

	@Override
	public void insert(Ad_sortVO ad_sortVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
		

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, ad_sortVO.getAd_sort_id());
			pstmt.setString(1, ad_sortVO.getAd_forfree());
			pstmt.setString(2, ad_sortVO.getAd_chargetype());
			pstmt.setInt(3, ad_sortVO.getAd_charge());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(Ad_sortVO ad_sortVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ad_sortVO.getAd_forfree());
			pstmt.setString(2, ad_sortVO.getAd_chargetype());
			pstmt.setInt(3, ad_sortVO.getAd_charge());
			pstmt.setString(4, ad_sortVO.getAd_sort_id());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(String ad_sort_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ad_sort_id);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Ad_sortVO findByPrimaryKey(String ad_sort_id) {
		// TODO Auto-generated method stub

		Ad_sortVO ad_sortVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//					try {
//						con = ds.getConnection();
//						pstmt = con.prepareStatement(GET_ONE_STMT);
		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ad_sort_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ad_sortVO = new Ad_sortVO();
				ad_sortVO.setAd_sort_id(rs.getString("ad_sort_id"));
				ad_sortVO.setAd_forfree(rs.getString("ad_forfree"));
				ad_sortVO.setAd_chargetype(rs.getString("ad_chargetype"));
				ad_sortVO.setAd_charge(rs.getInt("ad_charge"));

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return ad_sortVO;
	}

	@Override
	public List<Ad_sortVO> getAll() {
		// TODO Auto-generated method stub
		List<Ad_sortVO> list = new ArrayList<Ad_sortVO>();
		Ad_sortVO ad_sortVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ad_sortVO = new Ad_sortVO();
				ad_sortVO.setAd_sort_id(rs.getString("ad_sort_id"));
				ad_sortVO.setAd_forfree(rs.getString("ad_forfree"));
				ad_sortVO.setAd_chargetype(rs.getString("ad_chargetype"));
				ad_sortVO.setAd_charge(rs.getInt("ad_charge"));
				list.add(ad_sortVO);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

}
