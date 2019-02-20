package com.goodhouse.member.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemDAO implements MemDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx= new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/goodhouse");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT =
			"INSERT INTO MEMBER VALUES ( 'M'||LPAD(to_char(MEM_SEQ.NEXTVAL),9,'0'), ?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL =
			"SELECT * FROM MEMBER order by MEM_ID ";
	private static final String GET_ONE=
			"SELECT * FROM MEMBER where MEM_ID=?";
	private static final String DELETE=
			"DELETE FROM MEMBER where MEM_ID=?";
	private static final String UPDATE=
			"UPDATE MEMBER set MEM_NAME=?,MEM_BIRTHDAY=?,MEM_PASSWORD=?,MEM_ADDRESS=?,MEM_ZIPCODE=?,MEM_TELEPHONE=?,MEM_PHONE=?,MEM_EMAIL =?,MEM_STATUS=?,MEM_PICTURE=?,GOOD_TOTAL=?,MEM_SEX =? where MEM_ID=?";
	
	@Override
	public void insert(MemVO memVo) {
		// TODO Auto-generated method stub
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setDate(2, memVo.getMem_birthday());
			pstmt.setString(3,memVo.getMem_password());
			pstmt.setString(4,memVo.getMem_address());
			pstmt.setString(5,memVo.getMem_zipcode());
			pstmt.setInt(6,memVo.getMem_telephone());
			pstmt.setInt(7,memVo.getMem_phone());
			pstmt.setString(8,memVo.getMem_email());
			pstmt.setString(9,memVo.getMem_status());
			pstmt.setBytes(10,memVo.getMem_picture());
			pstmt.setInt(11,memVo.getGood_total());
			pstmt.setString(12,memVo.getMem_sex());
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."
					+se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con !=null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(MemVO memVo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt= con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setDate(2, memVo.getMem_birthday());
			pstmt.setString(3,memVo.getMem_password());
			pstmt.setString(4,memVo.getMem_address());
			pstmt.setString(5,memVo.getMem_zipcode());
			pstmt.setInt(6,memVo.getMem_telephone());
			pstmt.setInt(7,memVo.getMem_phone());
			pstmt.setString(8,memVo.getMem_email());
			pstmt.setString(9,memVo.getMem_status());
			pstmt.setBytes(10,memVo.getMem_picture());
			pstmt.setInt(11,memVo.getGood_total());
			pstmt.setString(12,memVo.getMem_sex());
			pstmt.setString(13,memVo.getMem_id());
			
			pstmt.executeUpdate();
			
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." +se.getMessage());
			
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con !=null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String MEM_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con= ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, MEM_ID);
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured"+ se.getMessage());
			
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MemVO findByPrimaryKey(String MEM_ID) {
		// TODO Auto-generated method stub
		MemVO memVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setString(1,MEM_ID);
			
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("MEM_ID"));
				memVO.setMem_name(rs.getString("MEM_NAME"));
				memVO.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
				memVO.setMem_password(rs.getString("MEM_PASSWORD"));
				memVO.setMem_address(rs.getString("MEM_ADDRESS"));
				memVO.setMem_zipcode(rs.getString("MEM_ZIPCODE"));
				memVO.setMem_telephone(rs.getInt("MEM_TELEPHONE"));
				memVO.setMem_phone(rs.getInt("MEM_PHONE"));
				memVO.setMem_email(rs.getString("MEM_EMAIL"));
				memVO.setMem_status(rs.getString("MEM_STATUS"));
				memVO.setMem_picture(rs.getBytes("MEM_PICTURE"));
				memVO.setGood_total(rs.getInt("GOOD_TOTAL"));
				memVO.setMem_sex(rs.getString("MEM_SEX"));
				}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
			
		}finally {
			try {
				rs.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList();
		MemVO memVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
			memVO = new MemVO();
			memVO.setMem_id(rs.getString("MEM_ID"));
			memVO.setMem_name(rs.getString("MEM_NAME"));
			memVO.setMem_birthday(rs.getDate("MEM_BIRTHDAY"));
			memVO.setMem_password(rs.getString("MEM_PASSWORD"));
			memVO.setMem_address(rs.getString("MEM_ADDRESS"));
			memVO.setMem_zipcode(rs.getString("MEM_ZIPCODE"));
			memVO.setMem_telephone(rs.getInt("MEM_TELEPHONE"));
			memVO.setMem_phone(rs.getInt("MEM_PHONE"));
			memVO.setMem_email(rs.getString("MEM_EMAIL"));
			memVO.setMem_status(rs.getString("MEM_STATUS"));
			memVO.setMem_picture(rs.getBytes("MEM_PICTURE"));
			memVO.setGood_total(rs.getInt("GOOD_TOTAL"));
			memVO.setMem_sex(rs.getString("MEM_SEX"));
			list.add(memVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured "+se.getMessage());
		}finally {
			try {
				rs.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
