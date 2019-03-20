package com.goodhouse.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.employee.model.*;


	
	public class EmployeeServlet extends HttpServlet{
		
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
			String emp_name = req.getParameter("emp_name");
			String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			Integer emp_phone = null;
			try {
				 emp_phone = new Integer(req.getParameter("emp_phone").trim());
			}catch(Exception e) {
				errorMsgs.add("請輸入電話");
			}
			
			
			String emp_account = req.getParameter("emp_account").trim();
			if(emp_account == null ||emp_account.trim().length()==0 ) {
				errorMsgs.add("帳號請勿空白");
			}
			String emp_password = req.getParameter("emp_password").trim();
			if(emp_password == null || emp_password.trim().length()==0) {
				errorMsgs.add("密碼不能空白");
			}
			String emp_status =  req.getParameter("emp_status").trim();
			
			
			
			EmpVO empVO = new EmpVO();
			empVO.setEmp_name(emp_name);
			empVO.setEmp_phone(emp_phone);
			empVO.setEmp_account(emp_account);
			empVO.setEmp_password(emp_password);
			empVO.setEmp_status(emp_status);
			
					
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
			EmpService empSvc = new EmpService();
			empVO = empSvc.addEmp(emp_name, emp_phone, emp_account, emp_password, emp_status);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back/employee/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
			/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back/employee/addEmp.jsp");
		failureView.forward(req, res);
	}

	}
	//==================================	
	if("update".equals(action)) {
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs );
		
		
		
		try {
			
			String emp_id = req.getParameter("emp_id").trim();
			
			String emp_name = req.getParameter("emp_name");
			String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			Integer emp_phone = null;
			try {
				 emp_phone = new Integer(req.getParameter("emp_phone").trim());
			}catch(Exception e) {
				errorMsgs.add("請輸入電話");
			}
			
			
			String emp_account = req.getParameter("emp_account").trim();
			if(emp_account == null ||emp_account.trim().length()==0 ) {
				errorMsgs.add("帳號請勿空白");
			}
			String emp_password = req.getParameter("emp_password").trim();
			if(emp_password == null || emp_password.trim().length()==0) {
				errorMsgs.add("密碼不能空白");
			}
			
			
			String emp_status =  req.getParameter("emp_status").trim();
		
			
			
			

			EmpVO empVO = new EmpVO();
			
			empVO.setEmp_id(emp_id);
			empVO.setEmp_name(emp_name);
			empVO.setEmp_phone(emp_phone);
			empVO.setEmp_account(emp_account);
			empVO.setEmp_password(emp_password);
			empVO.setEmp_status(emp_status);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("back/employee/update_emp_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			EmpService empSvc = new EmpService();
			empVO = empSvc.updateEmp( emp_id, emp_name, emp_phone, emp_account, emp_password, emp_status);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back/employee/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("back/employee/update_emp_input.jsp");
			failureView.forward(req, res);
		}
	}
	
	
//	==================================
		if("getOne_For_Display".equals(action)) {
		
		List<String>errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs",errorMsgs);
	
	
	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	try {
			String emp_id = req.getParameter("emp_id");
			if (emp_id  == null || (emp_id.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			} 
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			
			
			/***************************2.開始新增資料***************************************/
			EmpService empSvc = new EmpService();
			EmpVO  empVO = empSvc.getOneEmp(emp_id);
			if(emp_id == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("empVO",empVO);
			String url = "/back/employee/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
			/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
			errorMsgs.add("查不到你要的東西:");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back/employee/select_page.jsp");
		failureView.forward(req, res);
		}
	}	
//		System.out.println("=======================================");	
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String emp_id = req.getParameter("emp_id");
				
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back/employee/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//===================================================================
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String emp_id = new String(req.getParameter("emp_id"));
				
				/***************************2.開始刪除資料***************************************/
				
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(emp_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/employee/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
}
}
