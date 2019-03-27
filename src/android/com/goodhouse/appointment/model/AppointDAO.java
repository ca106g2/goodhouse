package android.com.goodhouse.appointment.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.goodhouse.maim.MyData;

public class AppointDAO implements AppointDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO appointment (appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind) VALUES ('APP'||LPAD(to_char(SEQ_APPOINT_ID.nextval),7,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,to_char(hou_app_date, 'yyyy-mm-dd') hou_app_date,app_status,app_remind FROM appointment order by appoint_id desc";
	private static final String GET_ONE_STMT = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind FROM appointment where Mem_id =? and lan_id=? and ?=(to_char(hou_app_date, 'yyyy-mm-dd')) and app_status=?";
	private static final String DELETE = "DELETE FROM appointment where appoint_id = ?";
	private static final String UPDATE = "UPDATE appointment set app_status=? where mem_id=? and lan_id=? and ?=(to_char(hou_app_date, 'yyyy-mm-dd'))";

	
	private static final String GET_DATE_BY_ALL = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind FROM appointment where lan_id=? order by hou_app_date desc";
	private static final String GET_DATE_BY_DATE = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind FROM appointment where lan_id=? and (to_char(hou_app_date, 'yyyy-mm-dd')) between ? and ?  order by hou_app_date desc";
	private static final String GET_DATE_BY_DATE2 = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind FROM appointment where mem_id=? and (to_char(hou_app_date, 'yyyy-mm-dd')) between ? and ?  order by hou_app_date desc";
	private static final String GET_DATE_BY_ALL2 = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind FROM appointment where mem_id=? order by hou_app_date desc";
	private static final String GET_ONE_STMT2 = "SELECT appoint_id,mem_id,lan_id,hou_id,hou_app_time,hou_app_date,app_status,app_remind FROM appointment where Mem_id =? and hou_id=? and ?=(to_char(hou_app_date, 'yyyy-mm-dd'))";

	
	public void insert(AppointVO appointVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, appointVO.getMem_id());
			pstmt.setString(2, appointVO.getLan_id());
			pstmt.setString(3, appointVO.getHou_id());
			pstmt.setString(4, appointVO.getHou_app_time());
			pstmt.setDate(5, appointVO.getHou_app_date());
			pstmt.setString(6, appointVO.getApp_status());
			pstmt.setString(7, appointVO.getApp_remind());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AppointVO appointVO) { // �g�� �qupdate�ҥ�_

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, appointVO.getMem_id());
			pstmt.setString(2, appointVO.getLan_id());
			pstmt.setString(3, appointVO.getHou_id());
			pstmt.setString(4, appointVO.getHou_app_time());
			pstmt.setDate(5, appointVO.getHou_app_date());
			pstmt.setString(6, appointVO.getApp_status());
			pstmt.setString(7, appointVO.getApp_remind());
			pstmt.setString(8, appointVO.getAppoint_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String appoint_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, appoint_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public AppointVO findByPrimaryKey(String appoint_id) {

		AppointVO appointVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, appoint_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointVO = new AppointVO();
				appointVO.setAppoint_id(rs.getString("appoint_id"));
				appointVO.setMem_id(rs.getString("mem_id"));
				appointVO.setLan_id(rs.getString("lan_id"));
				appointVO.setHou_id(rs.getString("hou_id"));
				appointVO.setHou_app_time(rs.getString("hou_app_time"));
				appointVO.setHou_app_date(rs.getDate("hou_app_date"));
				appointVO.setApp_status(rs.getString("app_status"));
				appointVO.setApp_remind(rs.getString("app_remind"));

			}

		} catch (SQLException se) {
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return appointVO;
	}

	@Override
	public List<AppointVO> getAll() {
		List<AppointVO> list = new ArrayList<AppointVO>();
		AppointVO appointVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointVO = new AppointVO();
				appointVO.setAppoint_id(rs.getString("appoint_id"));
				appointVO.setMem_id(rs.getString("mem_id"));
				appointVO.setLan_id(rs.getString("lan_id"));
				appointVO.setHou_id(rs.getString("hou_id"));
				appointVO.setHou_app_time(rs.getString("hou_app_time"));
				appointVO.setHou_app_date(rs.getDate("hou_app_date"));
				appointVO.setApp_status(rs.getString("app_status"));
				appointVO.setApp_remind(rs.getString("app_remind"));
				list.add(appointVO);
			}

		} catch (SQLException se) {
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<AppointVO> findByLanid(String lan_id, String start, String end) {
		List<AppointVO> list = new ArrayList<AppointVO>();
		AppointVO appointVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			if (start == null || end == null || start.isEmpty() || end.isEmpty()) {
				pstmt = con.prepareStatement(GET_DATE_BY_ALL);
				pstmt.setString(1, lan_id);

			} else {
				pstmt = con.prepareStatement(GET_DATE_BY_DATE);
				pstmt.setString(1, lan_id);
				pstmt.setString(2, start);
				pstmt.setString(3, end);

			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointVO = new AppointVO();
				appointVO.setAppoint_id(rs.getString("appoint_id"));
				appointVO.setMem_id(rs.getString("mem_id"));
				appointVO.setLan_id(rs.getString("lan_id"));
				appointVO.setHou_id(rs.getString("hou_id"));
				appointVO.setHou_app_time(rs.getString("hou_app_time"));
				appointVO.setHou_app_date(rs.getDate("hou_app_date"));
				appointVO.setApp_status(rs.getString("app_status"));
				appointVO.setApp_remind(rs.getString("app_remind"));
				list.add(appointVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("a database error occured." + e.getMessage());

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
	public boolean isUserIdExist(String mem_id,String lan_id,String hou_app_date,String app_status) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;

		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			ps = conn.prepareStatement(GET_ONE_STMT);

			ps.setString(1, mem_id);
			ps.setString(2, lan_id);
			ps.setString(3,  hou_app_date);
			ps.setString(4,app_status);
		
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
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUserIdExist;
	}

	@Override
	public boolean updatestatud(String mem_id, String app_status,String lan_id,String hou_app_date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, app_status);
			pstmt.setString(2, mem_id);
			pstmt.setString(3, lan_id);
			pstmt.setString(4, hou_app_date);
			
			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return isUpdated;
	}

	@Override
	public boolean insert2(AppointVO appointVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insert2 = false;
				
		
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);pstmt.setString(1, appointVO.getMem_id());
			pstmt.setString(2, appointVO.getLan_id());
			pstmt.setString(3, appointVO.getHou_id());
			pstmt.setString(4, appointVO.getHou_app_time());
			pstmt.setDate(5, appointVO.getHou_app_date());
			pstmt.setString(6, appointVO.getApp_status());
			pstmt.setString(7, appointVO.getApp_remind());

			int count = pstmt.executeUpdate();
			insert2 = count > 0 ? true : false;
			
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return insert2;
	}

	@Override
	public List<AppointVO> findByMemid(String mem_id, String start, String end) {
		List<AppointVO> list = new ArrayList<AppointVO>();
		AppointVO appointVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			if (start == null || end == null || start.isEmpty() || end.isEmpty()) {
				pstmt = con.prepareStatement(GET_DATE_BY_ALL2);
				pstmt.setString(1, mem_id);

			} else {
				pstmt = con.prepareStatement(GET_DATE_BY_DATE2);
				pstmt.setString(1, mem_id);
				pstmt.setString(2, start);
				pstmt.setString(3, end);
				
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointVO = new AppointVO();
				appointVO.setAppoint_id(rs.getString("appoint_id"));
				appointVO.setMem_id(rs.getString("mem_id"));
				appointVO.setLan_id(rs.getString("lan_id"));
				appointVO.setHou_id(rs.getString("hou_id"));
				appointVO.setHou_app_time(rs.getString("hou_app_time"));
				appointVO.setHou_app_date(rs.getDate("hou_app_date"));
				appointVO.setApp_status(rs.getString("app_status"));
				appointVO.setApp_remind(rs.getString("app_remind"));
				list.add(appointVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("a database error occured." + e.getMessage());

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
	public boolean isUserIdExist2(String mem_id, String hou_id, String hou_app_date) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;

		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			ps = conn.prepareStatement(GET_ONE_STMT2);

			ps.setString(1, mem_id);
			ps.setString(2, hou_id);
			ps.setString(3,  hou_app_date);
			
		
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
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUserIdExist;
	}

	
	
	}
