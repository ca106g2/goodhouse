package com.goodhouse.house_track.model;

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

public class House_TrackJNDIDAO implements House_TrackDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/goodhouse");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO HOUSE_TRACK (HOU_TRA_ID,MEM_ID,HOU_ID) VALUES ('HT'||LPAD(HOU_TRA_SEQ.NEXTVAL,8,0),?,?)";
	private static final String UPDATE = 
			"UPDATE HOUSE_TRACK SET MEM_ID=?, HOU_ID=?, HOU_TRA_STATUS=? WHERE HOU_TRA_ID=?";
	private static final String DELETE =
			"DELETE FROM HOUSE_TRACK WHERE HOU_TRA_ID=?";
	private static final String GET_ONE_STMT =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK WHERE HOU_TRA_ID=?";
	private static final String GET_ALL_STMT =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK ORDER BY HOU_TRA_ID";
	
	private static final String GET_LIST_BY_MEM_ID = 
			"select * from house_track where mem_id = ? ";
	private static final String GET_LSIT_BY_HOU_ID_AND_MEM_ID = 
			"select * from house_track where hou_id =? and mem_id = ?";
	
	@Override//新增
	public void insert(House_TrackVO houTraVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO HOUSE_TRACK (HOU_TRA_ID,MEM_ID,HOU_ID) VALUES ('HT'||LPAD(HOU_TRA_SEQ.NEXTVAL,8,0),?,?,?)
			pstmt.setString(1, houTraVO.getMem_id());
			pstmt.setString(2, houTraVO.getHou_id());
			System.out.println(houTraVO.getMem_id());
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

	@Override//修改
	public void update(House_TrackVO houTraVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			//UPDATE HOUSE_TRACK SET MEM_ID=?, HOU_ID=?, HOU_TRA_STATUS=? WHERE HOU_TRA_ID=?
			pstmt.setString(1, houTraVO.getMem_id());
			pstmt.setString(2, houTraVO.getHou_id());
			pstmt.setString(3, houTraVO.getHou_tra_id());
			
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

	@Override//刪除
	public void delete(String hou_tra_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			//DELETE FROM HOUSE_TRACK WHERE HOU_TRA_ID=?
			pstmt.setString(1, hou_tra_id);

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
	public House_TrackVO findByPrimaryKey(String hou_tra_id) {
		// TODO Auto-generated method stub
		House_TrackVO houTraVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACT WHERE HOU_TRA_ID=?
			pstmt.setString(1, hou_tra_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				houTraVO = new House_TrackVO();
				houTraVO.setMem_id(rs.getString("mem_id"));
				houTraVO.setHou_id(rs.getString("hou_id"));
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			//SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK ORDER BY HOU_TRA_ID
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				houTraVO = new House_TrackVO();
				houTraVO.setHou_tra_id(rs.getString("hou_tra_id"));
				houTraVO.setMem_id(rs.getString("mem_id"));
				houTraVO.setHou_id(rs.getString("hou_id"));
;
				list.add(houTraVO); // Store the row in the list
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
	public List<House_TrackVO> getListByMemId(String mem_id) {
		List<House_TrackVO> list = new ArrayList<House_TrackVO>();
		House_TrackVO houTraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_MEM_ID);
			//"select * from house_track where mem_id = ? "
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				houTraVO = new House_TrackVO();
				houTraVO.setHou_tra_id(rs.getString("hou_tra_id"));
				houTraVO.setMem_id(rs.getString("mem_id"));
				houTraVO.setHou_id(rs.getString("hou_id"));

				list.add(houTraVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public House_TrackVO findByHouIdAndMem_id(String hou_id, String mem_id) {
		House_TrackVO houTraVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LSIT_BY_HOU_ID_AND_MEM_ID);
			//"select * from house_track where hou_id =? and mem_id = ?";
			
			pstmt.setString(1, hou_id);
			pstmt.setString(2, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				houTraVO = new House_TrackVO();
				houTraVO.setHou_tra_id(rs.getString("hou_tra_id"));
				houTraVO.setMem_id(rs.getString("mem_id"));
				houTraVO.setHou_id(rs.getString("hou_id"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

}
