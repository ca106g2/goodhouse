package com.goodhouse.landlord.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.landlord.model.LanService;
import com.goodhouse.landlord.model.LanVO;


@MultipartConfig
public class LandlordServlet extends HttpServlet{
	
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
		
		try {
				String mem_id = req.getParameter("mem_id");
				
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
				
			String lan_receipt = req.getParameter("lan_receipt");
			if( lan_receipt == null ||  lan_receipt.trim().length() == 0) {
				errorMsgs.add("發票請勿空白");
			}
			
			String lan_account = req.getParameter("lan_account").trim();
			if( lan_account == null ||  lan_account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			String lan_accountstatus = req.getParameter("lan_accountstatus").trim();
			if( lan_accountstatus == null ||  lan_accountstatus.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			
			byte lan_ciziten[] = null;
			try {
				Part part = req.getPart("lan_ciziten");
				if(part.getSubmittedFileName() != "") {
					BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					int len;
					byte[] b = new byte[8192];
					while((len = bif.read(b)) != -1) {
						bao.write(b);
					}
					lan_ciziten = bao.toByteArray();
				}
			}catch(Exception e) {
				errorMsgs.add("上傳照片失敗，請重新上傳");
			}
			
			LanVO lanVO = new LanVO();
			lanVO.setMem_id(mem_id);
			lanVO.setLan_receipt(lan_receipt);
			lanVO.setLan_account(lan_account);
			lanVO.setLan_accountstatus(lan_accountstatus);
			lanVO.setLan_ciziten(lan_ciziten);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("lanVO", lanVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/landlord/addLan.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
			LanService lanSvc = new LanService();
			lanVO = lanSvc.addLan(mem_id,lan_receipt, lan_account, lan_accountstatus, lan_ciziten);	
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("lanVO", lanVO);
			String url = "/front/landlord/listAllLan.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
			
		} catch(Exception e){
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/landlord/addLan.jsp");
		failureView.forward(req, res);
	   }
			
		}
	//========================================================
		if("update".equals(action)) {
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs );
		
			try {
			
				String lan_id = req.getParameter("lan_id");
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} 
				String mem_id = req.getParameter("mem_id");
				
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
			String lan_receipt = req.getParameter("lan_receipt").trim();
			if( lan_receipt == null ||  lan_receipt.trim().length() == 0) {
				errorMsgs.add("發票請勿空白");
			}
			String lan_account = req.getParameter("lan_account").trim();
			if( lan_account == null ||  lan_account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			String lan_accountstatus = req.getParameter("lan_accountstatus").trim();
			if( lan_accountstatus == null ||  lan_accountstatus.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			
			byte lan_ciziten[] = null;
			try {
				Part part = req.getPart("lan_ciziten");
				if(part.getSubmittedFileName() != "") {
					BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					int len;
					byte[] b = new byte[8192];
					while((len = bif.read(b)) != -1) {
						bao.write(b);
					}
					lan_ciziten = bao.toByteArray();
				}
			}catch(Exception e) {
				errorMsgs.add("上傳照片失敗，請重新上傳");
			}
			
			

			LanVO lanVO = new LanVO();
			
			lanVO.setMem_id(mem_id);
			lanVO.setLan_receipt(lan_receipt);
			lanVO.setLan_account(lan_account);
			lanVO.setLan_accountstatus(lan_accountstatus);
			lanVO.setLan_ciziten(lan_ciziten);
			lanVO.setLan_id(lan_id);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("lanVO", lanVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/landlord/update_lan_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始修改資料***************************************/
			LanService lanSvc = new LanService();
			lanVO = lanSvc.updateLan(mem_id,lan_receipt, lan_account, lan_accountstatus,lan_ciziten,lan_id);	
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("lanVO", lanVO);
			String url = "/front/landlord/listOneLan.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);		
		
			/***************************其他可能的錯誤處理*************************************/
		} catch(Exception e){
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/landlord/udpate_lan_input.jsp");
		failureView.forward(req, res);
	   }
			
		}
//		==================================
		if("getOne_For_Display".equals(action)) {
			
			List<String>errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
		
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
		
			try {
				String lan_id = req.getParameter("lan_id");
				if (lan_id  == null || (lan_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				} 
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/landlord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			
				/***************************2.開始新增資料***************************************/
				LanService lanSvc = new LanService();
				LanVO  lanVO = lanSvc.getOneLan(lan_id);
				if(lan_id == null) {
					errorMsgs.add("查無資料");
				}	
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/landlord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("lanVO",lanVO);
				String url = "/front/landlord/listOneLan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
			
		}catch (Exception e) {
			errorMsgs.add("查不到你要的東西:");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/landlord/select_page.jsp");
		failureView.forward(req, res);
		}
	}	
//=======================================================
	
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數****************************************/
				String lan_id = req.getParameter("lan_id");
	
				/***************************2.開始查詢資料****************************************/
				LanService lanSvc = new LanService();
				LanVO lanVO = lanSvc.getOneLan(lan_id);	
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lanVO", lanVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front/landlord/update_lan_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/landlord/listAllLan.jsp");
			failureView.forward(req, res);
		}
	
		}
		//====================================================
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數***************************************/
				String lan_id = new String(req.getParameter("lan_id"));
				
				/***************************2.開始刪除資料***************************************/
				
				LanService lanSvc = new LanService();
				lanSvc.deleteLan(lan_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front/landlord/listAllLan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			
			
			
			}catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/landlord/listAllLan.jsp");
				failureView.forward(req, res);
			}
	
		}
	
	}
}