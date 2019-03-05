package com.goodhouse.rental_message.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.rental_message.model.*;

public class RentMessServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
	
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);//左邊是key 右邊是物件

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ren_mes_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("請輸入聊天訊息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ren_mes_id = null;
				try {
					ren_mes_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("聊天訊息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentMessService rentMessSvc = new RentMessService();
				RentMessVO rentMessVO = rentMessSvc.getOneRentMess(ren_mes_id);
				if (rentMessVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentMessVO", rentMessVO); // 資料庫取出的rentMessVO物件,存入req
				String url = "/back/rentMess/listOneRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRentMess.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllRentMess.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String ren_mes_id = new String(req.getParameter("ren_mes_id"));
				
				/***************************2.開始查詢資料****************************************/
				RentMessService rentMessSvc = new RentMessService();
				RentMessVO rentMessVO = rentMessSvc.getOneRentMess(ren_mes_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rentMessVO", rentMessVO);         // 資料庫取出的rentMessVO物件,存入req
				String url = "/back/rentMess/update_rentMess_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rentMess_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/listAllRentMess.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_rentMess_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ren_mes_id = new String(req.getParameter("ren_mes_id").trim());
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母HOU開頭和數字 , 且總長度必需是10");
	            }
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母M開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				java.sql.Date ren_mes_time = null;
				try {
					ren_mes_time = java.sql.Date.valueOf(req.getParameter("ren_mes_time").trim());
				} catch (IllegalArgumentException e) {
					ren_mes_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String ren_mes_request = req.getParameter("ren_mes_request").trim();
				if (ren_mes_request == null || ren_mes_request.trim().length() == 0) {
					errorMsgs.add("提問訊息請勿空白");
				}
				
				String ren_mes_response = req.getParameter("ren_mes_response").trim();
				if (ren_mes_response == null || ren_mes_response.trim().length() == 0) {
					errorMsgs.add("回覆訊息請勿空白");
				}

				RentMessVO rentMessVO = new RentMessVO();
				rentMessVO.setRen_mes_id(ren_mes_id);
				rentMessVO.setHou_id(hou_id);
				rentMessVO.setMem_id(mem_id);
				rentMessVO.setLan_id(lan_id);
				rentMessVO.setRen_mes_time(ren_mes_time);
				rentMessVO.setRen_mes_request(ren_mes_request);
				rentMessVO.setRen_mes_response(ren_mes_response);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentMessVO", rentMessVO); // 含有輸入格式錯誤的rentMessVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/update_rentMess_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RentMessService rentMessSvc = new RentMessService();
				rentMessVO = rentMessSvc.updateRentMess(ren_mes_id, hou_id, mem_id, lan_id, ren_mes_time, ren_mes_request, ren_mes_response);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentMessVO", rentMessVO); // 資料庫update成功後,正確的的rentMessVO物件,存入req
				String url = "/back/rentMess/listOneRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRentMess.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/update_rentMess_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addRentMess.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母HOU開頭和數字 , 且總長度必需是10");
	            }
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母M開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				java.sql.Date ren_mes_time = null;
				try {
					ren_mes_time = java.sql.Date.valueOf(req.getParameter("ren_mes_time").trim());
				} catch (IllegalArgumentException e) {
					ren_mes_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String ren_mes_request = req.getParameter("ren_mes_request").trim();
				if (ren_mes_request == null || ren_mes_request.trim().length() == 0) {
					errorMsgs.add("提問訊息請勿空白");
				}
				
				String ren_mes_response = req.getParameter("ren_mes_response").trim();
				if (ren_mes_response == null || ren_mes_response.trim().length() == 0) {
					errorMsgs.add("回覆訊息請勿空白");
				}
			
				
//				String deptno = new String(req.getParameter("deptno").trim());

				RentMessVO rentMessVO = new RentMessVO();
				rentMessVO.setHou_id(hou_id);
				rentMessVO.setMem_id(mem_id);
				rentMessVO.setLan_id(lan_id);
				rentMessVO.setRen_mes_time(ren_mes_time);
				rentMessVO.setRen_mes_request(ren_mes_request);
				rentMessVO.setRen_mes_response(ren_mes_response);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentMessVO", rentMessVO); // 含有輸入格式錯誤的rentMessVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/addRentMess.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RentMessService rentMessSvc = new RentMessService();
				rentMessVO = rentMessSvc.addRentMess(hou_id, mem_id, lan_id, ren_mes_time, ren_mes_request, ren_mes_response);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/rentMess/listAllRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllRentMess.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/addRentMess.jsp");
				failureView.forward(req, res);
			}
		}
//		
//		
		if ("delete".equals(action)) { // 來自listAllRentMess.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ren_mes_id = new String(req.getParameter("ren_mes_id"));
				
				/***************************2.開始刪除資料***************************************/
				RentMessService rentMessSvc = new RentMessService();
				rentMessSvc.deleteRentMess(ren_mes_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/rentMess/listAllRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/listAllRentMess.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
//git上傳註解用無意義