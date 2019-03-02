package com.goodhouse.appointment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AppointJNDIDAO implements AppointDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO appointment (appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind) VALUES ('APP'||LPAD(to_char(SEQ_APP_ID.nextval),7,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,to_char(hou_app_date, 'yyyy-mm-dd') hou_app_date,app_status,app_remind FROM appointment order by appoint_id";
	private static final String GET_ONE_STMT = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,to_char(hou_app_date, 'yyyy-mm-dd') hou_app_date,app_status,app_remind FROM appointment where appoint_id = ?";
	private static final String DELETE = "DELETE FROM appointment where appoint_id = ?";
	private static final String UPDATE = "UPDATE appointment set mem_id=?, lan_id=?, hou_id=?, hou_app_time=?, hou_app_date=?, app_status=?, app_remind=? where appoint_id=?";

	@Override
	public void insert(AppointVO appointVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, appointVO.getMem_id());
			pstmt.setString(2, appointVO.getLan_id());
			pstmt.setString(3, appointVO.getHou_id());
			pstmt.setString(4, appointVO.getHou_app_time());
			pstmt.setDate(5, appointVO.getHou_app_date());
			pstmt.setString(6, appointVO.getApp_status());
			pstmt.setString(7, appointVO.getApp_remind());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AppointVO appointVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, appointVO.getMem_id());
			pstmt.setString(2, appointVO.getLan_id());
			pstmt.setString(3, appointVO.getHou_id());
			pstmt.setString(4, appointVO.getHou_app_time());
			pstmt.setDate(5, appointVO.getHou_app_date());
			pstmt.setString(6, appointVO.getApp_status());
			pstmt.setString(7, appointVO.getApp_remind());
			pstmt.setString(8, appointVO.getAppoint_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String app_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, app_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public AppointVO findByPrimaryKey(String app_id) {

		AppointVO appointVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, app_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointVO = new AppointVO();
				appointVO.setAppoint_id(rs.getString("appoint_id"));
				appointVO.setMem_id(rs.getString("mem_id"));
				appointVO.setLan_id(rs.getString("lan_id"));
				appointVO.setHou_id(rs.getString("hou_id"));
				appointVO.setHou_app_time(rs.getString("hou_app_time"));
				appointVO.setHou_app_date(rs.getDate("hou_app_date"));
				appointVO.setApp_status(rs.getString("app_status"));
				appointVO.setApp_remind(rs.getString("app_remind"));

			}

		} catch (SQLException se) {
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return appointVO;
	}

	@Override
	public List<AppointVO> getAll() {
		List<AppointVO> list = new ArrayList<AppointVO>();
		AppointVO appointVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointVO = new AppointVO();
				appointVO.setAppoint_id(rs.getString("appoint_id"));
				appointVO.setMem_id(rs.getString("mem_id"));
				appointVO.setLan_id(rs.getString("lan_id"));
				appointVO.setHou_id(rs.getString("hou_id"));
				appointVO.setHou_app_time(rs.getString("hou_app_time"));
				appointVO.setHou_app_date(rs.getDate("hou_app_date"));
				appointVO.setApp_status(rs.getString("app_status"));
				appointVO.setApp_remind(rs.getString("app_remind"));
				list.add(appointVO);
			}

		} catch (SQLException se) {
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
