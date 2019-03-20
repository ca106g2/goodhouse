package com.goodhouse.good_ord_list.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.good_ord_list.model.*;

public class Good_ord_listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Good_ord_listServlet() {
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
				String good_id = req.getParameter("good_id");
				String good_ord_id = req.getParameter("good_ord_id");
				String good_ord_amoStr = req.getParameter("good_ord_amo").trim();
				String good_ord_amoReg = "^[0-9]{1,10}$";
				Integer good_ord_amo = null;
				if(good_ord_amoStr == null || good_ord_amoStr.trim().length() == 0) {
					errorMsgs.add("數量不可為空白");
					good_ord_amo = 0;
				} else if (!good_ord_amoStr.trim().matches(good_ord_amoReg)) {
					errorMsgs.add("數量格式錯誤，請重新輸入");
					good_ord_amo = 0;
				} else {
					good_ord_amo = Integer.parseInt(good_ord_amoStr);
				}
				
				Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
				good_ord_listVO.setGood_id(good_id);
				good_ord_listVO.setGood_ord_id(good_ord_id);
				good_ord_listVO.setGood_ord_amo(good_ord_amo);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("good_ord_listVO", good_ord_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord_list/addGood_ord_list.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Good_ord_listService good_ord_listSvc = new Good_ord_listService();
				good_ord_listVO = good_ord_listSvc.addGood_ord_list(good_id, good_ord_id, good_ord_amo);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord_list/listAllGood_ord_list.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord_list/addGood_ord_list.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_id = req.getParameter("good_id");
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ord_listService good_ord_listSvc = new Good_ord_listService();
				Good_ord_listVO good_ord_listVO = good_ord_listSvc.getOneGood_ord_list(good_id, good_ord_id);
				
				req.setAttribute("good_ord_listVO", good_ord_listVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord_list/updateGood_ord_list.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord_list/listAllGood_ord_list.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_id = req.getParameter("good_id");
				String good_ord_id = req.getParameter("good_ord_id");
				String good_ord_amoStr = req.getParameter("good_ord_amo");
				String good_ord_amoReg = "^[0-9]{1,10}$";
				Integer good_ord_amo = null;
				if(good_ord_amoStr == null || good_ord_amoStr.trim().length() == 0) {
					errorMsgs.add("數量不可為空白");
					good_ord_amo = 0;
				} else if (!good_ord_amoStr.trim().matches(good_ord_amoReg)) {
					errorMsgs.add("數量格式錯誤，請重新輸入");
					good_ord_amo = 0;
				} else {
					good_ord_amo = Integer.parseInt(good_ord_amoStr);
				}
				
				
				Good_ord_listVO good_ord_listVO = new Good_ord_listVO();
				good_ord_listVO.setGood_id(good_id);
				good_ord_listVO.setGood_ord_id(good_ord_id);
				good_ord_listVO.setGood_ord_amo(good_ord_amo);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("good_ord_listVO", good_ord_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord_list/updateGood_ord_list.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Good_ord_listService good_ord_listSvc = new Good_ord_listService();
				good_ord_listVO = good_ord_listSvc.updateGood_ord_list(good_id, good_ord_id, good_ord_amo);
				
				req.setAttribute("good_ord_listVO", good_ord_listVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord_list/listOneGood_ord_list.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord_list/updateGood_ord_list.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_id = req.getParameter("good_id");
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ord_listService good_ord_listSvc = new Good_ord_listService();
				good_ord_listSvc.deleteGood_ord_list(good_id, good_ord_id);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord_list/listAllGood_ord_list.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord_list/listAllGood_ord_list.jsp");
				failureView.forward(req, res);
			}
		}
		if("getPart_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ord_listService good_ord_listSvc = new Good_ord_listService();
				List<Good_ord_listVO> list = good_ord_listSvc.getPart(good_ord_id);
								
//				HttpSession session = req.getSession();
				req.setAttribute("list", list);
				req.setAttribute("good_ord_id", good_ord_id);
				RequestDispatcher successView = req.getRequestDispatcher("/front/pointgoods/good_order_check.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("查無此資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pointgoods/good_order_check.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
