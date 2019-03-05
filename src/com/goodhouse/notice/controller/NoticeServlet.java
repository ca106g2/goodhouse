package com.goodhouse.notice.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.notice.model.*;

public class NoticeServlet extends HttpServlet {

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
				String str = req.getParameter("notice_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("請輸入通知事項編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String notice_id = null;
				try {
					notice_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("通知事項編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				NoticeService  noticeSvc = new NoticeService();
				NoticeVO noticeVO = noticeSvc.getOneNotice(notice_id);
				if (noticeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("noticeVO", noticeVO); // 資料庫取出的NoticeVO物件,存入req
				String url = "/back/notice/listOneNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRentMess.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllNotice.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String notice_id = new String(req.getParameter("notice_id"));
				/***************************2.開始查詢資料****************************************/
				NoticeService noticeSvc = new NoticeService();
				NoticeVO noticeVO = noticeSvc.getOneNotice(notice_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("noticeVO", noticeVO);         // 資料庫取出的NoticeVO物件,存入req
				String url = "/back/notice/update_notice_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_notice_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/listAllNotice.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {  // 來自update_notice_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String notice_id = new String(req.getParameter("notice_id").trim());
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				String notice_message = req.getParameter("notice_message");
				if (notice_message == null || notice_message.trim().length() == 0) {
					errorMsgs.add("通知內容: 請勿空白");
				}
				
				String notice_status = req.getParameter("notice_status");
				String notice_statusReg = "^[N][0-9]{1}$";
				if (notice_status == null || notice_status.trim().length() == 0) {
					errorMsgs.add("通知狀態: 請勿空白");
				}else if(!notice_status.trim().matches(notice_statusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("通知狀態: 只能是英文字母N開頭和數字 , 且總長度必需是2");
	            }

				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setNotice_id(notice_id);
				noticeVO.setMem_id(mem_id);
				noticeVO.setNotice_message(notice_message);
				noticeVO.setNotice_status(notice_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noticeVO", noticeVO); // 含有輸入格式錯誤的noticeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/update_notice_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				NoticeService noticeSvc = new NoticeService();
				noticeVO = noticeSvc.updateNotice(notice_id, mem_id, notice_message, notice_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("noticeVO", noticeVO); // 資料庫update成功後,正確的的noticeVO物件,存入req
				String url = "/back/notice/listOneNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneNotice.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/update_notice_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addNotice.jsp的請求  
			
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
				
				
				String notice_message = req.getParameter("notice_message");
				if (notice_message == null || notice_message.trim().length() == 0) {
					errorMsgs.add("通知內容: 請勿空白");
				}
				
				String notice_status = req.getParameter("notice_status");
				String notice_statusReg = "^[N][0-9]{1}$";
				if (notice_status == null || notice_status.trim().length() == 0) {
					errorMsgs.add("通知狀態: 請勿空白");
				}else if(!notice_status.trim().matches(notice_statusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("通知狀態: 只能是英文字母N開頭和數字 , 且總長度必需是2");
	            }
				
				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setMem_id(mem_id);
				noticeVO.setNotice_message(notice_message);
				noticeVO.setNotice_status(notice_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noticeVO", noticeVO); // 含有輸入格式錯誤的noticeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/addNotice.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				NoticeService noticeSvc = new NoticeService();
				noticeVO = noticeSvc.addNotice(mem_id, notice_message, notice_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/notice/listAllNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNotice.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/addNotice.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllNotice.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String notice_id = new String(req.getParameter("notice_id"));
				
				/***************************2.開始刪除資料***************************************/
				NoticeService noticeSvc = new NoticeService();
				noticeSvc.deleteNotice(notice_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/notice/listAllNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/listAllNotice.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
//git上傳註解用無意義