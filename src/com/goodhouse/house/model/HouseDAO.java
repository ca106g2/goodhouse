package com.goodhouse.house.model;

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


public class HouseDAO implements HouseDAO_interface {

	private static DataSource ds = null;
	static Connection con;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "goodhouse";
//	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO house(hou_id ,hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee, hou_address, lan_id, hou_rent, hou_f_picture, hou_s_picture, hou_t_picture, hou_note) "
			+ "VALUES ('HOU'||LPAD(to_char(HOUSE_SEQ.NEXTVAL),7,0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE house SET hou_name = ? ,hou_type = ? ,hou_size = ? ,hou_property = ? ,hou_parkspace = ? ,hou_cook = ?, hou_managefee = ?, hou_address = ?, hou_rent = ? ,hou_note = ?,hou_f_picture = ?, hou_s_picture = ?, hou_t_picture = ? where hou_id = ?";//, hou_f_picture = ?, hou_s_picture = ?, hou_t_picture = ?
	private static final String DELETE = 
			"DELETE FROM HOUSE WHERE hou_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT hou_id, hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee,hou_address,lan_id, hou_rent, hou_f_picture, hou_s_picture, hou_t_picture, hou_note FROM house order by hou_id";// 
	private static final String GET_ONE_STMT = 
			"SELECT hou_id, hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee,hou_address,lan_id, hou_rent, hou_f_picture, hou_s_picture, hou_t_picture, hou_note FROM house where hou_id = ?";// 
	private static final String GET_ONE_BY_LAN_ID =
			"select * from house where lan_id=?";
	
	@Override
	public void insert(HouseVO houseVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, houseVO.getHou_id());
			pstmt.setString(1, houseVO.getHou_name());
			pstmt.setString(2, houseVO.getHou_type());
			pstmt.setString(3, houseVO.getHou_size());
			pstmt.setString(4, houseVO.getHou_property());
			pstmt.setString(5, houseVO.getHou_parkspace());
			pstmt.setString(6, houseVO.getHou_cook());
			pstmt.setString(7, houseVO.getHou_managefee());
			pstmt.setString(8, houseVO.getHou_address());
			pstmt.setString(9, houseVO.getLan_id());
			pstmt.setInt(10, houseVO.getHou_rent());
			pstmt.setBytes(11, houseVO.getHou_f_picture());
			pstmt.setBytes(12, houseVO.getHou_s_picture());
			pstmt.setBytes(13, houseVO.getHou_t_picture());
			pstmt.setString(14, houseVO.getHou_note());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. "
					+ se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
		}  finally {
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
	public void update(HouseVO houseVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER);
//	
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1, houseVO.getHou_name());
			pstmt.setString(2, houseVO.getHou_type());
			pstmt.setString(3, houseVO.getHou_size());
			pstmt.setString(4, houseVO.getHou_property());
			pstmt.setString(5, houseVO.getHou_parkspace());
			pstmt.setString(6, houseVO.getHou_cook());
			pstmt.setString(7, houseVO.getHou_managefee());
			pstmt.setString(8, houseVO.getHou_address());
//			pstmt.setString(9, houseVO.getLan_id());
			pstmt.setInt(9, houseVO.getHou_rent());
			pstmt.setString(10, houseVO.getHou_note());
			pstmt.setBytes(11, houseVO.getHou_f_picture());
			pstmt.setBytes(12, houseVO.getHou_s_picture());
			pstmt.setBytes(13, houseVO.getHou_t_picture());
			pstmt.setString(14, houseVO.getHou_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
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
	public void delete(String hou_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(DELETE);

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, hou_id);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. " + se.getMessage());
			// TODO Auto-generated catch block
			// se.printStackTrace();
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
	public HouseVO findByPrimaryKey(String hou_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, hou_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				houseVO = new HouseVO();
				houseVO.setHou_id(rs.getString("hou_id"));
				houseVO.setHou_name(rs.getString("hou_name"));
				houseVO.setHou_type(rs.getString("hou_type"));
				houseVO.setHou_size(rs.getString("hou_size"));
				houseVO.setHou_property(rs.getString("hou_property"));
				houseVO.setHou_parkspace(rs.getString("hou_parkspace"));
				houseVO.setHou_cook(rs.getString("hou_cook"));
				houseVO.setHou_managefee(rs.getString("hou_managefee"));
				houseVO.setHou_address(rs.getString("hou_address"));
				houseVO.setLan_id(rs.getString("lan_id"));
				houseVO.setHou_rent(rs.getInt("hou_rent"));
				houseVO.setHou_f_picture(rs.getBytes("hou_f_picture"));
				houseVO.setHou_s_picture(rs.getBytes("hou_s_picture"));
				houseVO.setHou_t_picture(rs.getBytes("hou_t_picture"));
				houseVO.setHou_note(rs.getString("hou_note"));

			}
		} catch (SQLException se) {
			se.printStackTrace();
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

		return houseVO;
	}

	@Override
	public List<HouseVO> getAll() {
		// TODO Auto-generated method stub
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {

				houseVO = new HouseVO();
				houseVO.setHou_id(rs.getString("hou_id"));
				houseVO.setHou_name(rs.getString("hou_name"));
				houseVO.setHou_type(rs.getString("hou_type"));
				houseVO.setHou_size(rs.getString("hou_size"));
				houseVO.setHou_property(rs.getString("hou_property"));
				houseVO.setHou_parkspace(rs.getString("hou_parkspace"));
				houseVO.setHou_cook(rs.getString("hou_cook"));
				houseVO.setHou_managefee(rs.getString("hou_managefee"));
				houseVO.setHou_address(rs.getString("hou_address"));
				houseVO.setLan_id(rs.getString("lan_id"));
				houseVO.setHou_rent(rs.getInt("hou_rent"));
				houseVO.setHou_f_picture(rs.getBytes("hou_f_picture"));
				houseVO.setHou_s_picture(rs.getBytes("hou_s_picture"));
				houseVO.setHou_t_picture(rs.getBytes("hou_t_picture"));
				houseVO.setHou_note(rs.getString("hou_note"));
				list.add(houseVO);
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

		return list;
	}

	@Override
	public HouseVO findByLanId(String lan_id) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(DRIVER);
//
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_LAN_ID);
			
			pstmt.setString(1, lan_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				houseVO = new HouseVO();
				houseVO.setHou_id(rs.getString("hou_id"));
				houseVO.setHou_name(rs.getString("hou_name"));
				houseVO.setHou_type(rs.getString("hou_type"));
				houseVO.setHou_size(rs.getString("hou_size"));
				houseVO.setHou_property(rs.getString("hou_property"));
				houseVO.setHou_parkspace(rs.getString("hou_parkspace"));
				houseVO.setHou_cook(rs.getString("hou_cook"));
				houseVO.setHou_managefee(rs.getString("hou_managefee"));
				houseVO.setHou_address(rs.getString("hou_address"));
				houseVO.setLan_id(rs.getString("lan_id"));
				houseVO.setHou_rent(rs.getInt("hou_rent"));
				houseVO.setHou_f_picture(rs.getBytes("hou_f_picture"));
				houseVO.setHou_s_picture(rs.getBytes("hou_s_picture"));
				houseVO.setHou_t_picture(rs.getBytes("hou_t_picture"));
				houseVO.setHou_note(rs.getString("hou_note"));

			}
		} catch (SQLException se) {
			se.printStackTrace();
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

		return houseVO;
	}

}
