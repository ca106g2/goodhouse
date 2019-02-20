package com.goodhouse.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmpDAO implements EmpDAO_interface{

		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
			}catch(NamingException e) {
				e.printStackTrace();
			}
		}
	private static final String INSERT =
		"INSERT INTO EMPLOYEE VALUES ('E'||LPAD(to_char(EMP_SEQ.NEXTVAL), 9, '0'), ?,?,?,?,?)";
	private static final String GET_ALL =
		"SELECT *  FROM  EMPLOYEE order by EMP_ID ";
	private static final String GET_ONE=
		"SELECT *  FROM EMPLOYEE where EMP_ID =?";
	private static final String DELETE =
		"DELETE FROM EMPLOYEE where EMP_ID =?";
	private static final String UPDATE=
		"UPDATE EMPLOYEE set EMP_NAME=?, EMP_PHONE=?,EMP_ACCOUNT=?,EMP_PASSWORD=?,EMP_STATUS=? where EMP_ID=?";
	
	@Override
	public void insert(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setInt(2,empVO.getEmp_phone());
			pstmt.setString(3,empVO.getEmp_account());
			pstmt.setString(4,empVO.getEmp_password());
			pstmt.setString(5,empVO.getEmp_status());
			pstmt.executeUpdate();
			
			
			
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setInt(2,empVO.getEmp_phone());
			pstmt.setString(3,empVO.getEmp_account());
			pstmt.setString(4,empVO.getEmp_password());
			pstmt.setString(5,empVO.getEmp_status());
			pstmt.setString(6,empVO.getEmp_id());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A data error occured." + se.getMessage());
		}finally {
			if(pstmt != null ) {
				try {
					pstmt.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
				}
			}
		}
	
	@Override
	public void delete(String EMP_ID) {
		
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 
		 try {
			 
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(DELETE);
			 
			 pstmt.setString(1,EMP_ID);
			 
			 pstmt.executeUpdate();
			 
		 }catch(SQLException se) {
			 throw new RuntimeException("A database error occured."
					 +se.getMessage());
		 }finally {
			 if(pstmt !=null) {
				 try {
					 pstmt.close();
				 
				 }catch(SQLException se) {
					 se.printStackTrace(System.err);
				 }
			 }
			 if(con != null) {
				 try {
					 con.close();
				 }catch(Exception e) {
					 e.printStackTrace(System.err);
				 }
			 } 
		 }
	}

	@Override
	public EmpVO findByPrimaryKey(String EMP_ID) {
		
		EmpVO empVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setString(1,EMP_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				empVO  = new  EmpVO();
				
				empVO .setEmp_id(rs.getString("EMP_ID"));
				empVO .setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_phone(rs.getInt("EMP_PHONE"));
				empVO.setEmp_account(rs.getString("EMP_ACCOUNT"));
				empVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				empVO.setEmp_status(rs.getString("EMP_STATUS"));
				
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
			     }
			}
			if(pstmt != null) {
				try {
					pstmt.close();
					} catch(SQLException se) {
						se.printStackTrace(System.err);
				}
				
				if(con != null) {
					try {
						con.close();
						}catch( Exception e) {
							e.printStackTrace(System.err);
							}
						}
					}	
				}
		
		
		return empVO ;	
	}
	
		

	@Override
	public List<EmpVO> getall() {
		List<EmpVO>list = new ArrayList<EmpVO>();
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
			empVO = new EmpVO();
			empVO .setEmp_id(rs.getString("EMP_ID"));
			empVO .setEmp_name(rs.getString("EMP_NAME"));
			empVO.setEmp_phone(rs.getInt("EMP_PHONE"));
			empVO.setEmp_account(rs.getString("EMP_ACCOUNT"));
			empVO.setEmp_password(rs.getString("EMP_PASSWORD"));
			empVO.setEmp_status(rs.getString("EMP_STATUS"));
			list.add(empVO);
				
			}
			
			
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			
		}finally {
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
			
			
			
		
		
		
		
		

