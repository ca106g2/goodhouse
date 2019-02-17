package com.goodhouse.keyword.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KeyWordServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//
				String kw_keyword = req.getParameter("kw_keyword");
				if(kw_keyword == null || kw_keyword.trim().length() == 0) {
					errorMsgs.add("請輸入想要查詢的關鍵字");
				}
				Integer kw_count = new Integer(req.getParameter("kw_count").trim());
				kw_count++;
				
				
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/font/keyword/addKW.jsp");
				failureView.forward(req, res);
			}
			
			
			
			
		}
	}
}
