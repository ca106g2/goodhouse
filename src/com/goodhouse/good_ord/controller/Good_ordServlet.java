package com.goodhouse.good_ord.controller;

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

import com.goodhouse.good_ord.model.*;

public class Good_ordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Good_ordServlet() {
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
				Timestamp good_ord_dat = new Timestamp(System.currentTimeMillis());
				String good_ord_sta = req.getParameter("good_ord_sta");
				String good_ord_nam = req.getParameter("good_ord_nam");
				String good_ord_namReg = "^[(\u4e00-\u9fa5)]{2,4}$";
				if(good_ord_nam == null || good_ord_nam.trim().length() == 0) {
					errorMsgs.add("收件人名稱不可空白");
				} else if(!good_ord_nam.trim().matches(good_ord_namReg)) {
					errorMsgs.add("姓名需填入3個中文字");
				}
				
				String good_ord_add = req.getParameter("good_ord_add");
				if(good_ord_add == null || good_ord_add.trim().length() == 0) {
					errorMsgs.add("收件地址請勿空白");
				}
				// 地址在處裡
//				String good_ord_addReg = "^[(\u4e00-\u9fa5){}]$";
				
				Good_ordVO good_ordVO = new Good_ordVO();
				good_ordVO.setMem_id(mem_id);
				good_ordVO.setGood_ord_dat(good_ord_dat);
				good_ordVO.setGood_ord_sta(good_ord_sta);
				good_ordVO.setGood_ord_nam(good_ord_nam);
				good_ordVO.setGood_ord_add(good_ord_add);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("Good_ordVO", good_ordVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/addGood_ord.jsp");
					failureView.forward(req, res);
				}
				
				// 開始新增資料
				Good_ordService good_ordService = new Good_ordService();
				good_ordVO = good_ordService.addGood_ord(mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_add);
				
				//新增完成，準備轉交
				String url = "/back/good_ord/listAllGood_ord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/addGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				String good_ord_idReg = "^O\\d{9}$";
				if(good_ord_id == null || (good_ord_id.trim().length() == 0)) {
					errorMsgs.add("請輸入積分商品訂單編號");
				} else if(!good_ord_id.trim().matches(good_ord_idReg)) {
					errorMsgs.add("格式輸入錯誤，請輸入如(O000000001)");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Good_ordService good_ordSvc = new Good_ordService();
				Good_ordVO good_ordVO = good_ordSvc.getOneGood_ord(good_ord_id);
				
				if(good_ordVO == null) {
					errorMsgs.add("查無此資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("good_ordVO", good_ordVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord/listOneGood_ord.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				String mem_id = req.getParameter("mem_id");
				Timestamp good_ord_dat = Timestamp.valueOf(req.getParameter("good_ord_dat").trim());
				String good_ord_sta = req.getParameter("good_ord_sta");
				String good_ord_nam = req.getParameter("good_ord_nam");
				String good_ord_namReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,4}$";
				if(good_ord_nam == null || good_ord_nam.trim().length() == 0) {
					errorMsgs.add("收件人名稱不可空白");
				} else if(!good_ord_nam.trim().matches(good_ord_namReg)) {
					errorMsgs.add("姓名需填入3個中文字或是10個字內的英文字");
				}
				// 地址再處理
				String good_ord_add = req.getParameter("good_ord_add");
				if(good_ord_add == null || good_ord_add.trim().length() == 0) {
					errorMsgs.add("收件地址請勿空白");
				}
				
				Good_ordVO good_ordVO = new Good_ordVO();
				good_ordVO.setGood_ord_id(good_ord_id);
				good_ordVO.setMem_id(mem_id);
				good_ordVO.setGood_ord_dat(good_ord_dat);
				good_ordVO.setGood_ord_sta(good_ord_sta);
				good_ordVO.setGood_ord_nam(good_ord_nam);
				good_ordVO.setGood_ord_add(good_ord_add);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("good_ordVO", good_ordVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/updateGood_ord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 開始新增資料
				Good_ordService good_ordSvc = new Good_ordService();
				good_ordVO = good_ordSvc.updateGood_ord(good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_add);
				
				// 新增完成開始轉交
				req.setAttribute("good_ordVO", good_ordVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord/listOneGood_ord.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/updateGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ordService good_ordSvc = new Good_ordService();
				Good_ordVO good_ordVO = good_ordSvc.getOneGood_ord(good_ord_id);
				
				req.setAttribute("good_ordVO", good_ordVO);
				String url = "/back/good_ord/updateGood_ord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/listAllGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ordService good_ordSvc = new Good_ordService();
				good_ordSvc.deleteGood_ord(good_ord_id);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord/listAllGood_ord.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/listAllGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
