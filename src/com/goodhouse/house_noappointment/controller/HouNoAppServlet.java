package com.goodhouse.house_noappointment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.house_noappointment.model.*;

public class HouNoAppServlet extends HttpServlet {

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
			// send the ErrorPage view.//git上傳註解用無意義
			req.setAttribute("errorMsgs", errorMsgs);//左邊是key 右邊是物件

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("hou_noapp_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("請輸入房屋不可預約編號");
				}
				// Send the use front to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/houNoApp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String hou_noapp_id = null;
				try {
					hou_noapp_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("房屋不可預約編號格式不正確");
				}
				// Send the use front to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/houNoApp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				HouNoAppVO houNoAppVO = houNoAppSvc.getOneHouNoApp(hou_noapp_id);
				if (houNoAppVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use front to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/houNoApp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("houNoAppVO", houNoAppVO); // 資料庫取出的houNoAppVO物件,存入req
				String url = "/front/houNoApp/listOneHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRentMess.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/houNoApp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllHouNoApp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String hou_noapp_id = new String(req.getParameter("hou_noapp_id"));
				/***************************2.開始查詢資料****************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				HouNoAppVO houNoAppVO = houNoAppSvc.getOneHouNoApp(hou_noapp_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("houNoAppVO", houNoAppVO);         // 資料庫取出的houNoAppVO物件,存入req
				String url = "/front/houNoApp/update_houNoApp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rentMess_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/houNoApp/listAllHouNoApp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_houNoApp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String hou_noapp_id = new String(req.getParameter("hou_noapp_id").trim());
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母HOU開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				String hou_noapp_time = req.getParameter("hou_noapp_time");
				String hou_noapp_timeReg = "^[H][1-3]{1}$";
				if (hou_noapp_time == null || hou_noapp_time.trim().length() == 0) {
					errorMsgs.add("預約時段: 請勿空白");
				} else if(!hou_noapp_time.trim().matches(hou_noapp_timeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約時段: 只能是英文字母H開頭和數字範圍1~3 , 且總長度必需是2");
	            }
				
				java.sql.Date hou_noapp_date = null;
				try {
					hou_noapp_date = java.sql.Date.valueOf(req.getParameter("hou_noapp_date").trim());
				} catch (IllegalArgumentException e) {
					hou_noapp_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				HouNoAppVO houNoAppVO = new HouNoAppVO();
				houNoAppVO.setHou_noapp_id(hou_noapp_id);
				houNoAppVO.setHou_id(hou_id);
				houNoAppVO.setLan_id(lan_id);
				houNoAppVO.setHou_noapp_time(hou_noapp_time);
				houNoAppVO.setHou_noapp_date(hou_noapp_date);

				// Send the use front to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("houNoAppVO", houNoAppVO); // 含有輸入格式錯誤的houNoAppVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/houNoApp/update_houNoApp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				houNoAppVO = houNoAppSvc.updateHouNoApp(hou_noapp_id, hou_id, lan_id, hou_noapp_time, hou_noapp_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("houNoAppVO", houNoAppVO); // 資料庫update成功後,正確的的houNoAppVO物件,存入req
				String url = "/front/houNoApp/listOneHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneHouNoApp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/houNoApp/update_houNoApp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addHouNoApp.jsp的請求
			
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
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				
				String hou_noapp_time = req.getParameter("hou_noapp_time");
				String hou_noapp_timeReg = "^[H][1-3]{1}$";
				if (hou_noapp_time == null || hou_noapp_time.trim().length() == 0) {
					errorMsgs.add("預約時段: 請勿空白");
				} else if(!hou_noapp_time.trim().matches(hou_noapp_timeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約時段: 只能是英文字母H開頭和數字範圍1~3 , 且總長度必需是2");
	            }
				
				java.sql.Date hou_noapp_date = null;
				try {
					hou_noapp_date = java.sql.Date.valueOf(req.getParameter("hou_noapp_date").trim());
				} catch (IllegalArgumentException e) {
					hou_noapp_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				HouNoAppVO houNoAppVO = new HouNoAppVO();
				houNoAppVO.setHou_id(hou_id);
				houNoAppVO.setLan_id(lan_id);
				houNoAppVO.setHou_noapp_time(hou_noapp_time);
				houNoAppVO.setHou_noapp_date(hou_noapp_date);

				// Send the use front to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("houNoAppVO", houNoAppVO); // 含有輸入格式錯誤的houNoAppVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/houNoApp/addHouNoApp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				houNoAppVO = houNoAppSvc.addHouNoApp(hou_id, lan_id, hou_noapp_time, hou_noapp_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/front/houNoApp/listAllHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneHouNoApp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/houNoApp/addHouNoApp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllHouNoApp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String hou_noapp_id = new String(req.getParameter("hou_noapp_id"));
				
				/***************************2.開始刪除資料***************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				houNoAppSvc.deleteHouNoApp(hou_noapp_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front/houNoApp/listAllHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/houNoApp/listAllHouNoApp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
