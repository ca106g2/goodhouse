package com.goodhouse.employee_permission.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.employee_permission.model.*;
import com.goodhouse.employee.model.*;

/**
 * Servlet implementation class Employee_PermissionServlet
 */
@WebServlet("/Employee_PermissionServlet")
public class Employee_PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getSome_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("emp_ID");
				String str2[] = req.getParameterValues("per_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入員工ID");
				}
//				if (str2== null || (str2.trim()).length() == 0) {
//					errorMsgs.add("請輸入權限ID");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_ID = null;
				try {
					emp_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("員工ID格式不正確");
				}
				
				String per_ID[] = null;
				System.out.println(str2);
				try {
					per_ID = str2;
				} catch (Exception e) {
					errorMsgs.add("權限ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Emp_PerService emp_perSvc = new Emp_PerService();//???
				List<Emp_PerVO> list  = emp_perSvc.getPartOfOneEmp_Per(emp_ID);
				
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list); // 資料庫取出的EmpAuthVO物件,存入req
				String url = "/back/employee_permission/listSomeEmp_Per.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmpAuth.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee_permission/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自addEmpAuth.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("emp_id");
				String str2 = req.getParameter("per_id");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入員工ID");
				}
				if (str2== null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入權限ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_ID = null;
				try {
					emp_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("員工ID格式不正確");
				}
				
				String per_ID = null;
				try {
					per_ID = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("權限ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Emp_PerService emp_perSvc = new Emp_PerService();//
				Emp_PerVO emp_perVO  =  emp_perSvc.getOneEmp_Per(emp_ID, per_ID);
				
				if (emp_perVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("emp_perVO", emp_perVO); // 資料庫取出的empAuthVO物件,存入req
				String url = "/back/employee_permission/listOneEmp_Per.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmpAuth.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee_permission/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		if ("getPer_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				System.out.println(emp_ID);
				//String auth_ID[] = new String(req.getParameter("auth_ID"));
				
				/***************************2.開始查詢資料****************************************/
				Emp_PerService emp_perSvc = new Emp_PerService();
				List<Emp_PerVO> listEmp_perVO = emp_perSvc.getPartOfOneEmp_Per(emp_ID);
				List<String> listPer_ID = emp_perSvc.getPartOfOneEmp_Per2(emp_ID);
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.getSession().setAttribute("empVO", empVO);
				req.getSession().setAttribute("listEmp_PerVO", listEmp_perVO);         // 資料庫取出的empAuthVO物件,存入req //???
				req.getSession().setAttribute("listAuth_ID", listPer_ID);         // 資料庫取出的empAuthVO物件,存入req //???
				String url = "/back/employee_permission/update_emp_per_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_empAuth_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/empAuth/listAllEmpAuth.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmpAuth.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				String per_ID = new String(req.getParameter("per_ID"));
				
				/***************************2.開始查詢資料****************************************/
				Emp_PerService emp_perSvc = new Emp_PerService();
				Emp_PerVO emp_perVO = emp_perSvc.getOneEmp_Per(emp_ID, per_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("emp_perVO", emp_perVO);         // 資料庫取出的empAuthVO物件,存入req
				String url = "/back/employee_permission/update_emp_per_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_empAuth_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee_permission/listAllEmp_Per.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_empAuth_input.jsp的請求 //?
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_ID = new String(req.getParameter("emp_ID").trim());
				String per_ID[] = req.getParameterValues("per_ID");
				
				List<Emp_PerVO> listEmp_PerVO = new LinkedList<Emp_PerVO>();
				for(Integer i = 0; i < per_ID.length; i++) {
					Emp_PerVO emp_perVO = new 	Emp_PerVO();
					emp_perVO .setEmp_id(emp_ID);
					emp_perVO.setPer_id(per_ID[i]);
					listEmp_PerVO.add(emp_perVO);
				}
				
				
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("list", listEmp_PerVO); // 含有輸入格式錯誤的empAuthVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee/listAllEmp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				Emp_PerService emp_perSvc = new Emp_PerService();
				listEmp_PerVO = emp_perSvc.updateAllPer(emp_ID, listEmp_PerVO );
				
				Emp_PerService empPerSvc2 = new Emp_PerService();//???
				List<Emp_PerVO> list  = empPerSvc2.getPartOfOneEmp_Per(emp_ID);
				
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list); // 資料庫update成功後,正確的的empAuthVO物件,存入req
				String url = "/back/employee_permission/listSomeEmp_Per.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmpAuth.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee_permission/update_emp_per_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmpAuth.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_ID = req.getParameter("emp_ID");
				String emp_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (emp_ID == null || emp_ID.trim().length() == 0) {
					errorMsgs.add("員工ID: 請勿空白");
				} else if(!emp_ID.trim().matches(emp_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
				
				String per_ID = req.getParameter("per_ID");
				String per_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (per_ID == null || per_ID.trim().length() == 0) {
					errorMsgs.add("權限ID: 請勿空白");
				} else if(!per_ID.trim().matches(per_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("權限ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
										
				Emp_PerVO emp_PerVO = new Emp_PerVO();
								System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("emp_perVO", emp_PerVO); // 含有輸入格式錯誤的empAuthVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/employee_permission/addEmp_Per.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Emp_PerService emp_perSvc = new Emp_PerService();
				System.out.println(emp_ID);
				System.out.println(per_ID);
				emp_PerVO = emp_perSvc.addEmp_Per(emp_ID, per_ID);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/employee_permission/listAllEmp_Per.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmpAuth.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee_permission/addEmp_Per.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmpAuth.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				String per_ID = new String(req.getParameter("per_ID"));
				
				/***************************2.開始刪除資料***************************************/
				Emp_PerService emp_perSvc = new Emp_PerService();
				emp_perSvc.deleteEmp_Per(emp_ID, per_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/employee_permission/listAllEmp_Per.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/employee_permission/listAllEmp_Per.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

