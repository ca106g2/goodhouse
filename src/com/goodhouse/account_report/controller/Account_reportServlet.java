package com.goodhouse.account_report.controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.goodhouse.account_report.model.*;
import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.landlord.model.LanService;
import com.goodhouse.landlord.model.LanVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

public class Account_reportServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/* *********************************************************
		 *	                                                       *
		 *                   getOne_For_Display                    *
		 *                                                         *
		 * *********************************************************
		 */
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// asking for inupt and fix error
				String str = req.getParameter("acc_rep_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉帳號");
				}
				// send the use back to the form if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/select_page.jsp");
					failureView.forward(req, res);
					return;// end
				}

				String acc_rep_id = null;
				try {
					acc_rep_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				Account_reportService acreSvc = new Account_reportService();
				Account_reportVO acreVO = acreSvc.getOneAccount_report(acc_rep_id);
				if (acreVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("acreVO", acreVO);
				String url = "/back/account_report/listOneAccount_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		/*
		 * ********************************************************* *
		 * getOne_For_Update" * *
		 * *********************************************************
		 */
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String acc_rep_id = new String(req.getParameter("acc_rep_id"));
				Account_reportService acreSvc = new Account_reportService();
				Account_reportVO acreVO = acreSvc.getOneAccount_report(acc_rep_id);
				req.setAttribute("acreVO", acreVO);
				String url = "/back/account_report/update_acre_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得修改資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/listAllAccount_report");
				failureView.forward(req, res);
			}
		}
		/********************************************************** 
		 * 		 					 Update 					  *
		 *														  *
		 * ********************************************************
		 */
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			EmpService empSvc = new EmpService();
			try {
				String acc_rep_id = new String(req.getParameter("acc_rep_id").trim());

				String emp_id = new String (req.getParameter("emp_id"));
				String emp_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,10}$";
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.add("員工帳號不可空白");
				} else if (!emp_id.trim().matches(emp_idReg)) {
					errorMsgs.add("員工帳號格式錯誤或長度不正確");
				}
				
//				String emp_name = new String (req.getParameter("emp_name"));
//				for(EmpVO mvo : empSvc.getAll()) {
//					if(mvo.getEmp_name().equals(emp_name)) {
//						emp_id =mvo.getEmp_id();
//					}
//				}
//				if(emp_name == null || emp_name.trim().length() == 0) {
//					errorMsgs.add("員工姓名必須填寫");
//				}else if (!emp_name.trim().matches(emp_name)){
//					errorMsgs.add("員工姓名錯誤或長度不正確");
//				}

				String mem_id = new String(req.getParameter("mem_id").trim());
				String mem_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,10}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("檢舉帳號不可空白");
				} else if (!mem_id.trim().matches(mem_idReg)) {
					errorMsgs.add("檢舉帳號格式錯誤或長度不正確");
				}

				String lan_id = new String(req.getParameter("lan_id").trim());
				String lan_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,10}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("檢舉帳號不可空白");
				} else if (!lan_id.trim().matches(lan_idReg)) {
					errorMsgs.add("檢舉帳號格式錯誤或長度不正確");
				}

				String acc_rep_status = req.getParameter("acc_rep_status").trim();
				if (acc_rep_status == null || acc_rep_status.trim().length() == 0) {
					errorMsgs.add("檢舉狀態不可空白");
				}

				String acc_rep_reason = req.getParameter("acc_rep_reason").trim();
				if (acc_rep_reason == null || acc_rep_reason.trim().length() == 0) {
					errorMsgs.add("檢舉事由不可空白");
				}

				java.sql.Date acc_rep_date = null;
				try {
					acc_rep_date = java.sql.Date.valueOf(req.getParameter("acc_rep_date").trim());
				} catch (IllegalArgumentException e) {
					acc_rep_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
//----------------------------------字串日期-待測試
//				String acc_rep_date = req.getParameter("acr_date");
//				if(acc_rep_date == null || acc_rep_date.trim().length()==0) {
//					errorMsgs.add("時間不可空白");
//				}
//----------------------------------------------				
			
				Account_reportVO acreVO = new Account_reportVO();
				acreVO.setAcc_rep_id(acc_rep_id);
				acreVO.setEmp_id(emp_id);
				acreVO.setMem_id(mem_id);
				acreVO.setLan_id(lan_id);
				acreVO.setAcc_rep_status(acc_rep_status);
				acreVO.setAcc_rep_reason(acc_rep_reason);
				acreVO.setAcc_rep_date(acc_rep_date);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("acreVO", acreVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/update_acre_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Account_reportService acreSvc = new Account_reportService();
				acreVO = acreSvc.updateAccount_report(acc_rep_id, emp_id, mem_id, lan_id, acc_rep_status,
						acc_rep_reason, acc_rep_date);
				System.out.println(acreVO);//.......................
				req.setAttribute("acreVO", acreVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/account_report/listOneAccount_report.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料無法更新" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/update_acre_input.jsp");
				failureView.forward(req, res);
			}
		}
		/*
		 * ********************************************************* 
		 *														   *
		 *                                                         * 
		 *                      Insert                             *
		 *                                                         * 
		 *                                                         *
		 * *********************************************************
		 */

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				EmpService empSvc = new EmpService();
				MemService memSvc = new MemService();
				LanService lanSvc = new LanService();

