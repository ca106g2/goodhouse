package com.goodhouse.bill.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.bill.model.BillService;
import com.goodhouse.bill.model.BillVO;
import com.goodhouse.ele_contract.model.Ele_ContractService;
import com.goodhouse.ele_contract.model.Ele_ContractVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

public class BillServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//前台帳單編號單一查詢
		if("bill_getOne_mem".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1接收請求參數******************/
				//帳單編號
				String bill_id = req.getParameter("bill_id");
				if(bill_id == null || bill_id.trim().length() == 0) {
					errorMsgs.add("帳單編號不能空白，請重新輸入");
				}
				// 如 ~ 帳單編號：20190226-B00001
				String bill_idReq = "^[0-9]{8}[-]{1}[B]{1}[0-9]{5}$";
				if(!bill_id.trim().matches(bill_idReq)) {
					errorMsgs.add("帳單編號輸入格式 ( 如：20190226-B00001，含 - 共15碼 ) 錯誤，請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/bill/mem_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/******2準備查詢***********************/
				BillService billSvc = new BillService();
				BillVO billVO = billSvc.getOneB(bill_id);
				
				if(billVO == null) {
					errorMsgs.add("查無資料");
				}
				
				/******3查詢完成準備轉交************************/
				req.setAttribute("billVO", billVO);
				String url = "/front/bill/mem_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/front/bill/mem_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//前台使用者：輸入姓名查詢該會員的所有帳單
		if("bill_getBy_mem_name".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1接收請求參數**********************/
				String mem_id = null;
				String mem_name = req.getParameter("mem_name");
				if(mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("名字不能空白，請重新輸入");
				}
				
				MemService mSvc = new MemService();
				for(MemVO mVO : mSvc.getAll()) {
					if(mem_name.equals(mVO.getMem_name())){
						mem_id = mVO.getMem_id();
					}
				}
				
				if(mem_id == null) {
					errorMsgs.add("姓名輸入錯誤或無此資料，請重新輸入");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/bill/mem_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*****2開始查詢資料*******************************/
				BillService billSvc = new BillService();
				Ele_ContractService eleConSvc = new Ele_ContractService();
				List<BillVO> list = new ArrayList();
				String ele_con_id = null;
				String bill_id = null;
				
				//利用mem_id找出該會員的電子合約
				//eleConSvc.getOneEC( billVO.getEle_con_id() ).getMem_id().equals( mSvc.getOneMem(mem_id).mem_id )
//				for(Ele_ContractVO eleConVO : eleConSvc.getAll()) {
//					//把每筆所取出的資料跟使用者做比對
//					if(mem_id.equals(eleConVO.getMem_id())) {
//						//利用找到的電子合約物件與帳單所有物件比對電子合約編號
//						for(BillVO billVO : billSvc.getAll()) {
//							//符合電子合約編號的帳單物件才取出
//							if(eleConVO.getEle_con_id().equals(billVO.getEle_con_id())) {
//								bill_id = billVO.getBill_id();
//								list.add(billVO);
//							}
//						}
//					}
//				}
				for(BillVO billVO : billSvc.getAll()) {
					//把mem_id跟電子合約的mem_id做比對取出所屬的帳單
					if( (eleConSvc.getOneEC( billVO.getEle_con_id() ).getMem_id() ).equals(mem_id) ){
						bill_id = billVO.getBill_id();
						list.add(billVO);
					}
				}
				
				if(bill_id == null) {
					errorMsgs.add("查無資料");
				}
				
				/*****3查詢完成準備轉交***************************/
				req.setAttribute("billList", list);
				String url = "/front/bill/billList_for_oneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/bill/mem_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後臺使用者：單一編號查詢
		if("back_getBy_bill_id".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1接收請求參數******************/
				//帳單編號
				String bill_id = req.getParameter("bill_id");
				if(bill_id == null || bill_id.trim().length() == 0) {
					errorMsgs.add("帳單編號不能空白，請重新輸入");
				}
				// 如 ~ 帳單編號：20190226-B00001
				String bill_idReq = "^[0-9]{8}[-]{1}[B]{1}[0-9]{5}$";
				if(!bill_id.trim().matches(bill_idReq)) {
					errorMsgs.add("帳單編號輸入格式 ( 如：20190226-B00001，含 - 共15碼 ) 錯誤，請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/bill/back_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/******2準備查詢***********************/
				BillService billSvc = new BillService();
				BillVO billVO = billSvc.getOneB(bill_id);
				
				if(billVO == null) {
					errorMsgs.add("查無資料");
				}
				
				/******3查詢完成準備轉交************************/
				req.setAttribute("billVO", billVO);
				String url = "/back/bill/back_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/bill/back_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/back/bill/back_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台使用者：單一姓名查詢
		if("back_getBy_mem_name".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1接收請求參數**********************/
				String mem_id = null;
				String mem_name = req.getParameter("mem_name");
				if(mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("名字不能空白，請重新輸入");
				}
				
				MemService mSvc = new MemService();
				for(MemVO mVO : mSvc.getAll()) {
					if(mem_name.equals(mVO.getMem_name())){
						mem_id = mVO.getMem_id();
					}
				}
				
				if(mem_id == null) {
					errorMsgs.add("姓名輸入錯誤或無此資料，請重新輸入");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/bill/back_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*****2開始查詢資料*******************************/
				BillService billSvc = new BillService();
				Ele_ContractService eleConSvc = new Ele_ContractService();
				List<BillVO> list = new ArrayList();
				String ele_con_id = null;
				String bill_id = null;
				
				//利用mem_id找出該會員的電子合約
				//eleConSvc.getOneEC( billVO.getEle_con_id() ).getMem_id().equals( mSvc.getOneMem(mem_id).mem_id )
//				for(Ele_ContractVO eleConVO : eleConSvc.getAll()) {
//					//把每筆所取出的資料跟使用者做比對
//					if(mem_id.equals(eleConVO.getMem_id())) {
//						//利用找到的電子合約物件與帳單所有物件比對電子合約編號
//						for(BillVO billVO : billSvc.getAll()) {
//							//符合電子合約編號的帳單物件才取出
//							if(eleConVO.getEle_con_id().equals(billVO.getEle_con_id())) {
//								bill_id = billVO.getBill_id();
//								list.add(billVO);
//							}
//						}
//					}
//				}
				for(BillVO billVO : billSvc.getAll()) {
					//把mem_id跟電子合約的mem_id做比對取出所屬的帳單
					if( (eleConSvc.getOneEC( billVO.getEle_con_id() ).getMem_id() ).equals(mem_id) ){
						bill_id = billVO.getBill_id();
						list.add(billVO);
					}
				}
				
				if(bill_id == null) {
					errorMsgs.add("查無資料");
				}
				
				/*****3查詢完成準備轉交***************************/
				req.setAttribute("billList", list);
				String url = "/back/bill/back_billList_for_oneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/bill/back_select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
