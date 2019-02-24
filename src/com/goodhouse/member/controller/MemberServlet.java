package com.goodhouse.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(req, res);	
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	if("insert".equals(action)) {
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs",errorMsgs);
		
	}
	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	try {
			String mem_name = req.getParameter("mem_name");
			String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(mem_name == null || mem_name.trim().length() == 0) {
				errorMsgs.add("會員姓名請勿空白");
			}else if(!mem_name.trim().matches(mem_nameReg)) {
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			Date mem_birthday = new Date(req.getParameter("mem_birthday").trim());
			String mem_password = req.getParameter("mem_password").trim();
			String mem_address = req.getParameter("mem_address").trim();
			String mem_zipcode = req.getParameter("mem_zipcode").trim();
			Integer mem_telephone = new Integer(req.getParameter("mem_telephone").trim());
			Integer  mem_phone = new Integer(req.getParameter(" mem_phone").trim());
			String mem_email = req.getParameter("mem_email").trim();
			String mem_status = req.getParameter("mem_status").trim();
			Byte mem_picture = req.getParameter("mem_picture").trim();
			Integer  good_total = new Integer(req.getParameter(" good_total").trim());
			String mem_sex = req.getParameter("mem_sex").trim();
		
			MemVO memVO = new MemVO();
			memVO.setMem_name(mem_name);
			memVO.setMem_birthday(mem_birthday);
			memVO.setMem_password(mem_password);
			memVO.setMem_address(mem_address);
			memVO.setMem_zipcode(mem_zipcode);
			memVO.setMem_telephone(mem_telephone);
			memVO.setMem_phone(mem_phone);
			memVO.setMem_email(mem_email);
			memVO.setMem_status(mem_status);
			memVO.setMem_picture(mem_picture);
			memVO.setGood_total(good_total);
			memVO.setMem_sex(mem_sex);
			
			if(!erroeMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			MemService memSvc = new MemService();
			memVO = memSvc.addEmp(mem_name, mem_birthday,mem_password,mem_address,mem_zipcode,mem_telephone,mem_phone,mem_email,mem_status,mem_picture,good_total,mem_sex);
			
		
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/front/member/listAllMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
			
			
	}catch(Exception e) {
		
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/front/member/addEmp.jsp");
	failureView.forward(req, res);
	}
	//==================================	
		if("update".equals(action)) {
			List<String>errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs );
		
				try {
					String mem_name = req.getParameter("mem_name");
					String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名請勿空白");
				}else if(!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Date mem_birthday = new Date(req.getParameter("mem_birthday").trim());
				String mem_password = req.getParameter("mem_password").trim();
				String mem_address = req.getParameter("mem_address").trim();
				String mem_zipcode = req.getParameter("mem_zipcode").trim();
				Integer mem_telephone = new Integer(req.getParameter("mem_telephone").trim());
				Integer  mem_phone = new Integer(req.getParameter(" mem_phone").trim());
				String mem_email = req.getParameter("mem_email").trim();
				String mem_status = req.getParameter("mem_status").trim();
				Byte mem_picture = req.getParameter("mem_picture").trim();
				Integer  good_total = new Integer(req.getParameter(" good_total").trim());
				String mem_sex = req.getParameter("mem_sex").trim();
			
				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_birthday(mem_birthday);
				memVO.setMem_password(mem_password);
				memVO.setMem_address(mem_address);
				memVO.setMem_zipcode(mem_zipcode);
				memVO.setMem_telephone(mem_telephone);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_email(mem_email);
				memVO.setMem_status(mem_status);
				memVO.setMem_picture(mem_picture);
				memVO.setGood_total(good_total);
				memVO.setMem_sex(mem_sex);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("front/member/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addEmp(mem_name, mem_birthday,mem_password,mem_address,mem_zipcode,mem_telephone,mem_phone,mem_email,mem_status,mem_picture,good_total,mem_sex);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front/member/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);	
				
				}catch(Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/update_mem_input.jsp");
					failureView.forward(req, res);
				}
			}
		
		//=================================================
		
		if("getOne_For_Display".equals(action)) {
			
			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
		}
		
		try {
			String mem_id = req.getParameter("mem_id");
			if (mem_id  == null || (mem_id.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			} 
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************2.開始新增資料***************************************/
			MemService memSvc = new MemService();
			MemVO  memVO = memSvc.getOneEmp(mem_id);
			if(mem_id == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("memVO",memVO);
			String url = "/front/mem/listOneMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
		}catch(Exception e) {
			errorMsgs.add("查不到你要的東西:");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/member/select_page.jsp");
		failureView.forward(req, res);
		}
	
		//==================================================================
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數****************************************/
				String mem_id = req.getParameter("mem_id");	
			
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front/member/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	//=========================================================
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		try {
			/***************************1.接收請求參數***************************************/
			String mem_id = new String(req.getParameter("mem_id"));	
			
			/***************************2.開始刪除資料***************************************/
			
			MemService memSvc = new MemService();
			memSvc.deleteMem(mem_id);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/front/member/listAllMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
