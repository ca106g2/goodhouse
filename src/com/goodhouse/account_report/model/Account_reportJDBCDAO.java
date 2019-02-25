package com.goodhouse.account_report.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Account_reportJDBCDAO implements Account_reportDAO_interface {
	private static DataSource ds = null;

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "goodhouse";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO account_report(acc_rep_id, emp_id, mem_id, lan_id, acc_rep_status, acc_rep_reason, acc_rep_date) VALUES ('ACR'||LPAD(to_char(ACCOUNT_REPORT_SEQ.nextval),7,'0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT acc_rep_id, emp_id, mem_id, lan_id, acc_rep_status, acc_rep_reason, acc_rep_date FROM account_report ORDER BY acc_rep_id";
	private static final String GET_ONE_STMT = 
			"SELECT acc_rep_id, emp_id, mem_id, lan_id, acc_rep_status, acc_rep_reason, acc_rep_date FROM account_report WHERE acc_rep_id=?";
	private static final String DELETE = 
			"DELETE FROM ACCOUNT_REPORT WHERE acc_rep_id = ?";
	private static final String UPDATE = 
			"UPDATE account_report SET acc_rep_status=?,acc_rep_reason=?,acc_rep_date=? where acc_rep_id=?";

	@Override
	public void insert(Account_reportVO account_reportVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, account_reportVO.getEmp_id());
			pstmt.setString(2, account_reportVO.getMem_id());
			pstmt.setString(3, account_reportVO.getLan_id());
			pstmt.setString(4, account_reportVO.getAcc_rep_status());
			pstmt.setString(5, account_reportVO.getAcc_rep_reason());
			pstmt.setDate(6, account_reportVO.getAcc_rep_date());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
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
	public void update(Account_reportVO account_reportVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, account_reportVO.getAcc_rep_status());
			pstmt.setString(2, account_reportVO.getAcc_rep_reason());
			pstmt.setDate(3, account_reportVO.getAcc_rep_date());
			pstmt.setString(4, account_reportVO.getAcc_rep_id());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// TODO Auto-generated catch block
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

	@Override // ---------���ð�����delete��type��O�_�b�������ƤW�i�H�R���@�뤣�O���ܪ��A
	public void delete(String acc_rep_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);


			pstmt.setString(1, acc_rep_id);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// TODO Auto-generated catch block
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
	public Account_reportVO findByPrimaryKey(String acc_rep_id) {
		// TODO Auto-generated method stub

		Account_reportVO account_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setString(1, acc_rep_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				account_reportVO = new Account_reportVO();
				account_reportVO.setAcc_rep_id(rs.getString("acc_rep_id"));
				account_reportVO.setEmp_id(rs.getString("emp_id"));
				account_reportVO.setMem_id(rs.getString("mem_id"));
				account_reportVO.setLan_id(rs.getString("lan_id"));
				account_reportVO.setAcc_rep_status(rs.getString("acc_rep_status"));
				account_reportVO.setAcc_rep_reason(rs.getString("acc_rep_reason"));
				account_reportVO.setAcc_rep_date(rs.getDate("acc_rep_date"));
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return account_reportVO;
	}

	@Override
	public List<Account_reportVO> getAll() {
		// TODO Auto-generated method stub
		List<Account_reportVO> list = new ArrayList<>();
		Account_reportVO account_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				account_reportVO = new Account_reportVO();
				account_reportVO.setAcc_rep_id(rs.getString("acc_rep_id"));
				account_reportVO.setEmp_id(rs.getString("emp_id"));
				account_reportVO.setMem_id(rs.getString("mem_id"));
				account_reportVO.setLan_id(rs.getString("lan_id"));
				account_reportVO.setAcc_rep_status(rs.getString("acc_rep_status"));
				account_reportVO.setAcc_rep_reason(rs.getString("acc_rep_reason"));
				account_reportVO.setAcc_rep_date(rs.getDate("acc_rep_date"));
				list.add(account_reportVO);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

}
