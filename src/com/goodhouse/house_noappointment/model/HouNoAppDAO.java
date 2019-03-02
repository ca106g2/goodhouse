package com.goodhouse.house_noappointment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HouNoAppDAO implements HouNoAppDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO house_noappointment (hou_noapp_id,hou_id,lan_id,hou_noapp_time,hou_noapp_date) VALUES ('HNA'||LPAD(to_char(SEQ_HOU_NOAPP_ID.nextval),7,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT hou_noapp_id,hou_id,lan_id,hou_noapp_time,to_char(hou_noapp_date, 'yyyy-mm-dd') hou_noapp_date FROM house_noappointment order by hou_noapp_id";
	private static final String GET_ONE_STMT = "SELECT hou_noapp_id,hou_id,lan_id,hou_noapp_time,to_char(hou_noapp_date, 'yyyy-mm-dd') hou_noapp_date FROM house_noappointment where hou_noapp_id= ?";
	private static final String DELETE = "DELETE FROM house_noappointment where hou_noapp_id = ?";
	private static final String UPDATE = "UPDATE house_noappointment set hou_id=?, lan_id=?, hou_noapp_time=?, hou_noapp_date=? where hou_noapp_id= ?";

	@Override
	public void insert(HouNoAppVO houNoAppVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, houNoAppVO.getHou_id());
			pstmt.setString(2, houNoAppVO.getLan_id());
			pstmt.setString(3, houNoAppVO.getHou_noapp_time());
			pstmt.setDate(4, houNoAppVO.getHou_noapp_date());

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
	public void update(HouNoAppVO houNoAppVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, houNoAppVO.getHou_id());
			pstmt.setString(2, houNoAppVO.getLan_id());
			pstmt.setString(3, houNoAppVO.getHou_noapp_time());
			pstmt.setDate(4, houNoAppVO.getHou_noapp_date());
			pstmt.setString(5, houNoAppVO.getHou_noapp_id());

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
	public void delete(String hou_noapp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, hou_noapp_id);


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
	public HouNoAppVO findByPrimaryKey(String hou_noapp_id) {

		HouNoAppVO houNoAppVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, hou_noapp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				houNoAppVO = new HouNoAppVO();
				houNoAppVO.setHou_noapp_id(rs.getString("hou_noapp_id"));
				houNoAppVO.setHou_id(rs.getString("hou_id"));
				houNoAppVO.setLan_id(rs.getString("lan_id"));
				houNoAppVO.setHou_noapp_time(rs.getString("hou_noapp_time"));
				houNoAppVO.setHou_noapp_date(rs.getDate("hou_noapp_date"));

			}

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

		return houNoAppVO;
	}

	@Override
	public List<HouNoAppVO> getAll() {
		List<HouNoAppVO> list = new ArrayList<HouNoAppVO>();
		HouNoAppVO houNoAppVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				houNoAppVO = new HouNoAppVO();
				houNoAppVO.setHou_noapp_id(rs.getString("hou_noapp_id"));
				houNoAppVO.setHou_id(rs.getString("hou_id"));
				houNoAppVO.setLan_id(rs.getString("lan_id"));
				houNoAppVO.setHou_noapp_time(rs.getString("hou_noapp_time"));
				houNoAppVO.setHou_noapp_date(rs.getDate("hou_noapp_date"));
				list.add(houNoAppVO);
			}

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

		return list;

	}

}
//git上傳註解用無意義