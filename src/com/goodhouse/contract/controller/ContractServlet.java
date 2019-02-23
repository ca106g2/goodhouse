package com.goodhouse.contract.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.contract.model.ContractService;
import com.goodhouse.contract.model.ContractVO;

public class ContractServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//用contract_id單一查詢
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***********1接收參數*************/
				
				String con_id = req.getParameter("con_id");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/contract/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				try {
//					con_id = new String(con_id);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/contract/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/************2開始查詢資料**********************/
				
				ContractService conSvc = new ContractService();
				ContractVO conVO = conSvc.getOneCon(con_id);
				
				if(conVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/contract/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*************3.查詢完成準備轉交***********************/
				req.setAttribute("conVO", conVO);
				String url = "/back/contract/listOne_contract.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOne_contract.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/contract/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//修改
		if ("getOne_For_Update".equals(action)) { 
			// 來自listAll_contract.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/********1開始接收參數****************/
				String con_id = new String(req.getParameter("con_id"));
				
				/**********2開始查詢資料************/
				
				ContractService conSvc = new ContractService();
				ContractVO conVO = conSvc.getOneCon(con_id);
				
				/**********3查詢完成，開始轉交資料************/
				req.setAttribute("conVO", conVO);
				String url = "/back/contract/update_contract_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/contract/listAll_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("update".equals(action)) {
			//來自update_contract_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				
				/******1開始接收資料**********************/
				String con_id = req.getParameter("con_id");
				
				String con_name = req.getParameter("con_name");
				if(con_name == null || con_name.trim().length() == 0) {
					errorMsgs.add("合約名稱不能空白");
				}
				
				String con_content = req.getParameter("con_content");
				if(con_content == null || con_content.trim().length() == 0) {
					errorMsgs.add("合約內容不能空白");
				}
				String con_status = req.getParameter("con_status");
				if(con_status == null || con_status.trim().length() == 0) {
					errorMsgs.add("請選擇合約分類的開放狀態");
				}
				
				ContractVO conVO = new ContractVO();
				conVO.setCon_id(con_id);
				conVO.setCon_name(con_name);
				conVO.setCon_content(con_content);
				conVO.setCon_status(con_status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("conVO", conVO);
					RequestDispatcher faliureView = req.getRequestDispatcher("/back/contract/update_contract_input.jsp");
					faliureView.forward(req, res);
					return;//程式中斷
				}
				
				/*******2開始修改資料****************************/
				
				ContractService conSvc = new ContractService();
				conSvc.updateCon(conVO);
				
				/********3修改完成，準備轉交******************************/
				
				req.setAttribute("conVO", conVO);
				String url = "/back/contract/listOne_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/********其他的可能錯誤處理******************************/
				
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/contract/update_contract_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		//新增
		if("insert".equals(action)) {
			//來自add_contract.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*****1接收請求參數************************/
				String con_name = req.getParameter("con_name");
				if(con_name == null || con_name.trim().length() == 0) {
					errorMsgs.add("合約名稱不能空白");
				}
				
				String con_content = req.getParameter("con_content");
				if(con_content == null || con_content.trim().length() == 0) {
					errorMsgs.add("合約內容不能空白");
				}
				
				String con_status = req.getParameter("con_status");
				if(con_status == null || con_status.trim().length() == 0) {
					errorMsgs.add("請選擇合約啟用狀態");
				}
				
				ContractVO conVO = new ContractVO();
				conVO.setCon_name(con_name);
				conVO.setCon_content(con_content);
				conVO.setCon_status(con_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("conVO", conVO); // 含有輸入格式錯誤的ContractVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/contract/add_contract.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*****2開始新增資料*********/
				ContractService conSvc = new ContractService();
				conSvc.addCon(conVO);
				
				/*****3新增完成，準備轉交*****************/
				String url = "/back/contract/listAll_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/contract/add_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//停用(取代掉刪除功能)
		if ("getOne_For_Stop".equals(action)) {
			// 來自listAll_contract.jsp的請求
			
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
						
						try {
							/********1開始接收參數****************/
							String con_id = new String(req.getParameter("con_id"));
							
							/**********2開始查詢資料************/
							
							ContractService conSvc = new ContractService();
							ContractVO conVO = conSvc.getOneCon(con_id);
							
							/**********3查詢完成，開始轉交資料************/
							req.setAttribute("conVO", conVO);
							String url = "/back/contract/stop_contract.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req,res);
							
							
						} catch (Exception e) {
							errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back/contract/listAll_contract.jsp");
							failureView.forward(req, res);
						}
						
		}
	}
}
