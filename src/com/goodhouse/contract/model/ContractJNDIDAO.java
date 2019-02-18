package com.goodhouse.contract.model;

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

public class ContractJNDIDAO implements ContractDAO_interface{
	
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
			"INSERT INTO CONTRACT (CON_ID,CON_NAME,CON_CONTENT) VALUES ('CON'||LPAD(CON_SEQ.NEXTVAL,7,0),?,?)";
	private static final String UPDATE = //修改指令
			"UPDATE CONTRACT SET CON_NAME=? , CON_CONTENT = ? WHERE CON_ID = ?";
	private static final String DELETE = //刪除指令
			"DELETE FROM CONTRACT WHERE CON_ID = ? ";	
	private static final String GET_ONE_STMT = //查詢指令
			"SELECT CON_ID,CON_NAME,CON_CONTENT FROM CONTRACT WHERE CON_ID=?";
	private static final String GET_ALL_STMT = //查詢指令
			"SELECT CON_ID,CON_NAME,CON_CONTENT FROM CONTRACT ORDER BY CON_ID";
	
	@Override//新增
	public void insert(ContractVO conVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO CONTRACT (CON_ID,CON_NAME,CON_CONTENT) VALUES ('CON'||LPAD(CON_SEQ.NEXTVAL,7,0),?,?)
			
			pstmt.setString(1,conVO.getCon_name());
			pstmt.setString(2, conVO.getCon_content());
			
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
	public void update(ContractVO conVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			//UPDATE CONTRACT SET CON_NAME=? , CON_CONTENT = ? WHERE CON_ID = ?
			
			pstmt.setString(1,conVO.getCon_name());
			pstmt.setString(2, conVO.getCon_content());
			pstmt.setString(3, conVO.getCon_id());
			
			pstmt.executeUpdate();
			
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

	@Override//刪除資料
	public void delete(String con_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, con_id);
			
			pstmt.executeUpdate();
			
			
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
	public ContractVO findByPrimaryKey(String con_id) {
		
		ContractVO conVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//SELECT CON_ID,CON_NAME,CON_CONTENT FROM CONTRACT WHERE CON_ID=?
			
			pstmt.setString(1, con_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				conVO = new ContractVO();
				conVO.setCon_id(rs.getString("CON_ID"));
				conVO.setCon_name(rs.getString("CON_NAME"));
				conVO.setCon_content(rs.getString("CON_CONTENT"));
				
			}
			
		}catch (SQLException se) {
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
		
		return conVO;
	}

	@Override//查詢全部
	public List<ContractVO> getAll() {
		List<ContractVO> list = new ArrayList();
		ContractVO conVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			//SELECT CON_ID,CON_NAME,CON_CONTENT FROM CONTRACT ORDER BY CON_ID
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				conVO = new ContractVO();
				conVO.setCon_id(rs.getString("CON_ID"));
				conVO.setCon_name(rs.getString("CON_NAME"));
				conVO.setCon_content(rs.getString("CON_CONTENT"));
				list.add(conVO);
				
			}
			
			
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
