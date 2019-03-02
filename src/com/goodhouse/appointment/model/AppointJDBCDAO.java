package com.goodhouse.appointment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointJDBCDAO implements AppointDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GOODHOUSE";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO appointment (appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind) VALUES ('APP'||LPAD(to_char(SEQ_APPOINT_ID.nextval),7,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,to_char(hou_app_date, 'yyyy-mm-dd') hou_app_date,app_status,app_remind FROM appointment order by appoint_id";
	private static final String GET_ONE_STMT = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,to_char(hou_app_date, 'yyyy-mm-dd') hou_app_date,app_status,app_remind FROM appointment where appoint_id = ?";
	private static final String DELETE = "DELETE FROM appointment where appoint_id = ?";
	private static final String UPDATE = "UPDATE appointment set mem_id=?, lan_id=?, hou_id=?, hou_app_time=?, hou_app_date=?, app_status=?, app_remind=? where appoint_id=?";

	public void insert(AppointVO appointVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, appointVO.getMem_id());
			pstmt.setString(2, appointVO.getLan_id());
			pstmt.setString(3, appointVO.getHou_id());
			pstmt.setString(4, appointVO.getHou_app_time());
			pstmt.setDate(5, appointVO.getHou_app_date());
			pstmt.setString(6, appointVO.getApp_status());
			pstmt.setString(7, appointVO.getApp_remind());

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
	public void update(AppointVO appointVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		}catch (SQLException se) {
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
	public void delete(String appoint_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, appoint_id);
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		}catch (SQLException se) {
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
	public AppointVO findByPrimaryKey(String appoint_id) {
		
		AppointVO appointVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, appoint_id);
			
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
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		}catch (SQLException se) {
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		}catch (SQLException se) {
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
		
		AppointJDBCDAO dao = new AppointJDBCDAO();
		
		AppointVO appVO1 = new AppointVO();
		appVO1.setMem_id("M000000001");
		appVO1.setLan_id("L000000001");
		appVO1.setHou_id("HOU0000001");
		appVO1.setHou_app_time("A1");
		appVO1.setHou_app_date(java.sql.Date.valueOf("2019-02-14"));
		appVO1.setApp_status("A0");
		appVO1.setApp_remind("A0");
		dao.insert(appVO1);
		
		
		AppointVO appVO2 = new AppointVO();
		appVO2.setMem_id("M000000005");
		appVO2.setLan_id("L000000005");
		appVO2.setHou_id("HOU0000005");
		appVO2.setHou_app_time("A2");
		appVO2.setHou_app_date(java.sql.Date.valueOf("2019-02-16"));
		appVO2.setApp_status("A1");
		appVO2.setApp_remind("A1");
		appVO2.setAppoint_id("APP0000006");
		dao.update(appVO2);
		
		dao.delete("APP0000004");
		
		AppointVO appVO3 = dao.findByPrimaryKey("APP0000003");
		System.out.print(appVO3.getAppoint_id() + ",");
		System.out.print(appVO3.getMem_id() + ",");
		System.out.print(appVO3.getLan_id() + ",");
		System.out.print(appVO3.getHou_id() + ",");
		System.out.print(appVO3.getHou_app_time() + ",");
		System.out.print(appVO3.getHou_app_date() + ",");
		System.out.print(appVO3.getApp_status() + ",");
		System.out.println(appVO3.getApp_remind() + ",");
		System.out.println("-----------------------");
		
		
		
		List<AppointVO> list = dao.getAll();
		for (AppointVO aappoint : list) {
			System.out.print(aappoint.getAppoint_id() + ",");
			System.out.print(aappoint.getMem_id() + ",");
			System.out.print(aappoint.getLan_id() + ",");
			System.out.print(aappoint.getHou_id() + ",");
			System.out.print(aappoint.getHou_app_time() + ",");
			System.out.print(aappoint.getHou_app_date() + ",");
			System.out.print(aappoint.getApp_status() + ",");
			System.out.print(aappoint.getApp_remind() + ",");
			System.out.println();
			
		}
		
	}

}
//git上傳註解用無意義