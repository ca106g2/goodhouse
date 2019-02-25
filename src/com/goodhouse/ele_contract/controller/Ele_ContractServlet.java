package com.goodhouse.ele_contract.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.contract.model.ContractService;
import com.goodhouse.contract.model.ContractVO;
import com.goodhouse.ele_contract.model.Ele_ContractService;
import com.goodhouse.ele_contract.model.Ele_ContractVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

public class Ele_ContractServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			MemService mSvc = new MemService();

			try {
				
				/*******1接收請求參數****************/
				String ele_con_id = req.getParameter("ele_con_id");
				String ele_con_idReg = "^[E]{1}[C]{1}[O]{1}[N]{1}[0-9]{6}$";
				if(ele_con_id == null || (ele_con_id.trim().length()) == 0) {
					errorMsgs.add("請輸入電子合約編號");
				}else if(!ele_con_id.trim().matches(ele_con_idReg)) {
					errorMsgs.add("電子合約編號：由 ECON + 6的數字組成");
				}
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ele_contract/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/*******2開始查詢資料***********************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
				if(ele_con_id == null) {
					errorMsgs.add("查無資料");
				}
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ele_contract/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/******3查詢完成準備轉交**************/
				req.setAttribute("eleConVO", eleConVO);
				String url = "/back/ele_contract/listOne_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ele_contract/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//用姓名查詢出該會員的所有電子合約資料
		if("getOne_For_Name".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			MemService mSvc = new MemService();
			
			try {
				/*******1接收請求參數****************/
				String mem_id = null;
				//取出名字
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
				
				System.out.println(mem_id);
				//檢查取出的會員id是否為空值
				if(mem_id == null){
					errorMsgs.add("姓名輸入錯誤");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ele_contract/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/*******2開始查詢資料***********************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
//				Ele_ContractVO eleConVO = eleConSvc.getOneEC(mem_id);
				List<Ele_ContractVO> list = new ArrayList();
				String ele_con_id = null;
				//先找出電子合約資料庫裡所有含mem_id的資料
				for(Ele_ContractVO eleConVO : eleConSvc.getAll()) {
					//把每筆所取出的資料跟使用者輸入的名字做比對
					if(mem_id.equals(eleConVO.getMem_id())) {
						//符合資料庫裡有的名字才取出會員ID
						ele_con_id = eleConVO.getEle_con_id();
						//把找到符合mem_id的電子合約資料放到list裡
						list.add((Ele_ContractVO) eleConSvc.getOneEC(ele_con_id));
					}
				}

				if(mem_id == null) {
					errorMsgs.add("查無資料");
				}
				
				System.out.println(list);
				/******3查詢完成準備轉交**************/
				
				session.setAttribute("list", list);
				String url = "/back/ele_contract/listSome_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ele_contract/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//新增
		if("select_contract".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*****1接收請求參數*****************/
				String con_id = req.getParameter("con_id");
				if(con_id == null || (con_id.trim().length() == 0) ) {
					errorMsgs.add("請選擇合約種類");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failueView = req.getRequestDispatcher("/front/ele_contract/select_contract.jsp");
					failueView.forward(req, res);
					return;
				}
				
				/******2開始查詢資料******************/
				ContractService conSvc = new ContractService();
				ContractVO conVO = conSvc.getOneCon(con_id);
				if(conVO == null) {
					errorMsgs.add("");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failueView = req.getRequestDispatcher("/front/ele_contract/select_contract.jsp");
				}
				
				/******3查詢完成準備轉交*************************/
				
				req.setAttribute("conVO", conVO);
				String url = "/front/ele_contract/add_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/select_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if(("insert").equals("action")) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/add_ele_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
	}
}
