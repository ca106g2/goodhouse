package com.goodhouse.house_evaluate.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.goodhouse.house.model.*;
import com.goodhouse.house_evaluate.model.House_EvaluateService;
import com.goodhouse.house_evaluate.model.House_EvaluateVO;
import com.goodhouse.member.model.*;

public class House_EvaluateServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//單一查詢
		if ("getOne_For_Display".equals(action)) { //來自select_page.jsp的要求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**************1接收請求要求****************/
				String str = req.getParameter("hou_eva_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房屋評價編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/house_evaluate/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String hou_eva_id = null;
				
				try {
					hou_eva_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("房屋評價編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/house_evaluate/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				House_EvaluateService heSvc = new House_EvaluateService();
				House_EvaluateVO heVO = heSvc.getOneHE(hou_eva_id);
				
				if (heVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/house_evaluate/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("House_EvaluateVO", heVO);
				String url = "/front/house_evaluate/listOne_house_evaluate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneHouse_evaluate.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/house_evaluate/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//選擇單一修改
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				String hou_eva_id = new String(req.getParameter("hou_eva_id"));
				/***************************2.開始查詢資料****************************************/
				House_EvaluateService heSvc = new House_EvaluateService();
				House_EvaluateVO heVO = heSvc.getOneHE(hou_eva_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("House_EvaluateVO", heVO); 
				String url = "/front/house_evaluate/update_house_evaluate_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_house_evaluate_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/house_evaluate/listAll_house_evaluate.jsp");
				failureView.forward(req, res);
			}
		}
		
		//修改
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String hou_eva_id = req.getParameter("hou_eva_id");
				
				//會員編號比對
				String mem_id = req.getParameter("mem_id");
				/*
				 	正規表達式
				 	^[指定的字元]{最少幾個,最多幾個}$
				 */
//				String mem_idReg = "^[M]{1}[0-9]{9}$";
//				if(mem_id == null || mem_id.trim().length() == 0) {
//					errorMsgs.add("會員編號不能空白");
//				} else if (!mem_id.trim().matches(mem_idReg)){
//					errorMsgs.add("會員編號 : 由 M + 9個數字組成 ");
//				}
				
				//房屋編號比對
				String hou_id = req.getParameter("hou_id");
//				String hou_idReg = "^[(HOU)]{1}[0-9]{7}$";
//				if(hou_id == null || hou_id.trim().length() == 0 ) {
//					errorMsgs.add("房屋編號不能空白");
//				}else if(!hou_id.trim().matches(hou_idReg)) {
//					errorMsgs.add("房屋編號 : 由 HOU + 7個數字組成 ");
//				}
				
				//評價等級
				String hou_eva_grade = req.getParameter("hou_eva_grade");
//				String hou_eva_gradeReg = "[(G1非常不好)(G2不好)(G3普通)(G4好)(G5非常好)]{1}";
				if(hou_eva_grade == null || hou_eva_grade.trim().length() == 0) {
					errorMsgs.add("請選擇評價等級");
				}
				
				//評價內容
				String hou_eva_content = req.getParameter("hou_eva_content");
				
				House_EvaluateVO heVO = new House_EvaluateVO();
				heVO.setHou_eva_id(hou_eva_id);
				heVO.setMem_id(mem_id);
				heVO.setHou_id(hou_id);
				heVO.setHou_eva_grade(hou_eva_grade);
				heVO.setHou_eva_content(hou_eva_content);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("House_EvaluateVO", heVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/house_evaluate/update_house_evaluate_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				House_EvaluateService heSvc = new House_EvaluateService();
				heVO = heSvc.updateHE(hou_eva_id, mem_id, hou_id, hou_eva_grade, hou_eva_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("House_EvaluateVO", heVO); // 資料庫update成功後,正確的House_EvaluateVO物件,存入req
				String url = "/front/house_evaluate/listOne_house_evaluate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneHouse_evaluate.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/house_evaluate/update_house_evaluate_input.jsp");
				failureView.forward(req, res);
			}
		}
		//刪除
		if ("insert".equals(action)) {//來自add_house_evaluate.jsp請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/house_evaluate/add_house_evaluate.jsp");
				failureView.forward(req, res);
			}
			
		
		}
		
		//刪除
		if ("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				
				/***************************1.接收請求參數***************************************/
				
				String hou_eva_id = new String(req.getParameter("hou_eva_id"));
				
				/***************************2.開始刪除資料***************************************/
				
				House_EvaluateService heSvc = new House_EvaluateService();
				heSvc.deleteHE(hou_eva_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				
				String url = "/front/house_evaluate/listAll_house_evaluate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/house_evaluate/listAll_house_evaluate.jsp");
				failureView.forward(req, res);
			}
		}
		
		//新增，來自add_house_evaluate.jsp請求
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			MemService mSvc = new MemService();
			
			try {
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				//會員資料比對
				String mem_id = null;
				//取得使用者輸入的名字
				String mem_name = req.getParameter("mem_name");
				//檢查使用者有沒有輸入資料
				if(mem_name == null || mem_name.trim().length() == 0 ) {
					errorMsgs.add("姓名請勿空白");
				}
				
				//利用service的getAll方法取出所有會員的資料
				for(MemVO mVO : mSvc.getAll()) {
					//把每筆所取出的資料跟使用者輸入的名字做比對
					if(mem_name.equals(mVO.getMem_name())) {
						//符合資料庫裡有的名字才取出會員ID
						mem_id = mVO.getMem_id();
					} 
				}
				//檢查取出的會員id是否為空值
				if(mem_id == null){
					errorMsgs.add("姓名輸入錯誤");
				}
				
				
				//房屋編號比對
				String hou_id = req.getParameter("hou_id");
//				for(HouseVO hVO : hSvc.getAll()) {
//					if(hVO.getHou_id().equals(hou_id)) {
//						hou_id = hVO.getHou_id();
//					}
//				}
				
				
				String hou_eva_grade = req.getParameter("hou_eva_grade");
				if(hou_eva_grade == null || hou_eva_grade.trim().length() == 0) {
					errorMsgs.add("請選擇評價等級");
				}
				
				String hou_eva_content = req.getParameter("hou_eva_content");
				
				House_EvaluateVO heVO = new House_EvaluateVO();
				heVO.setMem_id(mem_id);
				heVO.setHou_id(hou_id);
				heVO.setHou_eva_grade(hou_eva_grade);
				heVO.setHou_eva_content(hou_eva_content);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("House_EvaluateVO", heVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/house_evaluate/add_house_evaluate.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				House_EvaluateService heSvc = new House_EvaluateService();
				heVO = heSvc.addHE(mem_id, hou_id, hou_eva_grade, hou_eva_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/house_evaluate/listAll_house_evaluate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll_house_evaluate.jsp"
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/house_evaluate/add_house_evaluate.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
