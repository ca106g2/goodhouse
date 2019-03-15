package com.goodhouse.permission.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.goodhouse.permission.model.PerService;
import com.goodhouse.permission.model.PerVO;



	
	public class PermissionServlet extends HttpServlet{
		
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
		

	
	if("insert".equals(action)) {
		
		List<String>errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs",errorMsgs);
	
	
	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	try {
			String per_name = req.getParameter("per_name");
			String per_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (per_name == null || per_name.trim().length() == 0) {
				errorMsgs.add("權限名稱: 請勿空白");
			} else if(!per_name.trim().matches(per_nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
					
			
			PerVO perVO = new PerVO();
			perVO.setPer_name(per_name);
			
			
					
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("perVO", perVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/permission/addPer.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
			PerService perSvc = new PerService();
			perVO = perSvc.addPer(per_name);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back/permission/listAllPer.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
			/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back/permission/addPer.jsp");
		failureView.forward(req, res);
	}

	}
	//==================================	
	if("update".equals(action)) {
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs );
		
		
		
		try {
			
			String per_id = req.getParameter("per_id").trim();
			
			String per_name = req.getParameter("per_name");
			String per_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (per_name == null || per_name.trim().length() == 0) {
				errorMsgs.add("權限名稱: 請勿空白");
			} else if(!per_name.trim().matches(per_nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			
		
			
			
			

			PerVO perVO = new PerVO();
			
			perVO.setPer_id(per_id);
			perVO.setPer_name(per_name);
			
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("perVO", perVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("back/permission/update_per_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			PerService perSvc = new PerService();
			perVO = perSvc.updatePer( per_id, per_name);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("perVO", perVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back/permission/listOnePer.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back/permission/update_per_input.jsp");
			failureView.forward(req, res);
		}
	}
	
	
//	==================================
		if("getOne_For_Display".equals(action)) {
		
		List<String>errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs",errorMsgs);
	
	
	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	try {
			String per_id = req.getParameter("per_id");
			if (per_id  == null || (per_id.trim()).length() == 0) {
				errorMsgs.add("請輸入權限編號");
			} 
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/permission/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			
			
			/***************************2.開始新增資料***************************************/
			PerService perSvc = new PerService();
			PerVO  perVO = perSvc.getOnePer(per_id);
			if(per_id == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/permission/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("perVO",perVO);
			String url = "/back/permission/listOnePer.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
			/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
			errorMsgs.add("查不到你要的東西:");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back/permission/select_page.jsp");
		failureView.forward(req, res);
		}
	}	
//		System.out.println("=======================================");	
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPer.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String per_id = req.getParameter("per_id");
				
				
				/***************************2.開始查詢資料****************************************/
				PerService perSvc = new PerService();
				PerVO perVO = perSvc.getOnePer(per_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("perVO", perVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back/permission/update_per_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_per_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/permission/listAllPer.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//===================================================================
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String per_id = new String(req.getParameter("per_id"));
				
				/***************************2.開始刪除資料***************************************/
				
				PerService perSvc = new PerService();
				perSvc.deletePer(per_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/permission/listAllPer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/permission/listAllPer.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
}
}
