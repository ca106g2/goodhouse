package android.com.goodhouse.house_track.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.com.goodhouse.maim.MyData;





public class House_TrackJDBCDAO implements House_TrackDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "GOODHOUSE";
	String passwd = "123456";
	
	private static final String INSERT_STMT=
			"INSERT INTO HOUSE_TRACK (HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS) VALUES ('HT'||LPAD(HOU_TRA_SEQ.NEXTVAL,8,0),?,?,?)";
	private static final String UPDATE = 
			"UPDATE HOUSE_TRACK SET MEM_ID=?, HOU_ID=?, HOU_TRA_STATUS=? WHERE HOU_TRA_ID=?";
	private static final String DELETE =
			"DELETE FROM HOUSE_TRACK WHERE HOU_TRA_ID=?";
	private static final String GET_ONE_STMT =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK WHERE HOU_TRA_ID=?";
	private static final String GET_ONE_MEMID =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK WHERE MEM_ID=?";
	private static final String GET_ALL_STMT =
			"SELECT HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS FROM HOUSE_TRACK ORDER BY HOU_TRA_ID";
	
	private static final String CHECK_HOUID_EXIST ="SELECT HOU_ID FROM HOUSE_TRACK WHERE HOU_ID =? and MEM_ID=?";
	
	private static final String DELETE2="DELETE FROM HOUSE_TRACK WHERE HOU_ID=? and mem_id=?";

	
	
	
	@Override//新增
	public void insert(House_TrackVO houTraVO){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO HOUSE_TRACK (HOU_TRA_ID,MEM_ID,HOU_ID,HOU_TRA_STATUS) VALUES ('HT'||LPAD(HOU_TRA_SEQ.NEXTVAL,8,0),?,?,?)
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

	@Override
	public boolean isHouseIdExist(String hou_id,String mem_id) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;
		
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(CHECK_HOUID_EXIST);
			
			
			ps.setString(1, hou_id);
			ps.setString(2, mem_id);
			ResultSet rs = ps.executeQuery();
			isUserIdExist = rs.next();
			return isUserIdExist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUserIdExist;
	}

	@Override
	public boolean add(House_TrackVO trackVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdded = false;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, trackVO.getMem_id());
			pstmt.setString(2, trackVO.getHou_id());
			pstmt.setString(3, trackVO.getHou_tra_status());
			
			
			int count = pstmt.executeUpdate();
			isAdded = count > 0 ? true : false;
			
			
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
					return isAdded;
	}

	@Override
	public boolean delete2(String hou_id,String mem_id) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isdelete2 = false;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE2);
			
			
			pstmt.setString(1, hou_id);
			pstmt.setString(2,mem_id);

			int count = pstmt.executeUpdate();
			isdelete2 = count > 0 ? true : false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isdelete2;
	}

	@Override
	public List<House_TrackVO> findByid(String mem_id) {
		List<House_TrackVO> listtrackVO = new ArrayList();
		House_TrackVO trackVO = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(MyData.URL,MyData.USER, MyData.PASSWORD);
			pst = con.prepareStatement(GET_ONE_MEMID);
			
			pst.setString(1, mem_id);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				trackVO = new House_TrackVO();
				trackVO.setHou_tra_id(rs.getString(1));
				trackVO.setMem_id(rs.getString(2));
				trackVO.setHou_id(rs.getString(3));
				trackVO.setHou_tra_status(rs.getString(4));
				listtrackVO.add(trackVO);
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
			if (pst != null) {
				try {
					pst.close();
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

		return listtrackVO;
	}

	
}