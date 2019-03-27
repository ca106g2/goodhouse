package android.com.goodhouse.member.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.goodhouse.maim.MyData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemDAO implements MemDAO_interface {

	private static DataSource ds = null;

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	private static final String GET_ALL = "SELECT * FROM MEMBER order by MEM_ID ";
	private static final String GET_ONE = "SELECT mem_id , mem_name ,MEM_BIRTHDAY ,MEM_PASSWORD,MEM_ADDRESS,MEM_ZIPCODE,MEM_TELEPHONE,MEM_PHONE,MEM_EMAIL,MEM_STATUS,GOOD_TOTAL,MEM_SEX FROM MEMBER WHERE MEM_ID=?";
	private static final String DELETE = "DELETE FROM MEMBER where MEM_ID=?";

	private static final String INSERT = "INSERT INTO MEMBER(MEM_ID,MEM_NAME,MEM_BIRTHDAY,MEM_PASSWORD,MEM_ADDRESS,MEM_TELEPHONE,MEM_PHONE,MEM_EMAIL,MEM_STATUS,GOOD_TOTAL,MEM_SEX) VALUES ('M'||LPAD(to_char(MEM_SEQ.NEXTVAL), 9, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MEMBER set MEM_NAME=?,MEM_BIRTHDAY=?,MEM_PASSWORD=?,MEM_ADDRESS=?,MEM_ZIPCODE=?,MEM_TELEPHONE=?,MEM_PHONE=?,MEM_STATUS=?,MEM_SEX =? where MEM_EMAIL=?";
	private static final String FIND_BY_EMAIL_PASSWORD = "SELECT * FROM MEMBER WHERE MEM_EMAIL=? AND MEM_PASSWORD=?";

	private static final String FIND_BY_EMAIL = "SELECT mem_id , mem_name ,MEM_BIRTHDAY ,MEM_PASSWORD,MEM_ADDRESS,MEM_ZIPCODE,MEM_TELEPHONE,MEM_PHONE,MEM_EMAIL,MEM_STATUS,GOOD_TOTAL,MEM_SEX FROM MEMBER WHERE MEM_EMAIL=?";
	private static final String CHECK_EMAIL_EXIST = "SELECT MEM_EMAIL FROM MEMBER WHERE MEM_EMAIL=?";

	public MemDAO() {
		super();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemVO findByPrimaryKey(String mem_id) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString(1));
				memVO.setMem_name(rs.getString(2));
				memVO.setMem_birthday(rs.getDate(3));
				memVO.setMem_password(rs.getString(4));
				memVO.setMem_address(rs.getString(5));
				memVO.setMem_zipcode(rs.getString(6));
				memVO.setMem_telephone(rs.getInt(7));
				memVO.setMem_phone(rs.getInt(8));
				memVO.setMem_email(rs.getString(9));
				memVO.setMem_status(rs.getString(10));
				memVO.setGood_total(rs.getInt(11));
				memVO.setMem_sex(rs.getString(12));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());

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

		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_birthday(rs.getDate("mem_birthday"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_zipcode(rs.getString("mem_zipcode"));
				memVO.setMem_telephone(rs.getInt("mem_telephone"));
				memVO.setMem_phone(rs.getInt("mem_phone"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setGood_total(rs.getInt("good_total"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				list.add(memVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured " + se.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
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
	public boolean isMember(String mem_email, String mem_password) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isMember = false;

		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			ps = conn.prepareStatement(FIND_BY_EMAIL_PASSWORD);

			ps.setString(1, mem_email);
			ps.setString(2, mem_password);

			ResultSet rs = ps.executeQuery();
			isMember = rs.next();
			return isMember;

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
		return isMember;
	}

	@Override
	public boolean isUserIdExist(String mem_email) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;

		try {
			conn = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			ps = conn.prepareStatement(CHECK_EMAIL_EXIST);

			ps.setString(1, mem_email);
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
	public boolean insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insert = false;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			// pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setDate(2, memVO.getMem_birthday());
			pstmt.setString(3, memVO.getMem_password());
			pstmt.setString(4, memVO.getMem_address());

			pstmt.setInt(5, memVO.getMem_telephone());
			pstmt.setInt(6, memVO.getMem_phone());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_status());
			pstmt.setInt(9, memVO.getGood_total());
			pstmt.setString(10, memVO.getMem_sex());

			int count = pstmt.executeUpdate();
			insert = count > 0 ? true : false;

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
		return insert;
	}

	@Override
	public boolean update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setDate(2, memVO.getMem_birthday());
			pstmt.setString(3, memVO.getMem_password());
			pstmt.setString(4, memVO.getMem_address());
			pstmt.setString(5, memVO.getMem_zipcode());
			pstmt.setInt(6, memVO.getMem_telephone());
			pstmt.setInt(7, memVO.getMem_phone());
			pstmt.setString(8, memVO.getMem_status());
			pstmt.setString(9, memVO.getMem_sex());
			pstmt.setString(10, memVO.getMem_email());

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
	public boolean delete(MemVO memVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemVO findByEmail(String mem_email) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_EMAIL);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString(1));
				memVO.setMem_name(rs.getString(2));
				memVO.setMem_birthday(rs.getDate(3));
				memVO.setMem_password(rs.getString(4));
				memVO.setMem_address(rs.getString(5));
				memVO.setMem_zipcode(rs.getString(6));
				memVO.setMem_telephone(rs.getInt(7));
				memVO.setMem_phone(rs.getInt(8));
				memVO.setMem_email(rs.getString(9));
				memVO.setMem_status(rs.getString(10));
				memVO.setGood_total(rs.getInt(11));
				memVO.setMem_sex(rs.getString(12));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());

		} finally {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
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

		return memVO;
	}

	@Override
	public List<MemVO> getALL() {
		// TODO Auto-generated method stub
		return null;
	}

}
