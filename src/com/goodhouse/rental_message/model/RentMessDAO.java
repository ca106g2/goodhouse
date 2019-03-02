package com.goodhouse.rental_message.model;

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

import com.goodhouse.house_noappointment.model.HouNoAppVO;

public class RentMessDAO implements RentMessDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO rental_message (ren_mes_id,hou_id,mem_id,lan_id,ren_mes_time,ren_mes_request,ren_mes_response) VALUES ('RENM'||LPAD(to_char(SEQ_REN_MES_ID.nextval),6,'0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ren_mes_id,hou_id,mem_id,lan_id,to_char(ren_mes_time, 'yyyy-mm-dd') ren_mes_time, ren_mes_request, ren_mes_response FROM rental_message order by ren_mes_id";
	private static final String GET_ONE_STMT = "SELECT * FROM rental_message where ren_mes_id= ?";
	private static final String DELETE = "DELETE FROM rental_message where ren_mes_id = ?";
	private static final String UPDATE = "UPDATE rental_message set hou_id=?, mem_id=?, lan_id=?, ren_mes_time=?, ren_mes_request=?, ren_mes_response=? where ren_mes_id = ?";

	@Override
	public void insert(RentMessVO rentMessVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rentMessVO.getHou_id());
			pstmt.setString(2, rentMessVO.getMem_id());
			pstmt.setString(3, rentMessVO.getLan_id());
			pstmt.setDate(4, rentMessVO.getRen_mes_time());
			pstmt.setString(5, rentMessVO.getRen_mes_request());
			pstmt.setString(6, rentMessVO.getRen_mes_response());

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
	public void update(RentMessVO rentMessVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rentMessVO.getHou_id());
			pstmt.setString(2, rentMessVO.getMem_id());
			pstmt.setString(3, rentMessVO.getLan_id());
			pstmt.setDate(4, rentMessVO.getRen_mes_time());
			pstmt.setString(5, rentMessVO.getRen_mes_request());
			pstmt.setString(6, rentMessVO.getRen_mes_response());
			pstmt.setString(7, rentMessVO.getRen_mes_id());
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
	public void delete(String ren_mes_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ren_mes_id);

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
	public RentMessVO findByPrimaryKey(String ren_mes_id) {
		RentMessVO rentMessVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ren_mes_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				rentMessVO = new RentMessVO();
				rentMessVO.setRen_mes_id(rs.getString("ren_mes_id"));
				rentMessVO.setHou_id(rs.getString("hou_id"));
				rentMessVO.setMem_id(rs.getString("mem_id"));
				rentMessVO.setLan_id(rs.getString("lan_id"));
				rentMessVO.setRen_mes_time(rs.getDate("ren_mes_time"));
				rentMessVO.setRen_mes_request(rs.getString("ren_mes_request"));
				rentMessVO.setRen_mes_response(rs.getString("ren_mes_response"));

			}

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

		return rentMessVO;

	}

	@Override
	public List<RentMessVO> getAll() {
		
		List<RentMessVO> list = new ArrayList<RentMessVO>();
		RentMessVO rentMessVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rentMessVO = new RentMessVO();
				rentMessVO.setRen_mes_id(rs.getString("ren_mes_id"));
				rentMessVO.setHou_id(rs.getString("hou_id"));
				rentMessVO.setMem_id(rs.getString("mem_id"));
				rentMessVO.setLan_id(rs.getString("lan_id"));
				rentMessVO.setRen_mes_time(rs.getDate("ren_mes_time"));
				rentMessVO.setRen_mes_request(rs.getString("Ren_mes_request"));
				rentMessVO.setRen_mes_response(rs.getString("Ren_mes_response"));
				list.add(rentMessVO);
			}

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

		return list;

	}

}
//git上傳註解用無意義