package com.goodhouse.ad_report.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import com.goodhouse.account_report.model.Account_reportVO;

public class Ad_reportDAO implements Ad_reportDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO ad_report(ad_rep_id, ad_id, mem_id, emp_id, ad_rep_status, ad_rep_reason, ad_rep_date) "
			+ "VALUES ('ADR'||LPAD(to_char(AD_REPORT_SEQ.nextval),7,'0'),?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE ad_report SET ad_rep_status = ?, ad_rep_reason = ?, ad_rep_date =? WHERE ad_rep_id=?";

	private static final String DELETE = "DELETE FROM ad_report WHERE ad_rep_id=?";

	private static final String GET_ALL_STMT = "SELECT ad_rep_id, ad_id, mem_id, emp_id, ad_rep_date, ad_rep_status, ad_rep_reason "
			+ "FROM ad_report order by ad_rep_id";

	private static final String GET_ONE_STMT = "SELECT ad_rep_id, ad_id, mem_id, emp_id, ad_rep_date, ad_rep_status, ad_rep_reason "
			+ "FROM ad_report where ad_rep_id =?";

	@Override
	public void insert(Ad_reportVO ad_reportVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//
//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ad_reportVO.getAd_id());
			pstmt.setString(2, ad_reportVO.getMem_id());
			pstmt.setString(3, ad_reportVO.getEmp_id());
			pstmt.setString(4, ad_reportVO.getAd_rep_status());
			pstmt.setString(5, ad_reportVO.getAd_rep_reason());
			pstmt.setDate(6, ad_reportVO.getAd_rep_date());

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
	public void update(Ad_reportVO ad_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setString(1, ad_reportVO.getAd_id());
//			pstmt.setString(2, ad_reportVO.getMem_id());
//			pstmt.setString(3, ad_reportVO.getEmp_id());
			pstmt.setString(1, ad_reportVO.getAd_rep_status());
			pstmt.setString(2, ad_reportVO.getAd_rep_reason());
			pstmt.setDate(3, ad_reportVO.getAd_rep_date());
			pstmt.setString(4, ad_reportVO.getAd_rep_id());

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
	public void delete(String ad_rep_id) {
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

			pstmt.setString(1, ad_rep_id);

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
	public Ad_reportVO findByPrimaryKey(String ad_rep_id) {
		// TODO Auto-generated method stub

		Ad_reportVO ad_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//			try {

		try {

//			Class.forName(DRIVER);

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ad_rep_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ad_reportVO = new Ad_reportVO();
				ad_reportVO.setAd_rep_id(rs.getString("ad_rep_id"));
				ad_reportVO.setAd_id(rs.getString("ad_id"));
				ad_reportVO.setMem_id(rs.getString("mem_id"));
				ad_reportVO.setEmp_id(rs.getString("emp_id"));
				ad_reportVO.setAd_rep_date(rs.getDate("ad_rep_date"));
				ad_reportVO.setAd_rep_status(rs.getString("ad_rep_status"));
				ad_reportVO.setAd_rep_reason(rs.getString("ad_rep_reason"));
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

		return ad_reportVO;
	}

	@Override
	public List<Ad_reportVO> getAll() {
		// TODO Auto-generated method stub
		List<Ad_reportVO> list = new ArrayList<Ad_reportVO>();
		Ad_reportVO ad_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ad_reportVO = new Ad_reportVO();
				ad_reportVO.setAd_rep_id(rs.getString("ad_rep_id"));
				ad_reportVO.setAd_id(rs.getString("ad_id"));
				ad_reportVO.setMem_id(rs.getString("mem_id"));
				ad_reportVO.setEmp_id(rs.getString("emp_id"));
				ad_reportVO.setAd_rep_date(rs.getDate("ad_rep_date"));
				ad_reportVO.setAd_rep_status(rs.getString("ad_rep_status"));
				ad_reportVO.setAd_rep_reason(rs.getString("ad_rep_reason"));
				list.add(ad_reportVO);
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

}
