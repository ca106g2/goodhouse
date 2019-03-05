package com.goodhouse.appointment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.appointment.model.*;

public class AppointServlet extends HttpServlet {

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
				String str = req.getParameter("appoint_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("請輸入預約行程表編號");
				}
				// Send the use back to the form, if there were errors //git上傳註解用無意義
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String appoint_id = null;
				try {
					appoint_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("預約行程表編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AppointService appointSvc = new AppointService();
				AppointVO appointVO = appointSvc.getOneAppoint(appoint_id);
				if (appointVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("appointVO", appointVO); // 資料庫取出的AppointVO物件,存入req
				String url = "/back/appoint/listOneAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRentMess.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAppoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String appoint_id = new String(req.getParameter("appoint_id"));
				/***************************2.開始查詢資料****************************************/
				AppointService appointSvc = new AppointService();
				AppointVO appointVO = appointSvc.getOneAppoint(appoint_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("appointVO", appointVO);         // 資料庫取出的AppointVO物件,存入req
				String url = "/back/appoint/update_appoint_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_appoint_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/listAllAppoint.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // // 來自update_appoint_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String appoint_id = new String(req.getParameter("appoint_id").trim());
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母HOU開頭和數字 , 且總長度必需是10");
	            }
				
				
				
				String hou_app_time = req.getParameter("hou_app_time");
				String hou_app_timeReg = "^[A][0-9]{1}$";
				if (hou_app_time == null || hou_app_time.trim().length() == 0) {
					errorMsgs.add("預約時段: 請勿空白");
				} else if(!hou_app_time.trim().matches(hou_app_timeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約時段: 只能是英文字母A開頭和數字 , 且總長度必需是2");
	            }
				
				java.sql.Date hou_app_date = null;
				try {
					hou_app_date = java.sql.Date.valueOf(req.getParameter("hou_app_date").trim());
				} catch (IllegalArgumentException e) {
					hou_app_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String app_status = req.getParameter("app_status");
				String app_statusReg = "^[A][0-9]{1}$";
				if (app_status == null || app_status.trim().length() == 0) {
					errorMsgs.add("預約狀態: 請勿空白");
				} else if(!app_status.trim().matches(app_statusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約狀態: 只能是英文字母A開頭和數字 , 且總長度必需是2");
	            }
				
				String app_remind = req.getParameter("app_remind");
				String app_remindReg = "^[A][0-9]{1}$";
				if (app_remind == null || app_remind.trim().length() == 0) {
					errorMsgs.add("預約提醒: 請勿空白");
				} else if(!app_remind.trim().matches(app_remindReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約提醒: 只能是英文字母A開頭和數字 , 且總長度必需是2");
	            }
				

				AppointVO appointVO = new AppointVO();
				appointVO.setAppoint_id(appoint_id);
				appointVO.setMem_id(mem_id);
				appointVO.setLan_id(lan_id);
				appointVO.setHou_id(hou_id);
				appointVO.setHou_app_time(hou_app_time);
				appointVO.setHou_app_date(hou_app_date);
				appointVO.setApp_status(app_status);
				appointVO.setApp_remind(app_remind);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointVO", appointVO); // 含有輸入格式錯誤的AppointVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/update_appoint_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AppointService appointSvc = new AppointService();
				appointVO = appointSvc.updateAppoint(appoint_id, mem_id, lan_id, hou_id, hou_app_time, hou_app_date, app_status, app_remind);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("appointVO", appointVO); // 資料庫update成功後,正確的的appointVO物件,存入req
				String url = "/back/appoint/listOneAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAppoint.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/update_appoint_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addAppoint.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }

				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母HOU開頭和數字 , 且總長度必需是10");
	            }
				
				String hou_app_time = req.getParameter("hou_app_time");
				String hou_app_timeReg = "^[A][0-9]{1}$";
				if (hou_app_time == null || hou_app_time.trim().length() == 0) {
					errorMsgs.add("預約時段: 請勿空白");
				} else if(!hou_app_time.trim().matches(hou_app_timeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約時段: 只能是英文字母A開頭和數字 , 且總長度必需是2");
	            }
				
				java.sql.Date hou_app_date = null;
				try {
					hou_app_date = java.sql.Date.valueOf(req.getParameter("hou_app_date").trim());
				} catch (IllegalArgumentException e) {
					hou_app_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入預約日期!");
				}
				
				String app_status = req.getParameter("app_status");
				String app_statusReg = "^[A][0-9]{1}$";
				if (app_status == null || app_status.trim().length() == 0) {
					errorMsgs.add("預約狀態: 請勿空白");
				} else if(!app_status.trim().matches(app_statusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約狀態: 只能是英文字母A開頭和數字 , 且總長度必需是2");
	            }
				
				String app_remind = req.getParameter("app_remind");
				String app_remindReg = "^[A][0-9]{1}$";
				if (app_remind == null || app_remind.trim().length() == 0) {
					errorMsgs.add("預約提醒: 請勿空白");
				} else if(!app_remind.trim().matches(app_remindReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預約提醒: 只能是英文字母A開頭和數字 , 且總長度必需是2");
	            }
				
				AppointVO appointVO = new AppointVO();
				appointVO.setMem_id(mem_id);
				appointVO.setLan_id(lan_id);
				appointVO.setHou_id(hou_id);
				appointVO.setHou_app_time(hou_app_time);
				appointVO.setHou_app_date(hou_app_date);
				appointVO.setApp_status(app_status);
				appointVO.setApp_remind(app_remind);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointVO", appointVO); // 含有輸入格式錯誤的AppointVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/addAppoint.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AppointService appointSvc = new AppointService();
				appointVO = appointSvc.addAppoint(mem_id, lan_id, hou_id, hou_app_time, hou_app_date, app_status, app_remind);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/appoint/listAllAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAppoint.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/addAppoint.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllAppoint.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String appoint_id = new String(req.getParameter("appoint_id"));
				
				/***************************2.開始查詢資料***************************************/
				AppointService appointSvc = new AppointService();
				appointSvc.deleteAppoint(appoint_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				String url = "/back/appoint/listAllAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/listAllAppoint.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
