package com.goodhouse.equipment_repair.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodhouse.house_noappointment.model.HouNoAppJDBCDAO;
import com.goodhouse.house_noappointment.model.HouNoAppVO;

public class EquRepJDBCDAO implements EquRepDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106_G2";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO equipment_repair (equ_rep_id, hou_id, mem_id, lan_id, equ_rep_accetime, equ_rep_staff, equ_rep_staffphone, equ_rep_event, equ_rep_picture, equ_rep_descri, equ_rep_status, equ_rep_expectime, equ_rep_finish) VALUES ('EQU'||LPAD(to_char(SEQ_EQU_REP_ID.nextval),7,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT equ_rep_id, hou_id, mem_id, lan_id, to_char(equ_rep_accetime, 'yyyy-mm-dd') equ_rep_accetime, equ_rep_staff, equ_rep_staffphone, equ_rep_event, equ_rep_picture, equ_rep_descri, equ_rep_status, to_char(equ_rep_expectime, 'yyyy-mm-dd') equ_rep_expectime, to_char(equ_rep_finish, 'yyyy-mm-dd') equ_rep_finish FROM equipment_repair order by equ_rep_id";
	private static final String GET_ONE_STMT = "SELECT equ_rep_id, hou_id, mem_id, lan_id, to_char(equ_rep_accetime, 'yyyy-mm-dd') equ_rep_accetime, equ_rep_staff, equ_rep_staffphone, equ_rep_event, equ_rep_picture, equ_rep_descri, equ_rep_status, to_char(equ_rep_expectime, 'yyyy-mm-dd') equ_rep_expectime, to_char(equ_rep_finish, 'yyyy-mm-dd') equ_rep_finish FROM equipment_repair where equ_rep_id= ?";
	private static final String DELETE = "DELETE FROM equipment_repair where equ_rep_id = ?";
	private static final String UPDATE = "UPDATE equipment_repair set hou_id=?, mem_id=?, lan_id=?, equ_rep_accetime=?, equ_rep_staff=?, equ_rep_staffphone=?, equ_rep_event=?, equ_rep_picture=?, equ_rep_descri=?, equ_rep_status=?, equ_rep_expectime=?, equ_rep_finish=? where equ_rep_id= ?";

	@Override
	public void insert(EquRepVO equRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, equRepVO.getHou_id());
			pstmt.setString(2, equRepVO.getMem_id());
			pstmt.setString(3, equRepVO.getLan_id());
			pstmt.setDate(4, equRepVO.getEqu_rep_accetime());
			pstmt.setString(5, equRepVO.getEqu_rep_staff());
			pstmt.setString(6, equRepVO.getEqu_rep_staffphone());
			pstmt.setString(7, equRepVO.getEqu_rep_event());
			pstmt.setBytes(8, equRepVO.getEqu_rep_picture());
			pstmt.setString(9, equRepVO.getEqu_rep_descri());
			pstmt.setString(10, equRepVO.getEqu_rep_status());
			pstmt.setDate(11, equRepVO.getEqu_rep_expectime());
			pstmt.setDate(12, equRepVO.getEqu_rep_finish());

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
	public void update(EquRepVO equRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, equRepVO.getHou_id());
			pstmt.setString(2, equRepVO.getMem_id());
			pstmt.setString(3, equRepVO.getLan_id());
			pstmt.setDate(4, equRepVO.getEqu_rep_accetime());
			pstmt.setString(5, equRepVO.getEqu_rep_staff());
			pstmt.setString(6, equRepVO.getEqu_rep_staffphone());
			pstmt.setString(7, equRepVO.getEqu_rep_event());
			pstmt.setBytes(8, equRepVO.getEqu_rep_picture());
			pstmt.setString(9, equRepVO.getEqu_rep_descri());
			pstmt.setString(10, equRepVO.getEqu_rep_status());
			pstmt.setDate(11, equRepVO.getEqu_rep_expectime());
			pstmt.setDate(12, equRepVO.getEqu_rep_finish());
			pstmt.setString(13, equRepVO.getEqu_rep_id());

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
	public void delete(String equ_rep_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, equ_rep_id);

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
	public EquRepVO findByPrimaryKey(String equ_rep_id) {
		EquRepVO equRepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, equ_rep_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				equRepVO = new EquRepVO();
				equRepVO.setEqu_rep_id(rs.getString("equ_rep_id"));
				equRepVO.setHou_id(rs.getString("hou_id"));
				equRepVO.setMem_id(rs.getString("mem_id"));
				equRepVO.setLan_id(rs.getString("lan_id"));
				equRepVO.setEqu_rep_accetime(rs.getDate("equ_rep_accetime"));
				equRepVO.setEqu_rep_staff(rs.getString("equ_rep_staff"));
				equRepVO.setEqu_rep_staffphone(rs.getString("equ_rep_staffphone"));
				equRepVO.setEqu_rep_event(rs.getString("equ_rep_event"));
				equRepVO.setEqu_rep_picture(rs.getBytes("equ_rep_picture"));
				equRepVO.setEqu_rep_descri(rs.getString("equ_rep_descri"));
				equRepVO.setEqu_rep_status(rs.getString("equ_rep_status"));
				equRepVO.setEqu_rep_expectime(rs.getDate("equ_rep_expectime"));
				equRepVO.setEqu_rep_finish(rs.getDate("equ_rep_finish"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			se.printStackTrace();
                                                                                                                                                    
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
		return equRepVO;
	}

	@Override
	public List<EquRepVO> getAll() {
		List<EquRepVO> list = new ArrayList<EquRepVO>();
		EquRepVO equRepVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				equRepVO = new EquRepVO();
				equRepVO.setEqu_rep_id(rs.getString("equ_rep_id"));
				equRepVO.setHou_id(rs.getString("hou_id"));
				equRepVO.setMem_id(rs.getString("mem_id"));
				equRepVO.setLan_id(rs.getString("lan_id"));
				equRepVO.setEqu_rep_accetime(rs.getDate("equ_rep_accetime"));
				equRepVO.setEqu_rep_staff(rs.getString("equ_rep_staff"));
				equRepVO.setEqu_rep_staffphone(rs.getString("equ_rep_staffphone"));
				equRepVO.setEqu_rep_event(rs.getString("equ_rep_event"));
				equRepVO.setEqu_rep_picture(rs.getBytes("equ_rep_picture"));
				equRepVO.setEqu_rep_descri(rs.getString("equ_rep_descri"));
				equRepVO.setEqu_rep_status(rs.getString("equ_rep_status"));
				equRepVO.setEqu_rep_expectime(rs.getDate("equ_rep_expectime"));
				equRepVO.setEqu_rep_finish(rs.getDate("equ_rep_finish"));
				list.add(equRepVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
//			se.printStackTrace();  //��ܧ�����~�T��
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
		EquRepJDBCDAO dao = new EquRepJDBCDAO();

		EquRepVO equRepVO1 = new EquRepVO();
		byte[] test = new byte[1];
		test[0]= 1;
		equRepVO1.setHou_id("HOU0000001");
		equRepVO1.setMem_id("M000000001");
		equRepVO1.setLan_id("L000000001");
		equRepVO1.setEqu_rep_accetime(java.sql.Date.valueOf("2019-02-16"));
		equRepVO1.setEqu_rep_staff("9527");
		equRepVO1.setEqu_rep_staffphone("2");
		equRepVO1.setEqu_rep_event("2");
		equRepVO1.setEqu_rep_picture(test);
		equRepVO1.setEqu_rep_descri("2");
		equRepVO1.setEqu_rep_status("2");
		equRepVO1.setEqu_rep_expectime(java.sql.Date.valueOf("2019-02-26"));
		equRepVO1.setEqu_rep_finish(java.sql.Date.valueOf("2019-02-26"));
		dao.insert(equRepVO1);

		EquRepVO equRepVO2 = new EquRepVO();
		equRepVO2.setHou_id("HOU0000003");
		equRepVO2.setMem_id("M000000003");
		equRepVO2.setLan_id("L000000003");
		equRepVO2.setEqu_rep_accetime(java.sql.Date.valueOf("2019-02-18"));
		equRepVO2.setEqu_rep_staff("927");
		equRepVO2.setEqu_rep_staffphone("3");
		equRepVO2.setEqu_rep_event("3");
		equRepVO2.setEqu_rep_picture(test);
		equRepVO2.setEqu_rep_descri("3");
		equRepVO2.setEqu_rep_status("3");
		equRepVO2.setEqu_rep_expectime(java.sql.Date.valueOf("2019-02-16"));
		equRepVO2.setEqu_rep_finish(java.sql.Date.valueOf("2019-02-17"));
		equRepVO2.setEqu_rep_id("EQU0000008");
		
		dao.update(equRepVO2);

		dao.delete("EQU0000009");

		EquRepVO equRepVO3 = dao.findByPrimaryKey("EQU0000007");
		System.out.print(equRepVO3.getEqu_rep_id() + ",");
		System.out.print(equRepVO3.getHou_id() + ",");
		System.out.print(equRepVO3.getMem_id() + ",");
		System.out.print(equRepVO3.getLan_id() + ",");
		System.out.print(equRepVO3.getEqu_rep_accetime() + ",");
		System.out.print(equRepVO3.getEqu_rep_staff() + ",");
		System.out.print(equRepVO3.getEqu_rep_staffphone() + ",");
		System.out.print(equRepVO3.getEqu_rep_event() + ",");
		System.out.print(equRepVO3.getEqu_rep_picture() + ",");
		System.out.print(equRepVO3.getEqu_rep_descri() + ",");
		System.out.print(equRepVO3.getEqu_rep_status() + ",");
		System.out.print(equRepVO3.getEqu_rep_expectime() + ",");
		System.out.println(equRepVO3.getEqu_rep_finish() + ",");
		System.out.println("-------------------------");

		List<EquRepVO> list = dao.getAll();
		for (EquRepVO aequRepApp : list) {
			System.out.print(aequRepApp.getEqu_rep_id() + ",");
			System.out.print(aequRepApp.getHou_id() + ",");
			System.out.print(aequRepApp.getMem_id() + ",");
			System.out.print(aequRepApp.getLan_id() + ",");
			System.out.print(aequRepApp.getEqu_rep_accetime() + ",");
			System.out.print(aequRepApp.getEqu_rep_staff() + ",");
			System.out.print(aequRepApp.getEqu_rep_staffphone() + ",");
			System.out.print(aequRepApp.getEqu_rep_event() + ",");
			System.out.print(aequRepApp.getEqu_rep_picture() + ",");
			System.out.print(aequRepApp.getEqu_rep_descri() + ",");
			System.out.print(aequRepApp.getEqu_rep_status() + ",");
			System.out.print(aequRepApp.getEqu_rep_expectime() + ",");
			System.out.print(aequRepApp.getEqu_rep_finish() + ",");
			System.out.println();
		}

	}

}
//git上傳註解用無意義