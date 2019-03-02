package com.goodhouse.house_noappointment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouNoAppJDBCDAO implements HouNoAppDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106_G2";
	String passwd = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, houNoAppVO.getHou_id());
			pstmt.setString(2, houNoAppVO.getLan_id());
			pstmt.setString(3, houNoAppVO.getHou_noapp_time());
			pstmt.setDate(4, houNoAppVO.getHou_noapp_date());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

	}

	@Override
	public void update(HouNoAppVO houNoAppVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, houNoAppVO.getHou_id());
			pstmt.setString(2, houNoAppVO.getLan_id());
			pstmt.setString(3, houNoAppVO.getHou_noapp_time());
			pstmt.setDate(4, houNoAppVO.getHou_noapp_date());
			pstmt.setString(5, houNoAppVO.getHou_noapp_id());

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
	public void delete(String hou_noapp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, hou_noapp_id);

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
	public HouNoAppVO findByPrimaryKey(String hou_noapp_id) {

		HouNoAppVO houNoAppVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
		HouNoAppJDBCDAO dao = new HouNoAppJDBCDAO();
		
		HouNoAppVO houNoAppVO1 = new HouNoAppVO();
		houNoAppVO1.setHou_id("H000000001");
		houNoAppVO1.setLan_id("L000000001");
		houNoAppVO1.setHou_noapp_time("A0");
		houNoAppVO1.setHou_noapp_date(java.sql.Date.valueOf("2019-02-15"));
		dao.insert(houNoAppVO1);
		
		HouNoAppVO houNoAppVO2 = new HouNoAppVO();
		houNoAppVO2.setHou_id("H000000007");
		houNoAppVO2.setLan_id("L000000007");
		houNoAppVO2.setHou_noapp_time("A1");
		houNoAppVO2.setHou_noapp_date(java.sql.Date.valueOf("2019-02-16"));
		houNoAppVO2.setHou_noapp_id("HNA0000001");
		dao.update(houNoAppVO2);
		
		dao.delete("1");
		
		HouNoAppVO houNoAppVO3 = dao.findByPrimaryKey("HNA0000007");
		System.out.print(houNoAppVO3.getHou_noapp_id() + ",");
		System.out.print(houNoAppVO3.getHou_id() + ",");
		System.out.print(houNoAppVO3.getLan_id() + ",");
		System.out.print(houNoAppVO3.getHou_noapp_time() + ",");
		System.out.println(houNoAppVO3.getHou_noapp_date() + ",");
		System.out.println("-------------------------");
		
		
		
		List<HouNoAppVO> list = dao.getAll();
		for (HouNoAppVO ahouNoApp : list) {
			System.out.print(ahouNoApp.getHou_noapp_id() + ",");
			System.out.print(ahouNoApp.getHou_id() + ",");
			System.out.print(ahouNoApp.getLan_id() + ",");
			System.out.print(ahouNoApp.getHou_noapp_time() + ",");
			System.out.print(ahouNoApp.getHou_noapp_date() + ",");
			System.out.println();
		}
		
		

	}

}
//git上傳註解用無意義