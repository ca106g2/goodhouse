package com.goodhouse.house.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class Readpicture extends HttpServlet{
	
//	Connection con;
//	DataSource ds = null;
//	public static final String photo1 = "SELECT hou_f_picture FROM HOUSE WHERE hou_id='";
//	public static final String photo2 = "SELECT hou_s_picture FROM HOUSE WHERE hou_id='";
//	public static final String photo3 = "SELECT hou_t_picture FROM HOUSE WHERE hou_id='";
//	public void init() throws ServletException{
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/goodhouse");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
//		doPost(req,res);
//	}
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
//		req.setCharacterEncoding("UTF-8");
//		res.setContentType("image/jpeg");
//		ServletOutputStream out = res.getOutputStream();
//		String pic = req.getParameter("${houVO.hou_id} + photo=1");
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			con = ds.getConnection();
//			stmt = con.createStatement();
//			String hou_id = req.getParameter("hou_id").trim();
//			if (Integer.parseInt(req.getParameter("photo")) == 1) {
//				rs = stmt.executeQuery(photo1 + hou_id + "'");
//			} else if (Integer.parseInt(req.getParameter("photo")) == 2) {
//				rs = stmt.executeQuery(photo2 + hou_id + "'");
//			} else if (Integer.parseInt(req.getParameter("photo")) == 3) {
//				rs = stmt.executeQuery(photo3 + hou_id + "'");
//			} else {
//				InputStream in = getServletContext().getResourceAsStream("/back/house/image/test.jpg");
//				byte[] buf = new byte[in.available()];
//				in.read(buf);
//				out.write(buf);
//				in.close();
//			}
//
//			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(1));
//				byte[] buf = new byte[4 * 1024];
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
//				in.close();
//			} else {
//				InputStream in = getServletContext().getResourceAsStream("/back/house/image/test.jpg");
//				byte[] buf = new byte[in.available()];
//				in.read(buf);
//				out.write(buf);
//				in.close();
//			}
//			rs.close();
//			stmt.close();
//		} catch (Exception e) {
//			InputStream in = getServletContext().getResourceAsStream("/back/house/image/test.jpg");
//			byte[] buf = new byte[in.available()];
//			in.read(buf);
//			out.write(buf);
//			in.close();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//	public void destory() {
//		try {
//			if(con!=null) con.close();
//		}catch(SQLException e) {
//			System.out.println(e);
//		}
//	}
}
