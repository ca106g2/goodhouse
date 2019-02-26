package com.goodhouse.ele_contract.controller;

import java.io.IOException;
import java.sql.Date;
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
import com.goodhouse.landlord.model.LanService;
import com.goodhouse.landlord.model.LanVO;
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
		
		//後台使用者：用姓名查詢出該會員的所有電子合約資料
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
		
		//新增前的查詢
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
					errorMsgs.add("請選擇合約種類");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failueView = req.getRequestDispatcher("/front/ele_contract/select_contract.jsp");
				}
				
				/******3查詢完成準備轉交*************************/
				
				session.setAttribute("conVO", conVO);
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
		//新增

		if(("insert").equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/*****1接收請求參數******************/
				String con_id = req.getParameter("con_id");
				System.out.println(con_id);
				
				String mem_name = req.getParameter("mem_name");
				String mem_id = null;
				if(mem_name == null || mem_name.trim().length()== 0) {
					errorMsgs.add("租屋者姓名請勿空白");
				}
				
				//查詢會員資料庫資料
				MemService mSvc = new MemService();
				for(MemVO mVO : mSvc.getAll()) {
					if(mem_name.equals(mVO.getMem_name())) {
						mem_id = mVO.getMem_id();
					}
				}
				
				//檢查會員id是否為空直
				if(mem_id == null) {
					errorMsgs.add("姓名輸入錯誤 或 無此會員，請重新輸入");
				}

				String mem_idnumber = req.getParameter("mem_idnumber");
				//比對輸入的身分證字號格式是錯誤
				String mem_idnumberReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if (mem_idnumber == null || mem_idnumber.trim().length() == 0) {
					errorMsgs.add("會員身份證字號不能空白");
				} else if(!mem_idnumber.matches(mem_idnumberReg)) {
					errorMsgs.add("會員身分證字號格式(一個大寫英文字母 + 9個數字 所組成)是錯誤，請重新輸入 ");
				}
				
				//比對房東姓名
				String lan_id = null;
				String lan_name = req.getParameter("lan_name");
				if(lan_name == null || lan_name.trim().length() == 0) {
					errorMsgs.add("房東姓名不能空白");
				}
				
				MemService mSvc1 = new MemService();
				LanService lanSvc = new LanService();
				//利用房東姓名去比對是否為會員
				for(MemVO mVO : mSvc1.getAll()) {
					if(mVO.getMem_name().equals(lan_name)) {
						mem_id = mVO.getMem_id();
						//利用取出的會員id來去比對是否為房東
						for(LanVO lanVO : lanSvc.getAll()) {
							if(lanVO.getMem_id().equals(mem_id)) {
								lan_id = lanVO.getLan_id();
							}
						}
					}
				}
				
				if(lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("姓名輸入錯誤(與會員姓名一致)");
				}
				
				//比對房東身分證字號
				String lan_idnumber = req.getParameter("lan_idnumber");
				String lan_idnumberReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if(lan_idnumber == null || lan_idnumber.trim().length() == 0) {
					errorMsgs.add("房東身份證字號不能空白");
				}else if(!lan_idnumber.matches(lan_idnumberReg)) {
					errorMsgs.add("房東身分證字號格式(一個大寫英文字母 + 9個數字 所組成)是錯誤，請重新輸入 ");
				}
				
				//比對房屋資料
				String hou_id = req.getParameter("hou_id");
				
				//租金比對
				String rent_money = req.getParameter("ele_rent_money").trim();
				String rent_moneyReq = "^[0-9]*$";
				if(!rent_money.matches(rent_moneyReq)) {
					errorMsgs.add("租金不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_rent_money = Integer.parseInt(rent_money);
				if(ele_rent_money <= 0 ) {
					errorMsgs.add("租金不能空白、0或負數，請重新輸入");
				}
				
				//押金比對
				String deposit_money = req.getParameter("ele_deposit_money");
				String deposit_moneyReq = "^[0-9]*$";
				if(!deposit_money.matches(deposit_moneyReq)) {
					errorMsgs.add("租金不能含有字元或符號，只能是數字，請重新輸入");
				} 
				Integer ele_deposit_money = Integer.parseInt(deposit_money);
				if(ele_deposit_money <= 0) {
					errorMsgs.add("押金不能空白、0或負數，請重新輸入");
				} 
				
				//租賃期限比對
				String rent_time = req.getParameter("ele_rent_time");
				String rent_timeReq = "^[0-9]*$";
				if(!rent_time.matches(rent_timeReq)) {
					errorMsgs.add("租賃期限不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_rent_time = Integer.parseInt(rent_time);
				if(ele_rent_time <= 0) {
					errorMsgs.add("租賃期限不能空白、0或負數，請重新輸入");
				} 
				
				//租賃起訖日
				Date ele_rent_f_day = null;
				
				try {
					ele_rent_f_day = Date.valueOf(req.getParameter("ele_rent_f_day"));
				} catch (IllegalArgumentException e) {
					ele_rent_f_day = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				//租賃結束日
				
				Date ele_rent_l_day = null;
				
				try {
					ele_rent_l_day = Date.valueOf(req.getParameter("ele_rent_l_day"));
				} catch (IllegalArgumentException e) {
					ele_rent_l_day = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				//簽約日期
				Date ele_singdate = null;
				
				try {
					ele_singdate = Date.valueOf(req.getParameter("ele_singdate"));
				} catch (IllegalArgumentException e) {
					ele_singdate = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				//合約狀態
				String ele_con_status = req.getParameter("ele_con_status");
				
				//繳費型態
				String bill_paymenttype = req.getParameter("bill_paymenttype");
				
				//合約備註
				String ele_con_note = req.getParameter("ele_con_note");
				
				Ele_ContractVO eleConVO = new Ele_ContractVO();
				
				eleConVO.setCon_id(con_id);
				eleConVO.setMem_id(mem_id);
				eleConVO.setMem_idnumber(mem_idnumber);
				eleConVO.setLan_id(lan_id);
				eleConVO.setLan_idnumber(lan_idnumber);
				eleConVO.setHou_id(hou_id);
				eleConVO.setEle_rent_money(ele_rent_money);
				eleConVO.setEle_deposit_money(ele_deposit_money);
				eleConVO.setEle_rent_time(ele_rent_time);
				eleConVO.setEle_rent_f_day(ele_rent_f_day);
				eleConVO.setEle_rent_l_day(ele_rent_l_day);
				eleConVO.setEle_singdate(ele_singdate);
				eleConVO.setEle_con_status(ele_con_status);
				eleConVO.setBill_paymenttype(bill_paymenttype);
				eleConVO.setEle_con_note(ele_con_note);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eleConVO", eleConVO); // 含有輸入格式錯誤的eleConVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/ele_contract/add_ele_contract.jsp");
					failureView.forward(req, res);
					return;
				}
				/*******2開始新增資料**************************************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				eleConSvc.addEC(eleConVO);
				
				/*******3新增成功，準備轉交****************************/
				String url = "/front/ele_contract/listAll_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll_ele_contract.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/add_ele_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
	}
}
