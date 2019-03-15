package com.goodhouse.landlord.model;

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

public class LanDAO implements LanDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT=
			"INSERT INTO LANDLORD VALUES ('L'||LPAD(to_char(LAN_SEQ.NEXTVAL), 9, '0'),?,?,?,?,?)";
	private static final String GET_ALL=
			"SELECT * FROM LANDLORD order by LAN_ID ";
	private static final String GET_ONE=
			"SELECT * FROM LANDLORD where LAN_ID=? ";
	private static final String DELETE=
			"DELETE FROM LANDLORD where LAN_ID=?";
	private static final String UPDATE=
			"UPDATE LANDLORD set MEM_ID=?,LAN_RECEIPT=?,LAN_ACCOUNT=?,LAN_ACCOUNTSTATUS=?,LAN_CIZITEN=? where LAN_ID=?";
	private static final String GET_ONE_BY_MEM_ID=
			"SELECT * FROM LANDLORD where MEM_ID=?";

	@Override
	public void insert(LanVO lanVo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con= ds.getConnection();
			pstmt=con.prepareStatement(INSERT);
			
			pstmt.setString(1,lanVo.getMem_id());
			pstmt.setString(2,lanVo.getLan_receipt());
			pstmt.setString(3,lanVo.getLan_account());
			pstmt.setString(4,lanVo.getLan_accountstatus());
			pstmt.setBytes(5,lanVo.getLan_ciziten());
			
			pstmt.executeUpdate();
			
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

	@Override
	public void update(LanVO lanVo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,lanVo.getMem_id());
			pstmt.setString(2,lanVo.getLan_receipt());
			pstmt.setString(3,lanVo.getLan_account());
			pstmt.setString(4,lanVo.getLan_accountstatus());
			pstmt.setBytes(5,lanVo.getLan_ciziten());
			pstmt.setString(6,lanVo.getLan_id());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public void delete(String LAN_ID) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, LAN_ID);
			pstmt.executeUpdate();
		
		}catch (SQLException se) {
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
	public LanVO findByPrimaryKey(String lan_id) {
		// TODO Auto-generated method stub
		
		
		LanVO lanVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1,lan_id);
			
			rs = pstmt.executeQuery();
		    
				while(rs.next()) {
				lanVO =  new LanVO();
				lanVO.setLan_id(rs.getString("lan_id"));
				lanVO.setMem_id(rs.getString("mem_id"));
				lanVO.setLan_receipt(rs.getString("lan_receipt"));
				lanVO.setLan_account(rs.getString("lan_account"));
				lanVO.setLan_accountstatus(rs.getString("lan_accountstatus"));
				lanVO.setLan_ciziten(rs.getBytes("lan_ciziten"));
				} 
			
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
			return lanVO;
		}

	@Override
	public List<LanVO> getAll() {
		// TODO Auto-generated method stub
	List<LanVO> list = new ArrayList<LanVO>();
	LanVO lanVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL);
		rs = pstmt.executeQuery();
		
	  while(rs.next()) {
		  
		lanVO =  new LanVO();
		lanVO.setLan_id(rs.getString("lan_id"));
		lanVO.setMem_id(rs.getString("mem_id"));
		lanVO.setLan_receipt(rs.getString("lan_receipt"));
		lanVO.setLan_account(rs.getString("lan_account"));
		lanVO.setLan_accountstatus(rs.getString("lan_accountstatus"));
		lanVO.setLan_ciziten(rs.getBytes("lan_ciziten"));
		list.add(lanVO);
		} 
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
	@Override//利用mem_id找lan_id
	public LanVO findByMem_id(String mem_id) {
		LanVO lanVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MEM_ID);
			//private static final String GET_ONE_BY_MEM_ID=
			//"SELECT * FROM LANDLORD where MEM_ID=?";
			pstmt.setString(1,mem_id);
			
			rs = pstmt.executeQuery();
		    
				while(rs.next()) {
				lanVO =  new LanVO();
				lanVO.setLan_id(rs.getString("lan_id"));
				lanVO.setMem_id(rs.getString("mem_id"));
				lanVO.setLan_receipt(rs.getString("lan_receipt"));
				lanVO.setLan_account(rs.getString("lan_account"));
				lanVO.setLan_accountstatus(rs.getString("lan_accountstatus"));
				lanVO.setLan_ciziten(rs.getBytes("lan_ciziten"));
				} 
			
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
			return lanVO;
	}
}