//				String emp_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,10}$";
//				if (emp_id == null || emp_id.trim().length() == 0) {
//					errorMsgs.add("員工帳號不可空白");
//				} else if (!emp_id.trim().matches(emp_idReg)) {
//					errorMsgs.add("員工帳號格式錯誤或長度不正確");
//				}
				String emp_id = null;
				String emp_name = new String (req.getParameter("emp_name"));
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,5}$";
				String emp_name_test = null;
				if(emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名格式錯誤");	
				}else if (!emp_name.trim().matches(emp_nameReg)) {
					errorMsgs.add("員工帳號格式錯誤或長度不正確");
				}
				for(EmpVO mvo : empSvc.getAll()) {
					if(mvo.getEmp_name().equals(emp_name)) {
						emp_id =mvo.getEmp_id();	
					}
				}				
			
				String mem_id=null;
				String mem_name =req.getParameter("mem_name").trim();
				String mem_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,5}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("房客姓名錯誤");
				} else if (!mem_name.trim().matches(mem_idReg)) {
					errorMsgs.add("房客格式錯誤或長度不正確");
				}
				for(MemVO memvo : memSvc.getAll()) {
					if(memvo.getMem_name().equals(mem_name)) {
						mem_id = memvo.getMem_id();
					}
				}
				
				
				String lan_id=req.getParameter("lan_id").trim();
				
//				String lan_name = req.getParameter("lan_name").trim();
//				String lan_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,5}$";
//				if(lan_name == null || lan_name.trim().length()==0) {
//					errorMsgs.add("房東姓名錯誤");		
//				} else if (!lan_name.trim().matches(lan_idReg)) {
//					errorMsgs.add("房東格式錯誤或長度不正確");
//				}
//				for(LanVO lanvo : lanSvc.getAll()) {	
//					if(lanvo.getLan_id().equals(lan_name)) {
//						
//					}
//					
//				}

				
				
				
				

				String acc_rep_status = req.getParameter("acc_rep_status").trim();
				if (acc_rep_status == null || acc_rep_status.trim().length() == 0) {
					errorMsgs.add("檢舉型態不可空白");
				}
				String acc_rep_reason = req.getParameter("acc_rep_reason").trim();
				if (acc_rep_reason == null || acc_rep_reason.trim().length() == 0) {
					errorMsgs.add("檢舉事由不可空白");
				}
				java.sql.Date acc_rep_date = null;
				try {
					acc_rep_date = java.sql.Date.valueOf(req.getParameter("acc_rep_date").trim());
				} catch (IllegalArgumentException e) {
					acc_rep_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}

				Account_reportVO acreVO = new Account_reportVO();
				acreVO.setEmp_id(emp_id);
				acreVO.setMem_id(mem_id);
				acreVO.setLan_id(lan_id);
				acreVO.setAcc_rep_status(acc_rep_status);
				acreVO.setAcc_rep_reason(acc_rep_reason);
				acreVO.setAcc_rep_date(acc_rep_date);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("acreVO", acreVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/account_report/addAccount_report.jsp");
					failureView.forward(req, res);
					return;
				}

				Account_reportService acreSvc = new Account_reportService();
				acreVO = acreSvc.addAccount_report(emp_id, mem_id, lan_id, acc_rep_status, acc_rep_reason
				,acc_rep_date);

				RequestDispatcher successView = req
						.getRequestDispatcher("/back/account_report/listAllAccount_report.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/account_report/addAccount_report.jsp");
				failureView.forward(req, res);
			}
		}
		/* ********************************************************
		 *														  *
		 *  					 Delete   		                  *
		 *  													  *
		 * ********************************************************
		 */

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String acc_rep_id = new String(req.getParameter("acc_rep_id").trim());

				Account_reportService acreSvc = new Account_reportService();
				acreSvc.deleteAccount_report(acc_rep_id);

				RequestDispatcher successView = req
						.getRequestDispatcher("/back/account_report/listAllAccount_report.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗: "+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/account_report/listAllAccount_report.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
