package com.goodhouse.keyword.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodhouse.contract.model.ContractVO;

public class KeyWordJDBCDAO implements KeyWordDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GOODHOUSE";
	String passwd = "123456";
	
	private static final String INSERT_STMT = //新增
			"INSERT INTO KEYWORD (KW_ID,KW_KEYWORD,KW_COUNT) VALUES ('KW'||LPAD(KW_SEQ.NEXTVAL,8,0),?,?)";
	private static final String UPDATE = //修改
			"UPDATE KEYWORD SET KW_KEYWORD = ? , KW_COUNT = ? WHERE KW_ID = ?";
	private static final String DELETE = //刪除
			"DELETE FROM KEYWORD WHERE KW_ID = ? ";
	private static final String GET_ONE_STMT = //單一查詢
			"SELECT KW_ID,KW_KEYWORD,KW_COUNT FROM KEYWORD WHERE KW_ID=?";
	private static final String GET_ALL_STMT = //查詢全部
			"SELECT KW_ID,KW_KEYWORD,KW_COUNT FROM KEYWORD ORDER BY KW_ID";
	

	@Override//新增
	public void insert(KeyWordVO kwVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO KEYWORD (KW_ID,KW_KEYWORD,KW_COUNT) VALUES ('KW'||LPAD(KW_SEQ.NEXTVAL,8,0,?,?)
			
			pstmt.setString(1,kwVO.getKw_keyword());
			pstmt.setInt(2, kwVO.getKw_count());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e){
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

	@Override//修改
	public void update(KeyWordVO kwVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			//UPDATE KEYWORD SET KW_KEYWORD = ? , KW_COUNT = ? WHERE KW_ID = ?
			
			pstmt.setString(1,kwVO.getKw_keyword());
			pstmt.setInt(2, kwVO.getKw_count());
			pstmt.setString(3, kwVO.getKw_id());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override//刪除
	public void delete(String kw_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			//DELETE FROM KEYWORD WHERE KW_ID = ? 
			
			pstmt.setString(1, kw_id);
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override//單一查詢
	public KeyWordVO findByPrimaryKey(String kw_id) {
		KeyWordVO kwVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//SELECT KW_ID,KW_KEYWORD,KW_COUNT FROM KEYWORD WHERE KW_ID=?
			
			pstmt.setString(1, kw_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				kwVO = new KeyWordVO();
				kwVO.setKw_id(rs.getString("KW_ID"));
				kwVO.setKw_keyword(rs.getString("KW_KEYWORD"));
				kwVO.setKw_count(rs.getInt("KW_COUNT"));
				
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return kwVO;
	}

	@Override//全部查詢
	public List<KeyWordVO> getAll() {
		List<KeyWordVO> list = new ArrayList();
		KeyWordVO kwVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			//SELECT KW_ID,KW_KEYWORD,KW_COUNT FROM KEYWORD ORDER BY KW_ID
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				kwVO = new KeyWordVO();
				kwVO.setKw_id(rs.getString("KW_ID"));
				kwVO.setKw_keyword(rs.getString("KW_KEYWORD"));
				kwVO.setKw_count(rs.getInt("KW_COUNT"));
				list.add(kwVO);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

}
