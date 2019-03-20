package com.goodhouse.good_record.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.good_record.model.*;

public class Good_recordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Good_recordServlet() {
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
				String good_rec_dec = req.getParameter("good_rec_dec");
				Integer good_rec_poi = new Integer(req.getParameter("good_rec_poi"));
				Timestamp good_rec_dat = new Timestamp(System.currentTimeMillis());
				
				Good_recordService good_recordSvc = new Good_recordService();
				Good_recordVO good_recordVO = good_recordSvc.addGood_record(mem_id, good_rec_dec, good_rec_poi, good_rec_dat);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_record/listAllGood_record.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_record/addGood_record.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_rec_id = req.getParameter("good_rec_id");
				
				Good_recordService good_recordSvc = new Good_recordService();
				good_recordSvc.deleteGood_record(good_rec_id);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_record/listAllGood_record.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_record/listAllGood_record.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_record/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Good_recordService good_recordSvc = new Good_recordService();
				List<Good_recordVO> list = good_recordSvc.getPart(mem_id);
				
				if(list.isEmpty()) {
					errorMsgs.add("查無此資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_record/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_record/listPartGood_record.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("查無此資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_record/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
