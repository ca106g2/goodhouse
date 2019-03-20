package com.goodhouse.favorite_good.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.favorite_good.model.*;

public class Favorite_goodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Favorite_goodServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mem_id = req.getParameter("mem_id");
				String good_id = req.getParameter("good_id");
				
				Favorite_goodService favorite_goodSvc = new Favorite_goodService();
				Favorite_goodVO favorite_goodVO = favorite_goodSvc.addFavorite_good(mem_id, good_id);
				
//				RequestDispatcher successView = req.getRequestDispatcher("/front/pointgoods/listAllPointgoods.jsp");
//				successView.forward(req, res);
				// 一定要加，不然會出錯
				PrintWriter out = res.getWriter();
				out.println("{}");
				out.close();
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pointgoods/listAllPointgoods.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mem_id = req.getParameter("mem_id");
				String good_id = req.getParameter("good_id");
				
				Favorite_goodService favorite_goodSvc = new Favorite_goodService();
				favorite_goodSvc.deleteFavorite_good(mem_id, good_id);
				
//				RequestDispatcher successView = req.getRequestDispatcher("/back/favorite_good/listAllFavorite_good.jsp");
//				successView.forward(req, res);
				// 一定要加，不然會出錯
				PrintWriter out = res.getWriter();
				out.println("{}");
				out.close();
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pointgoods/listAllPointgoods.jsp");
				failureView.forward(req, res);
			}
		}
		if("getPart_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^M\\d{9}$";
				if(mem_id == null || (mem_id.trim().length() == 0)) {
					errorMsgs.add("請輸入會員編號");
				} else if(!mem_id.matches(mem_idReg)) {
					errorMsgs.add("格式輸入錯誤，請輸入(M000000001)");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/favorite_good/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Favorite_goodService favorite_goodSvc = new Favorite_goodService();
				List<Favorite_goodVO> list = favorite_goodSvc.getPart(mem_id);
				
				if(list.isEmpty()) {
					errorMsgs.add("查無此資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/favorite_good/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back/favorite_good/listPartFavorite_good.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("查無此資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/favorite_good/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
