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
	
	
	
	Connection con;
	DataSource ds = null;
	public static final String photo1 = "SELECT hou_f_picture FROM HOUSE WHERE hou_id='";
	public static final String photo2 = "SELECT hou_s_picture FROM HOUSE WHERE hou_id='";
	public static final String photo3 = "SELECT hou_t_picture FROM HOUSE WHERE hou_id='";
	public void init() throws ServletException{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GOODHOUSE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		
	}
	public void destory() {
		try {
			if(con!=null) con.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
