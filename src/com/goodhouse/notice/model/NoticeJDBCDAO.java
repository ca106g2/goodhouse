package com.goodhouse.notice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodhouse.appointment.model.AppointJDBCDAO;
import com.goodhouse.appointment.model.AppointVO;

public class NoticeJDBCDAO implements NoticeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106_G2";
	String passwd = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, noticeVO.getMem_id());
			pstmt.setString(2, noticeVO.getNotice_message());
			pstmt.setString(3, noticeVO.getNotice_status());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, noticeVO.getMem_id());
			pstmt.setString(2, noticeVO.getNotice_message());
			pstmt.setString(3, noticeVO.getNotice_status());
			pstmt.setString(4, noticeVO.getNotice_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, notice_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

	public static void main(String[] args) {

		NoticeJDBCDAO dao = new NoticeJDBCDAO();

		NoticeVO noticeVO1 = new NoticeVO();

		noticeVO1.setMem_id("M000000003");
		noticeVO1.setNotice_message("���l�^�Y");
		noticeVO1.setNotice_status("A1");
		dao.insert(noticeVO1);

		NoticeVO noticeVO2 = new NoticeVO();
		noticeVO2.setMem_id("M000000003");
		noticeVO2.setNotice_message("3");
		noticeVO2.setNotice_status("3");
		noticeVO2.setNotice_id("NOT0000003");
		dao.update(noticeVO2);

		dao.delete("M000000003");

		NoticeVO noticeVO3 = dao.findByPrimaryKey("NOT0000005");
		System.out.print(noticeVO3.getNotice_id() + ",");
		System.out.print(noticeVO3.getMem_id() + ",");
		System.out.print(noticeVO3.getNotice_message() + ",");
		System.out.println(noticeVO3.getNotice_status() + ",");
		System.out.println("-----------------------");

		List<NoticeVO> list = dao.getAll();
		for (NoticeVO anotice : list) {
			System.out.print(anotice.getNotice_id() + ",");
			System.out.print(anotice.getMem_id() + ",");
			System.out.print(anotice.getNotice_message() + ",");
			System.out.print(anotice.getNotice_status() + ",");
			System.out.println();

		}

	}

}
//git上傳註解用無意義