package com.goodhouse.rental_message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentMessJDBCDAO implements RentMessDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106_G2";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO rental_message (ren_mes_id,hou_id,mem_id,lan_id,ren_mes_time,ren_mes_request,ren_mes_response) VALUES ('RENM'||LPAD(to_char(SEQ_REN_MES_ID.nextval),6,'0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ren_mes_id,hou_id,mem_id,lan_id,to_char(ren_mes_time, 'yyyy-mm-dd') ren_mes_time, ren_mes_request, ren_mes_response FROM rental_message order by ren_mes_id";
	private static final String GET_ONE_STMT = "SELECT * FROM rental_message where ren_mes_id= ?";
	private static final String DELETE = "DELETE FROM rental_message where ren_mes_id = ?";
	private static final String UPDATE = "UPDATE rental_message set hou_id=?, mem_id=?, lan_id=?, ren_mes_time=?, ren_mes_request=?, ren_mes_response=? where ren_mes_id = ?";

	@Override
	public void insert(RentMessVO rentMessVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rentMessVO.getHou_id());
			pstmt.setString(2, rentMessVO.getMem_id());
			pstmt.setString(3, rentMessVO.getLan_id());
			pstmt.setDate(4, rentMessVO.getRen_mes_time());
			pstmt.setString(5, rentMessVO.getRen_mes_request());
			pstmt.setString(6, rentMessVO.getRen_mes_response());

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
	public void update(RentMessVO rentMessVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rentMessVO.getHou_id());
			pstmt.setString(2, rentMessVO.getMem_id());
			pstmt.setString(3, rentMessVO.getLan_id());
			pstmt.setDate(4, rentMessVO.getRen_mes_time());
			pstmt.setString(5, rentMessVO.getRen_mes_request());
			pstmt.setString(6, rentMessVO.getRen_mes_response());
			pstmt.setString(7, rentMessVO.getRen_mes_id());

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
	public void delete(String ren_mes_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ren_mes_id);

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
	public RentMessVO findByPrimaryKey(String ren_mes_id) {
		RentMessVO rentMessVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ren_mes_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				rentMessVO = new RentMessVO();
				rentMessVO.setRen_mes_id(rs.getString("ren_mes_id"));
				rentMessVO.setHou_id(rs.getString("hou_id"));
				rentMessVO.setMem_id(rs.getString("mem_id"));
				rentMessVO.setLan_id(rs.getString("lan_id"));
				rentMessVO.setRen_mes_time(rs.getDate("ren_mes_time"));
				rentMessVO.setRen_mes_request(rs.getString("ren_mes_request"));
				rentMessVO.setRen_mes_response(rs.getString("ren_mes_response"));

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
		return rentMessVO;
	}

	@Override
	public List<RentMessVO> getAll() {
		List<RentMessVO> list = new ArrayList<RentMessVO>();
		RentMessVO rentMessVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rentMessVO = new RentMessVO();
				rentMessVO.setRen_mes_id(rs.getString("ren_mes_id"));
				rentMessVO.setHou_id(rs.getString("hou_id"));
				rentMessVO.setMem_id(rs.getString("mem_id"));
				rentMessVO.setLan_id(rs.getString("lan_id"));
				rentMessVO.setRen_mes_time(rs.getDate("ren_mes_time"));
				rentMessVO.setRen_mes_request(rs.getString("Ren_mes_request"));
				rentMessVO.setRen_mes_response(rs.getString("Ren_mes_response"));
				list.add(rentMessVO);
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
		RentMessJDBCDAO dao = new RentMessJDBCDAO();

		RentMessVO rentMessVO1 = new RentMessVO();
		rentMessVO1.setHou_id("H000000011");
		rentMessVO1.setMem_id("M000000011");
		rentMessVO1.setLan_id("L000000011");
		rentMessVO1.setRen_mes_time(java.sql.Date.valueOf("2019-03-24"));
		rentMessVO1.setRen_mes_request("�Y���S?");
		rentMessVO1.setRen_mes_response("���AP4");
		dao.insert(rentMessVO1);
		
		RentMessVO rentMessVO2 = new RentMessVO();
		rentMessVO2.setHou_id("H000000012");
		rentMessVO2.setMem_id("M000000012");
		rentMessVO2.setLan_id("L000000012");
		rentMessVO2.setRen_mes_time(java.sql.Date.valueOf("2019-02-16"));
		rentMessVO2.setRen_mes_request("9527");
		rentMessVO2.setRen_mes_response("overhere!");
		rentMessVO2.setRen_mes_id("RENM000012");
		dao.update(rentMessVO2);

		dao.delete("RENM000011");

		RentMessVO rentMessVO3 = dao.findByPrimaryKey("RENM000010");
		System.out.print(rentMessVO3.getRen_mes_id() + ",");
		System.out.print(rentMessVO3.getHou_id() + ",");
		System.out.print(rentMessVO3.getMem_id() + ",");
		System.out.print(rentMessVO3.getLan_id() + ",");
		System.out.print(rentMessVO3.getRen_mes_time() + ",");
		System.out.print(rentMessVO3.getRen_mes_request() + ",");
		System.out.println(rentMessVO3.getRen_mes_response() + ",");
		System.out.println("-------------------------");

		List<RentMessVO> list = dao.getAll();
		for (RentMessVO aRentMessApp : list) {
			System.out.print(aRentMessApp.getRen_mes_id() + ",");
			System.out.print(aRentMessApp.getHou_id() + ",");
			System.out.print(aRentMessApp.getMem_id() + ",");
			System.out.print(aRentMessApp.getLan_id() + ",");
			System.out.print(aRentMessApp.getRen_mes_time() + ",");
			System.out.print(aRentMessApp.getRen_mes_request() + ",");
			System.out.print(aRentMessApp.getRen_mes_response() + ",");
			System.out.println();
		}

	}

}
//git上傳註解用無意義