package com.goodhouse.house_evaluate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class House_EvaluateJNDIDAO implements House_EvaluateDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/goodhouse");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = //新增指令
			"INSERT INTO HOUSE_EVALUATE (HOU_EVA_ID,MEM_ID,HOU_ID,HOU_EVA_GRADE,HOU_EVA_CONTENT) VALUES ('EVA'||LPAD(HOU_EVA_SEQ.NEXTVAL,7,0),?,?,?,?)";
	private static final String UPDATE = //修改指令
			"UPDATE HOUSE_EVALUATE SET MEM_ID = ? , HOU_ID = ? , HOU_EVA_GRADE = ? , HOU_EVA_CONTENT = ?  WHERE HOU_EVA_ID = ?";
	private static final String DELETE = //刪除指令
			"DELETE FROM HOUSE_EVALUATE WHERE HOU_EVA_ID = ? ";
	private static final String GET_ONE_STMT = //查詢指令
			"SELECT HOU_EVA_ID,MEM_ID,HOU_ID,HOU_EVA_GRADE,HOU_EVA_CONTENT FROM HOUSE_EVALUATE WHERE HOU_EVA_ID=?";
	private static final String GET_ALL_STMT = //查詢指令
			"SELECT HOU_EVA_ID,MEM_ID,HOU_ID,HOU_EVA_GRADE,HOU_EVA_CONTENT FROM HOUSE_EVALUATE ORDER BY HOU_EVA_ID";
	
	@Override//新增
	public void insert(House_EvaluateVO heVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO HOUSE_EVALUATE (HOU_EVA_ID,MEM_ID,HOU_ID,HOU_EVA_GRADE,HOU_EVA_CONTENT) VALUES ('EVA'||LPAD(HOU_EVA_SEQ.NEXTVAL,7,0),?,?,?,?)
			
			pstmt.setString(1, heVO.getMem_id());
			pstmt.setString(2, heVO.getHou_id());
			pstmt.setString(3, heVO.getHou_eva_grade());
			pstmt.setString(4, heVO.getHou_eva_content());
			
			pstmt.executeUpdate();
			
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
	
	@Override//修改
	public void update(House_EvaluateVO heVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	}
	@Override//刪除
	public void delete(String hou_eva_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			//DELETE FROM HOUSE_EVALUATE WHERE HOU_EVA_ID = ?
			pstmt.setString(1, hou_eva_id);

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
	public House_EvaluateVO findByPrimmaryKey(String hou_eva_id) {
		
		House_EvaluateVO heVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//SELECT HOU_EVA_ID,MEM_ID,HOU_ID,HOU_EVA_GRADE,HOU_EVA_CONTENT FROM HOUSE_EVALUATE WHERE HOU_EVA_ID=?
			pstmt.setString(1, hou_eva_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				heVO = new House_EvaluateVO();
				heVO.setMem_id(rs.getString("mem_id"));
				heVO.setHou_id(rs.getString("hou_id"));
				heVO.setHou_eva_grade(rs.getString("hou_eva_status"));
				heVO.setHou_eva_content(rs.getString("hou_eva_content"));
				
			
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return heVO;
	}
	@Override//查詢全部
	public List<House_EvaluateVO> getAll() {
		
		List<House_EvaluateVO> list = new ArrayList<House_EvaluateVO>();
		House_EvaluateVO heVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			//SELECT HOU_EVA_ID,MEM_ID,HOU_ID,HOU_EVA_GRADE,HOU_EVA_CONTENT FROM HOUSE_EVALUATE ORDER BY HOU_EVA_ID
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				heVO = new House_EvaluateVO();
				heVO.setHou_eva_id(rs.getString("hou_eva_id"));
				heVO.setMem_id(rs.getString("mem_id"));
				heVO.setHou_id(rs.getString("hou_id"));
				heVO.setHou_eva_grade(rs.getString("hou_eva_frade"));
				heVO.setHou_eva_content(rs.getString("hou_eva_content"));
;
				list.add(heVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
