package com.goodhouse.member.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

/**
 * Servlet implementation class MemberServlet
 */
@MultipartConfig
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
		
	
	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	try {
		
			String mem_name = req.getParameter("mem_name");
			String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(mem_name == null || mem_name.trim().length() == 0) {
				errorMsgs.add("會員姓名請勿空白");
			}else if(!mem_name.trim().matches(mem_nameReg)) {
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
		
			java.sql.Date mem_birthday = null;
			try {
				mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());	
			}catch(IllegalArgumentException e) {
				mem_birthday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期"+e.getMessage());
			}
			
			
			String mem_password = req.getParameter("mem_password").trim();
			if(mem_password == null || mem_password.trim().length()==0) {
				errorMsgs.add("密碼請勿空白");
			}
			String mem_address = req.getParameter("mem_address").trim();
			if(mem_address == null || mem_address.trim().length()==0) {
				errorMsgs.add("地址請勿空白");
			}
			String mem_zipcode = req.getParameter("mem_zipcode").trim();
			if(mem_zipcode == null || mem_zipcode.trim().length()==0) {
				errorMsgs.add("郵遞區號請勿空白");
			}
//			
			Integer mem_telephone = null;
			try {
				mem_telephone = new Integer(req.getParameter("mem_telephone").trim());
			}catch(Exception e) {
				errorMsgs.add("電話請填數字");
			}
			
			Integer  mem_phone = null;
					try{
						mem_phone = new Integer(req.getParameter("mem_phone").trim());
					}catch(Exception e) {
						errorMsgs.add("手機請填數字");
					}
			String mem_email = req.getParameter("mem_email").trim();
			if(mem_email == null || mem_email.trim().length()== 0) {
				errorMsgs.add("請填入email");
			}
			String mem_status = req.getParameter("mem_status").trim();
			if( mem_status == null || mem_status.trim().length()== 0) {
				errorMsgs.add("請填入正確狀態");
			}
			
			
			byte mem_picture[] = null;
			try {
				Part part = req.getPart("mem_picture");
				if(part.getSubmittedFileName() != "") {
					BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					int len;
					byte[] b = new byte[8192];
					while((len = bif.read(b)) != -1) {
						bao.write(b);
					}
					mem_picture = bao.toByteArray();
				}
			}catch(Exception e) {
				errorMsgs.add("上傳照片失敗，請重新上傳");
			}
			
			Integer  good_total = null;
					try{
						good_total = new Integer(req.getParameter("good_total").trim());
					}catch(Exception e) {
						errorMsgs.add("積分請填數字");
					}
			
			String mem_sex = req.getParameter("mem_sex").trim();
			if(mem_sex == null || mem_sex.trim().length()== 0) {
				errorMsgs.add("請填入正確狀態");
			}
			
			
			
			
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
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/addMem.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			MemService memSvc = new MemService();
			memVO = memSvc.addMem(mem_name, mem_birthday,mem_password,mem_address,mem_zipcode,mem_telephone,mem_phone,mem_email,mem_status,mem_picture,good_total,mem_sex);
			
		
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/front/member/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
			
			
	}catch(Exception e) {
		
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/front/member/addMem.jsp");
	failureView.forward(req, res);
	}
	}
	//==================================	
		if("update".equals(action)) {
			List<String>errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs );
		
				try {
					String mem_id = req.getParameter("mem_id");
					if(mem_id == null || mem_id.trim().length() == 0) {
						errorMsgs.add("會員姓名請勿空白");
					}
					
					String mem_name = req.getParameter("mem_name");
					String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if(mem_name == null || mem_name.trim().length() == 0) {
						errorMsgs.add("會員姓名請勿空白");
					}else if(!mem_name.trim().matches(mem_nameReg)) {
						errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
				
					java.sql.Date mem_birthday = null;
					try {
						mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());	
					}catch(IllegalArgumentException e) {
						mem_birthday = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期");
					}
					
					
					String mem_password = req.getParameter("mem_password").trim();
					if(mem_password == null || mem_password.trim().length()==0) {
						errorMsgs.add("密碼請勿空白");
					}
					String mem_address = req.getParameter("mem_address").trim();
					if(mem_address == null || mem_address.trim().length()==0) {
						errorMsgs.add("地址請勿空白");
					}
					String mem_zipcode = req.getParameter("mem_zipcode").trim();
					if(mem_zipcode == null || mem_zipcode.trim().length()==0) {
						errorMsgs.add("郵遞區號請勿空白");
					}
//					
					Integer mem_telephone = null;
					try {
						mem_telephone = new Integer(req.getParameter("mem_telephone").trim());
					}catch(Exception e) {
						errorMsgs.add("電話請填數字");
					}
					
					Integer  mem_phone = null;
							try{
								mem_phone = new Integer(req.getParameter("mem_phone").trim());
							}catch(Exception e) {
								errorMsgs.add("手機請填數字");
							}
					System.out.println(mem_phone);	
						
					String mem_email = req.getParameter("mem_email").trim();
					if(mem_email == null || mem_email.trim().length()== 0) {
						errorMsgs.add("請填入email");
					}
					String mem_status = req.getParameter("mem_status").trim();
					if( mem_status == null || mem_status.trim().length()== 0) {
						errorMsgs.add("請填入正確狀態");
					}
					
					
					byte mem_picture[] = null;
					try {
						Part part = req.getPart("mem_picture");
						if(part.getSubmittedFileName() != "") {
							BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
							ByteArrayOutputStream bao = new ByteArrayOutputStream();
							int len;
							byte[] b = new byte[8192];
							while((len = bif.read(b)) != -1) {
								bao.write(b);
							}
							mem_picture = bao.toByteArray();
						}
					}catch(Exception e) {
						errorMsgs.add("上傳照片失敗，請重新上傳");
					}
					
					Integer  good_total = null;
							try{
								good_total = new Integer(req.getParameter("good_total").trim());
							}catch(Exception e) {
								errorMsgs.add("積分請填數字");
							}
					
					String mem_sex = req.getParameter("mem_sex").trim();
					if(mem_sex == null || mem_sex.trim().length()== 0) {
						errorMsgs.add("請填入正確狀態");
					}
					
			
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
				memVO.setMem_id(mem_id);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_name, mem_birthday,mem_password,mem_address,mem_zipcode,mem_telephone,mem_phone,mem_email,mem_status,mem_picture,good_total,mem_sex,mem_id);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.removeAttribute("memVO");
				session.setAttribute("memVO", memVO);
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
		
		
		try {
			String mem_id = req.getParameter("mem_id");
			if (mem_id  == null || (mem_id.trim()).length() == 0) {
				errorMsgs.add("請輸入編號");
			} 
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
		
			/***************************2.開始查詢資料***************************************/
			MemService memSvc = new MemService();
			MemVO  memVO = memSvc.getOneMem(mem_id);
			if(memVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)***********/
			req.setAttribute("memVO",memVO);
			String url = "/front/member/listOneMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
		}catch(Exception e) {
			errorMsgs.add("查不到你要的東西:");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/member/select_page.jsp");
		failureView.forward(req, res);
		}
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
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front/member/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/listOneMem.jsp");
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
			String url = "/front/member/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/listOneMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if("buildMem".equals(action)) {
//			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				MemVO memVO = new MemVO();
				memVO.setMem_name("王王王");
				memVO.setMem_password("555999a");
				memVO.setMem_address("新北");
				memVO.setMem_zipcode("55985");
				memVO.setMem_telephone(228559999);
				memVO.setMem_phone(966555888);
				memVO.setMem_email("abc@abc.com");
				memVO.setMem_status("1");
				memVO.setGood_total(8888);
				memVO.setMem_sex("1");
				
				req.setAttribute("memVO",memVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front/member/addMem.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			}catch(Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
}
