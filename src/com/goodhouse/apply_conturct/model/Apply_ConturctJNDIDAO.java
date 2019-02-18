package com.goodhouse.apply_conturct.model;

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


public class Apply_ConturctJNDIDAO implements Apply_ConturctDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INT APPLY_CONTURCT (APP_CON_ID,ELE_CON_ID,MEM_ID,HOU_ID,APP_CON_CONTENT,APP_CON_STATUS,APP_CON_OTHEER) VALUES (APP_CON_SEQ.NEXTVAL,?,?,?,?,?,? )";
	private static final String UPDATE =
			"UPDATE APPLY_CONTURCT SET ELE_CON_ID=?,MEM_ID=?,HOU_ID=?,APP_CON_CONTENT=?,APP_CON_STATUS=?,APP_CON_OTHER=? WHERE APP_CON_ID=?";
	private static final String DELETE =
			"DELETE FROM APPLY_CONTURCT WHERE APP_CON_ID=?";
	private static final String GET_ONE_STMT = 
			"SELECT APP_CON_ID,ELE_CON_ID,MEM_ID,HOU_ID,APP_CON_CONTENT,APP_CON_STATUS,APP_CON_OTHER FROM APPLY_CONTURCT WHERE APP_CON_ID=?";
	private static final String GET_ALL_STMT = 
			"SELECT APP_CON_ID,ELE_CON_ID,MEM_ID,HOU_ID,APP_CON_CONTENT,APP_CON_STATUS,APP_CON_OTHER FROM APPLY_CONTURCT ORDER BY APP_CON_ID";
	
	@Override//新增
	public void insert(Apply_ConturctVO appConVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INT APPLY_CONTURCT (APP_CON_ID,ELE_CON_ID,MEM_ID,HOU_ID,APP_CON_CONTENT,APP_CON_STATUS,APP_CON_OTHEER) VALUES (APP_CON_SEQ.NEXTVAL,?,?,?,?,?,? 
			pstmt.setString(1, appConVO.getEle_con_id());
			pstmt.setString(2, appConVO.getMem_id());
			pstmt.setString(3, appConVO.getHou_id());
			pstmt.setString(4, appConVO.getApp_con_content());
			pstmt.setString(5, appConVO.getApp_con_status());
			pstmt.setString(6, appConVO.getApp_con_other());
			

			pstmt.executeUpdate();

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

	@Override//修改
	public void update(Apply_ConturctVO appConVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			//UPDATE APPLY_CONTURCT SET ELE_CON_ID=?,MEM_ID=?,HOU_ID=?,APP_CON_CONTENT=?,APP_CON_STATUS=?,APP_CON_OTHER=? WHERE APP_CON_ID=?

			pstmt.setString(1, appConVO.getEle_con_id());
			pstmt.setString(2, appConVO.getMem_id());
			pstmt.setString(3, appConVO.getHou_id());
			pstmt.setString(4, appConVO.getApp_con_content());
			pstmt.setString(5, appConVO.getApp_con_status());
			pstmt.setString(6, appConVO.getApp_con_other());
			pstmt.setString(7, appConVO.getApp_con_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override//刪除
	public void delete(Apply_ConturctVO app_con_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			//DELETE FROM APPLY_CONTURCT WHERE APP_CON_ID=?
			pstmt.setObject(1, app_con_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Apply_ConturctVO findByPrimaryKey(String app_con_id) {
		
		Apply_ConturctVO appConVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, app_con_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				appConVO = new Apply_ConturctVO();
				appConVO.setApp_con_id(rs.getString("app_con_id"));
				appConVO.setEle_con_id(rs.getString("ele_con_id"));
				appConVO.setMem_id(rs.getString("mem_id"));
				appConVO.setHou_id(rs.getString("hou_id"));
				appConVO.setApp_con_content(rs.getString("app_con_content"));
				appConVO.setApp_con_status(rs.getString("app_con_status"));
				appConVO.setApp_con_other(rs.getString("app_con_other"));
			}

			// Handle any driver errors
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
		return appConVO;
	}

	@Override//查詢全部
	public List<Apply_ConturctVO> getAll() {
		
		List<Apply_ConturctVO> list = new ArrayList<Apply_ConturctVO>();
		Apply_ConturctVO appConVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			//SELECT APP_CON_ID,ELE_CON_ID,MEM_ID,HOU_ID,APP_CON_CONTENT,APP_CON_STATUS,APP_CON_OTHER FROM APPLY_CONTURCT ORDER BY APP_CON_ID
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				appConVO = new Apply_ConturctVO();
				appConVO.setApp_con_id(rs.getString("app_con_id"));
				appConVO.setEle_con_id(rs.getString("ele_con_id"));
				appConVO.setMem_id(rs.getString("mem_id"));
				appConVO.setHou_id(rs.getString("hou_id"));
				appConVO.setApp_con_content(rs.getString("app_con_content"));
				appConVO.setApp_con_status(rs.getString("app_con_status"));
				appConVO.setApp_con_other(rs.getString("app_con_other"));
				list.add(appConVO); // Store the row in the list
			}

			// Handle any driver errors
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

}
