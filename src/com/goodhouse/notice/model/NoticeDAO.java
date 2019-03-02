package com.goodhouse.notice.model;

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

import com.goodhouse.house_noappointment.model.HouNoAppVO;

public class NoticeDAO implements NoticeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO notice (notice_id,mem_id,notice_message,notice_status) VALUES ('NOT'||LPAD(to_char(SEQ_NOTICE_ID.nextval),7,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT notice_id,mem_id,notice_message,notice_status FROM notice order by notice_id";
	private static final String GET_ONE_STMT = "SELECT notice_id,mem_id,notice_message,notice_status FROM notice where notice_id= ?";
	private static final String DELETE = "DELETE FROM notice where notice_id = ?";
	private static final String UPDATE = "UPDATE notice set mem_id=?, notice_message=?, notice_status=? where notice_id= ?";

	@Override
	public void insert(NoticeVO noticeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, noticeVO.getMem_id());
			pstmt.setString(2, noticeVO.getNotice_message());
			pstmt.setString(3, noticeVO.getNotice_status());

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
	public void update(NoticeVO noticeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, noticeVO.getMem_id());
			pstmt.setString(2, noticeVO.getNotice_message());
			pstmt.setString(3, noticeVO.getNotice_status());
			pstmt.setString(4, noticeVO.getNotice_id());

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
	public void delete(String notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, notice_id);

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
	public NoticeVO findByPrimaryKey(String notice_id) {
		NoticeVO noticeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, notice_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				noticeVO = new NoticeVO();
				noticeVO.setNotice_id(rs.getString("notice_id"));
				noticeVO.setMem_id(rs.getString("mem_id"));
				noticeVO.setNotice_message(rs.getString("notice_message"));
				noticeVO.setNotice_status(rs.getString("notice_status"));

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

		return noticeVO;
	}

	@Override
	public List<NoticeVO> getAll() {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO noticeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				noticeVO = new NoticeVO();
				noticeVO.setNotice_id(rs.getString("notice_id"));
				noticeVO.setMem_id(rs.getString("mem_id"));
				noticeVO.setNotice_message(rs.getString("notice_message"));
				noticeVO.setNotice_status(rs.getString("notice_status"));
				list.add(noticeVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
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