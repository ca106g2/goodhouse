package com.goodhouse.employee_permission.model;

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

import com.goodhouse.employee_permission.model.Emp_PerVO;

public class Emp_PerDAO implements Emp_PerDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = 
			"INSERT INTO EMPLOYEE_PERMISSION VALUES (?,?) ";
		private static final String GET_ALL = 
			"SELECT * FROM EMPLOYEE_PERMISSION ";
		private static final String GET_ONE = 
			"SELECT * FROM EMPLOYEE_PERMISSION where EMP_ID = ? AND PER_ID = ? ";
		private static final String DELETE = 
			"DELETE FROM EMPLOYEE_PERMISSION where EMP_ID = ? AND PER_ID = ? ";
		private static final String UPDATE = 
			"UPDATE EMPLOYEE_PERMISSION set EMP_ID = ?, PER_ID=?";
		private static final String GET_PART_OF_ONE_STMT = 
				"SELECT * FROM EMPLOYEE_PERMISSION where EMP_ID = ? ";
		private static final String DELETE_PER = 
				"DELETE FROM EMPLOYEE_PERMISSION where EMP_ID = ?";
			private static final String UPDATE_STEP1 = 
				"SELECT * FROM EMPLOYEE_PERMISSION where EMP_ID = ?";
			private static final String UPDATE_STEP2 = 
				"DELETE FROM EMPLOYEE_PERMISSION where EMP_ID = ?";
			private static final String UPDATE_STEP3 = 
				"INSERT INTO EMPLOYEE_PERMISSION (EMP_ID, PER_ID) VALUES (?, ?)";//??
		
		
		
		
	@Override
	public void insert(Emp_PerVO emp_perVo) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, emp_perVo.getEmp_id());
			pstmt.setString(2, emp_perVo.getPer_id());
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
		
	

	@Override
	public void update (Emp_PerVO emp_perVO_old , Emp_PerVO  emp_perVO_new) {
		List<Emp_PerVO> list = new ArrayList<Emp_PerVO>();
		Emp_PerVO emp_perVOtemp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STEP1);
			pstmt.setString(1, emp_perVO_old.getEmp_id());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp_perVOtemp = new Emp_PerVO();
				emp_perVOtemp.setEmp_id(rs.getString("emp_id"));
				emp_perVOtemp.setPer_id(rs.getString("per_id"));
				list.add(emp_perVOtemp);
			}
			
			for(Emp_PerVO emp_perVO_each : list) {
				if(emp_perVO_each.getPer_id().equals(emp_perVO_old.getPer_id())) {
					emp_perVO_each.setPer_id(emp_perVO_new.getPer_id());
				}
			}
			
			pstmt = null;
			pstmt = con.prepareStatement(UPDATE_STEP2);
			pstmt.setString(1, emp_perVO_old.getEmp_id());
			pstmt.executeUpdate();
			
			
			for(Emp_PerVO emp_perVO_each : list) {
				pstmt = null;
				pstmt = con.prepareStatement(UPDATE_STEP3);
				pstmt.setString(1, emp_perVO_each.getEmp_id());
				pstmt.setString(2, emp_perVO_each.getPer_id());
				
				pstmt.executeUpdate();
			}
			
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
	@Override
	public void delete(String emp_ID , String per_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_ID);
			pstmt.setString(2, per_ID);

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
		
		
	
	public void deletePer(String emp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_PER);

			pstmt.setString(1, emp_id);
			
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
	@Override
	public List<Emp_PerVO> findByPartOfOnePrimaryKey(String emp_id) {
		List<Emp_PerVO> list = new ArrayList<>();
		Emp_PerVO emp_perVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);

			pstmt.setString(1, emp_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empAuthVO 也稱為 Domain objects
				emp_perVO = new Emp_PerVO();
				emp_perVO.setEmp_id(rs.getString("emp_ID"));
				emp_perVO.setPer_id(rs.getString("per_ID"));
				
				list.add(emp_perVO);
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
	
	public List<String> findByPartOfOnePrimaryKey2(String emp_id) {
		List<String> list = new ArrayList<>();
		String per_id = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);
			
			pstmt.setString(1, emp_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empAuthVO 也稱為 Domain objects
				per_id = rs.getString("per_id");
				list.add(per_id);
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
		
	

	@Override
	
	


	public Emp_PerVO findByPrimaryKey(String emp_id ,String per_id) {
		// TODO Auto-generated method stub
		Emp_PerVO emp_PerVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				emp_PerVO = new Emp_PerVO();
				emp_PerVO.setEmp_id(rs.getString("emp_id"));
				emp_PerVO.setPer_id(rs.getString("per_id"));
				
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
		return emp_PerVO;
	}
		
	

	@Override
	public List<Emp_PerVO> getall() {
		// TODO Auto-generated method stub
		List<Emp_PerVO> list = new ArrayList<Emp_PerVO>();
		Emp_PerVO emp_PerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				emp_PerVO = new Emp_PerVO();
				emp_PerVO.setEmp_id(rs.getString("emp_id"));
				emp_PerVO.setPer_id(rs.getString("per_id"));
				
				list.add(emp_PerVO);
				
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
		
		public List<Emp_PerVO> insertPer(List<Emp_PerVO> listEmp_PerVO) {
			List<Emp_PerVO> listEmp_PerVO_new = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				con = ds.getConnection();
							
				
				for(Emp_PerVO emp_perVO_each : listEmp_PerVO) {
					pstmt = null;
					pstmt = con.prepareStatement(INSERT);
					pstmt.setString(1, emp_perVO_each.getEmp_id());
					pstmt.setString(2, emp_perVO_each.getPer_id());
					
					pstmt.executeUpdate();
				}
				listEmp_PerVO_new.addAll(listEmp_PerVO);
				
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
				return listEmp_PerVO_new;
			}
		
		
		
		
		
		
	}
}