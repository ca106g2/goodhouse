package android.com.goodhouse.house.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import android.com.goodhouse.maim.MyData;

public class HouseJDBCDAO implements HouseDAO_interface {

	private static DataSource ds = null;

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_STMT = 
			"INSERT INTO house(hou_id ,hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee, hou_address, lan_id,hou_rent,hou_note)"
			+ "VALUES ('HOU'||LPAD(HOUSE_SEQ.NEXTVAL,7,0),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE house SET hou_name = ? ,hou_type = ? ,hou_size = ? ,hou_property = ? ,hou_parkspace = ? ,hou_cook = ? ,hou_managefee = ?,hou_address = ?, hou_rent = ?, hou_note = ? where hou_id = ?";
	private static final String DELETE = 
			"DELETE FROM HOUSE WHERE hou_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT hou_id, hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee,hou_address,lan_id, hou_rent,hou_note FROM house  order by hou_id";
	private static final String GET_ONE_STMT = 
			"SELECT hou_id, hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee,hou_address,lan_id, hou_rent,hou_note FROM house where hou_id = ?";
	private static final String FIND_IMG_BY_HOUID = "SELECT hou_f_picture FROM house WHERE hou_id = ?";
	private static final String FIND_IMG_BY_HOUID2 = "SELECT hou_s_picture FROM house WHERE hou_id = ?";
	
	
	
	
	private static final String FIND_HOUID_HOURANT = "SELECT hou_id, hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee,hou_address,lan_id, hou_rent,hou_note FROM HOUSE WHERE LAN_ID=? AND HOU_PROPERTY=? ";
	private static final String FIND_HOUID_HOURANT2 = "SELECT hou_id, hou_name ,hou_type ,hou_size ,hou_property ,hou_parkspace ,hou_cook ,hou_managefee,hou_address,lan_id, hou_rent,hou_note FROM HOUSE WHERE HOU_PROPERTY=? AND hou_parkspace=? ";

	
	
	public HouseJDBCDAO() {
		super();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Override
	public void insert(HouseVO houseVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
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
			pstmt.setString(11, houseVO.getHou_note());

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
	public void update(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
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
//			pstmt.setString(10, houseVO.getHou_note());
			pstmt.setString(10, houseVO.getHou_id());
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
	public void delete(String hou_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
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
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
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
				houseVO.setHou_note(rs.getString("hou_note"));

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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
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
				houseVO.setHou_note(rs.getString("hou_note"));
				list.add(houseVO);
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

	@Override
	public byte[] getImage(String hou_id) {
		byte[] hou_f_picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con  = DriverManager.getConnection(MyData.URL,MyData.USER,MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_IMG_BY_HOUID);
			
			pstmt.setString(1, hou_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				hou_f_picture = rs.getBytes(1);
			}
			
			
		} catch (SQLException e) {
throw new RuntimeException("A database error occured."+e.getMessage());
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
		
		
		return hou_f_picture;
		
		
	}

	@Override
	public List<HouseVO> findByLanidLannotrant(String lan_id, String hou_property) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_HOUID_HOURANT);
			
			
			pstmt.setString(1, lan_id);
			pstmt.setString(2, hou_property);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				houseVO = new HouseVO();
				houseVO.setHou_id(rs.getString(1));
				houseVO.setHou_name(rs.getString(2));
				houseVO.setHou_type(rs.getString(3));
				houseVO.setHou_size(rs.getString(4));
				houseVO.setHou_property(rs.getString(5));
				houseVO.setHou_parkspace(rs.getString(6));
				houseVO.setHou_cook(rs.getString(7));
				houseVO.setHou_managefee(rs.getString(8));
				houseVO.setHou_address(rs.getString(9));
				houseVO.setLan_id(rs.getString(10));
				houseVO.setHou_rent(rs.getInt(11));
				houseVO.setHou_note(rs.getString(12));
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
	public byte[] getImage2(String hou_id) {
		byte[] hou_s_picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con  = DriverManager.getConnection(MyData.URL,MyData.USER,MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_IMG_BY_HOUID);
			
			pstmt.setString(1, hou_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				hou_s_picture = rs.getBytes(1);
			}
			
			
		} catch (SQLException e) {
throw new RuntimeException("A database error occured."+e.getMessage());
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
		
		
		return hou_s_picture;
		
		
	}





	@Override
	public List<HouseVO> getAllok(String hou_property, String hou_parkspace) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(MyData.URL, MyData.USER,MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_HOUID_HOURANT2);
			
			
			pstmt.setString(1, hou_property);
			pstmt.setString(2, hou_parkspace);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				houseVO = new HouseVO();
				houseVO.setHou_id(rs.getString(1));
				houseVO.setHou_name(rs.getString(2));
				houseVO.setHou_type(rs.getString(3));
				houseVO.setHou_size(rs.getString(4));
				houseVO.setHou_property(rs.getString(5));
				houseVO.setHou_parkspace(rs.getString(6));
				houseVO.setHou_cook(rs.getString(7));
				houseVO.setHou_managefee(rs.getString(8));
				houseVO.setHou_address(rs.getString(9));
				houseVO.setLan_id(rs.getString(10));
				houseVO.setHou_rent(rs.getInt(11));
				houseVO.setHou_note(rs.getString(12));
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
	}


