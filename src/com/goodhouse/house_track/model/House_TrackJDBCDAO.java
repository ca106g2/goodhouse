package com.goodhouse.house_track.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class House_TrackJDBCDAO implements House_TrackDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GOODHOUSE";
	String passwd = "123456";
	
	private static final String INSERT_STMT=
			"INSERT INTO HOUSE_TRACK (HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS) VALUES (HOU_TRA_SEQ.NEXTVAL,?,?)";
	private static final String UPDATE = 
			"UPDATE HOUSE_TRACK SET MEM_ID=?, HOU_ID=?, HOU_TRA_STATUS=? WHERE HOU_TRA_ID=?";
	private static final String DELETE =
			"DELETE FROM HOUSE_TRACK WHERE HOU_TRA_ID=?";
	private static final String GET_ONE_STMT =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK WHERE HOU_TRA_ID=?";
	private static final String GET_ALL_STMT =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK ORDER BY HOU_TRA_ID";
	@Override//新增
	public void insert(House_TrackVO houTraVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO HOUSE_TRACK (HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS) VALUES (HOU_TRA_SEQ.NEXTVAL,?,?)
			pstmt.setString(1, houTraVO.getMem_id());
			pstmt.setString(2, houTraVO.getHou_id());
			pstmt.setString(3, houTraVO.getHou_tra_status());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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

	@Override//修改
	public void update(House_TrackVO houTraVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			//UPDATE HOUSE_TRACK SET MEM_ID=?, HOU_ID=?, HOU_TRA_STATUS=? WHERE HOU_TRA_ID=?
			pstmt.setString(1, houTraVO.getMem_id());
			pstmt.setString(2, houTraVO.getHou_id());
			pstmt.setString(3, houTraVO.getHou_tra_status());
			pstmt.setString(4, houTraVO.getHou_tra_id());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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

	@Override//刪除
	public void delete(String hou_tra_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			//DELETE FROM HOUSE_TRACK WHERE HOU_TRA_ID=?
			pstmt.setString(1, hou_tra_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override//單一查詢
	public House_TrackVO findByPrimaryKey(String hou_tra_id) {
		// TODO Auto-generated method stub
		House_TrackVO houTraVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACT WHERE HOU_TRA_ID=?
			pstmt.setString(1, hou_tra_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				houTraVO = new House_TrackVO();
				houTraVO.setMem_id(rs.getString("mem_id"));
				houTraVO.setHou_id(rs.getString("hou_id"));
				houTraVO.setHou_tra_status(rs.getString("hou_tra_status"));
			
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return houTraVO;
	}

	@Override
	public List<House_TrackVO> getAll() {
		
		List<House_TrackVO> list = new ArrayList<House_TrackVO>();
		House_TrackVO houTraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			//SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK ORDER BY HOU_TRA_ID
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				houTraVO = new House_TrackVO();
				houTraVO.setHou_tra_id(rs.getString("hou_tra_id"));
				houTraVO.setMem_id(rs.getString("mem_id"));
				houTraVO.setHou_id(rs.getString("hou_id"));
				houTraVO.setHou_tra_status(rs.getString("hou_tra_status"));
;
				list.add(houTraVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	public static void main(String[] args) {
		
		House_TrackJDBCDAO houTraDAO = new House_TrackJDBCDAO();
		
		House_TrackVO houTraVO = new House_TrackVO();
		
		
		
	}
}