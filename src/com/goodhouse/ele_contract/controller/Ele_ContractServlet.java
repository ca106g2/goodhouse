package com.goodhouse.ele_contract.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.bill.model.BillVO;
import com.goodhouse.contract.model.ContractService;
import com.goodhouse.contract.model.ContractVO;
import com.goodhouse.ele_contract.model.Ele_ContractService;
import com.goodhouse.ele_contract.model.Ele_ContractVO;
import com.goodhouse.landlord.model.LanService;
import com.goodhouse.landlord.model.LanVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;
import com.goodhouse.bill.model.*;

public class Ele_ContractServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		//TODO 測試登入
		if("test_login".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemService mSvc = new MemService();
			
			
			try {
				/**1接收請求參數*****/
				
//				String mem_name = req.getParameter("mem_name");
				String mem_email = req.getParameter("mem_email");
				
//				String mem_id = null;
				
				for(MemVO mVO : mSvc.getAll()) {
					if(mem_email.equals(mVO.getMem_email())) {
//						mem_id = mVO.getMem_id();
						req.getSession().setAttribute("memVO",mVO);
					}
				}
				/**3查詢完成準備轉交****/
				String url = "/front/ele_contract/mem_select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ele_contract/mem_select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		//TODO 後台的單一查詢
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
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
				errorMsgs.add("查無資料");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ele_contract/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//TODO 後台使用者：用姓名查詢出該會員的所有電子合約資料
		if("getNameForEle_Contract".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
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
				
				//檢查取出的會員id是否為空值
				if(mem_id == null){
					errorMsgs.add("查無資料或姓名輸入錯誤，請重新輸入");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ele_contract/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/*******2開始查詢資料***********************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				//找出電子合約資料庫裡所有含mem_id的資料
				List<Ele_ContractVO> list = eleConSvc.getAllForEle_ConByMem_id(mem_id);
				
//				//先找出電子合約資料庫裡所有含mem_id的資料
//				for(Ele_ContractVO eleConVO : eleConSvc.getAll()) {
//					//把每筆所取出的資料跟使用者輸入的名字做比對
//					if(mem_id.equals(eleConVO.getMem_id())) {
//						//符合資料庫裡有的名字才取出會員ID
//						ele_con_id = eleConVO.getEle_con_id();
//						//把找到符合mem_id的電子合約資料放到list裡
//						list.add((Ele_ContractVO) eleConSvc.getOneEC(ele_con_id));
//					}
//				}

				if(list == null) {
					errorMsgs.add("查無資料");
				}
				
				/******3查詢完成準備轉交**************/
				session.setAttribute("list", list);
				String url = "/back/ele_contract/listSome_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("查無資料" );
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ele_contract/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//TODO 前台房東新增前的查詢
		if("select_contract".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

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
				
		}
		//TODO 前台房東新增
		if(("insert").equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*****1接收請求參數******************/
				String con_id = req.getParameter("con_id");
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
				//房東
				String lan_id = req.getParameter("lan_id");;
				
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
				String rent_money = req.getParameter("ele_rent_money");
				String rent_moneyReq = "^[0-9]*$";
				if(rent_money == null || rent_money.trim().length() == 0 ) {
					errorMsgs.add("租金不能空白，請重新輸入");
				} else if(!rent_money.matches(rent_moneyReq)) {
					errorMsgs.add("租金不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_rent_money = null;
				try {
					ele_rent_money = Integer.parseInt(rent_money);
					if(ele_rent_money <= 0 ) {
						errorMsgs.add("租金不能是0或負數，請重新輸入");
					}
				} catch (NumberFormatException ne) {
					errorMsgs.add("租金不能含有字元或符號，只能是數字，請重新輸入");
				}
				
				//押金比對
				String deposit_money = req.getParameter("ele_deposit_money");
				String deposit_moneyReq = "^[0-9]*$";
				if(deposit_money == null || deposit_money.trim().length() == 0) {
					errorMsgs.add("押金不能空白，請重新輸入");
				} else if(!deposit_money.matches(deposit_moneyReq)) {
					errorMsgs.add("押金不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_deposit_money = null;
				try {
					ele_deposit_money = Integer.parseInt(deposit_money);
					if(ele_deposit_money <= 0) {
						errorMsgs.add("押金不能是0或負數，請重新輸入");
					} 
				} catch (NumberFormatException ne) {
					errorMsgs.add("押金不能含有字元或符號，只能是數字，請重新輸入");
				}
				
				//租賃期限比對
				String rent_time = req.getParameter("ele_rent_time");
				String rent_timeReq = "^[0-9]*$";
				if(rent_time == null || rent_time.trim().length() == 0) {
					errorMsgs.add("租金不能空白，請重新輸入");
				} else if(!rent_time.matches(rent_timeReq)) {
					errorMsgs.add("租賃期限不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_rent_time = null;
				try {
					ele_rent_time = Integer.parseInt(rent_time);
					if(ele_rent_time <= 0) {
						errorMsgs.add("租賃期限不能是0或負數，請重新輸入");
					}
				} catch (NumberFormatException ne) {
					errorMsgs.add("租賃期限不能含有字元或符號，只能是數字，請重新輸入");
				}
				//租賃起訖日
				Date ele_rent_f_day = null;
				
				try {
					ele_rent_f_day = Date.valueOf(req.getParameter("ele_rent_f_day"));
				} catch (IllegalArgumentException e) {
//					ele_rent_f_day = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				//租賃結束日
				Date ele_rent_l_day = null;
				
				try {
					ele_rent_l_day = Date.valueOf(req.getParameter("ele_rent_l_day"));
				} catch (IllegalArgumentException e) {
//					ele_rent_l_day = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				//簽約日期
				Date ele_singdate = null;
				
				try {
					ele_singdate = Date.valueOf(req.getParameter("ele_singdate"));
				} catch (IllegalArgumentException e) {
//					ele_singdate = new Date(System.currentTimeMillis());
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
				req.setAttribute("lastPage", true);
				
				/********新增完畢電子合約，寄e-mail通知房客**********************/
				
				String to = "rosebaby0426@gmail.com";
			      
			    String subject = "電子合約通知";
			    LanService lanSvc = new LanService();
//			    String passRandom = "慈慈測試";
			    String messageText = "Hello! " + mSvc.getOneMem(mem_id).getMem_name() + "，" + mSvc.getOneMem(lanSvc.getOneLan(lan_id).getMem_id()).getMem_name()+ "已經將合約寄給妳囉，盡快確認喔"; 
			       
			    MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
				
				/*******3新增成功，準備轉交****************************/
				List<Ele_ContractVO> ele_contractForLanList = eleConSvc.getAllForEle_ConByLan_id(lan_id);
				req.getSession().setAttribute("ele_contractForLanList", ele_contractForLanList);
				String url = "/front/ele_contract/lan_listAll_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll_ele_contract.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料錯誤");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/add_ele_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//TODO 前台房東修改前的查詢
		if("getOne_For_Update".equals(action)) {
			
				/*****1接收請求參數*************************/
				String ele_con_id = req.getParameter("ele_con_id");
				
				/*****2開始查資料*********************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
				
				/*****3查詢成功準備轉交資料************************/
				
				req.setAttribute("eleConVO", eleConVO);
				String url = "/front/ele_contract/update_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
		}
		
		//TODO 前台房東修改資料
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*****1接收請求參數*********************/
				//電子合約編號
				String ele_con_id = req.getParameter("ele_con_id");
				
				//合約分類編號
				String con_id = req.getParameter("con_id");
				
				//會員編號
				String mem_id = req.getParameter("mem_id");
				
				//會員身分證字號
				String mem_idnumber = req.getParameter("mem_idnumber");
				String mem_idnumberReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if (mem_idnumber == null || mem_idnumber.trim().length() == 0) {
					errorMsgs.add("會員身份證字號不能空白");
				} else if(!mem_idnumber.matches(mem_idnumberReg)) {
					errorMsgs.add("會員身分證字號格式(一個大寫英文字母 + 9個數字 所組成)是錯誤，請重新輸入 ");
				}
				
				//房東編號
				String lan_id = req.getParameter("lan_id");
				
				//房東身分證字號
				String lan_idnumber = req.getParameter("lan_idnumber");
				String lan_idnumberReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if(lan_idnumber == null || lan_idnumber.trim().length() == 0) {
					errorMsgs.add("房東身份證字號不能空白");
				}else if(!lan_idnumber.matches(lan_idnumberReg)) {
					errorMsgs.add("房東身分證字號格式(一個大寫英文字母 + 9個數字 所組成)是錯誤，請重新輸入 ");
				}
				
				//房屋編號
				String hou_id = req.getParameter("hou_id");
				
				//租金比對
				String rent_money = req.getParameter("ele_rent_money");
				String rent_moneyReq = "^[0-9]*$";
				if(rent_money == null || rent_money.trim().length() == 0 ) {
					errorMsgs.add("租金不能空白，請重新輸入");
				} else if(!rent_money.matches(rent_moneyReq)) {
					errorMsgs.add("租金不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_rent_money = null;
				try {
					ele_rent_money = Integer.parseInt(rent_money);
					if(ele_rent_money <= 0 ) {
						errorMsgs.add("租金不能是0或負數，請重新輸入");
					}
				} catch (NumberFormatException ne) {
					errorMsgs.add("租金不能含有字元或符號，只能是數字，請重新輸入");
				}
				
				//押金比對
				String deposit_money = req.getParameter("ele_deposit_money");
				String deposit_moneyReq = "^[0-9]*$";
				if(deposit_money == null || deposit_money.trim().length() == 0) {
					errorMsgs.add("押金不能空白，請重新輸入");
				} else if(!deposit_money.matches(deposit_moneyReq)) {
					errorMsgs.add("押金不能含有字元或符號，只能是數字，請重新輸入");
				}
				Integer ele_deposit_money = null;
				try {
					ele_deposit_money = Integer.parseInt(deposit_money);
					if(ele_deposit_money <= 0) {
						errorMsgs.add("押金不能是0或負數，請重新輸入");
					} 
				} catch (NumberFormatException ne) {
					errorMsgs.add("押金不能含有字元或符號，只能是數字，請重新輸入");
				}
				
				//租賃期限比對
				String rent_time = req.getParameter("ele_rent_time");
//				String rent_timeReq = "[0-1][0-9]";
				
				if(rent_time == null || rent_time.trim().length() == 0) {
					errorMsgs.add("租賃期限不能空白，請重新輸入");
				} 
//				else if(!rent_time.matches(rent_timeReq)) {
//					errorMsgs.add("租賃期限不能含有字元或符號，只能是數字，請重新輸入");
//				}
				
				Integer ele_rent_time = null;
				
				try {
					
					if(rent_time != null) {
						rent_time = rent_time.trim();
					}
					ele_rent_time = Integer.parseInt(rent_time);
					if(ele_rent_time <= 0) {
						errorMsgs.add("租賃期限不能是0或負數，請重新輸入");
					}
				} catch (NumberFormatException ne) {
					errorMsgs.add("租賃期限只能是數字，請重新輸入");
				}
			
				//租賃起訖日
				Date ele_rent_f_day = null;
				String ele_rent_f_day_str = req.getParameter("ele_rent_f_day");
				try {
					if(ele_rent_f_day_str != null) {
						ele_rent_f_day = Date.valueOf(ele_rent_f_day_str);
					}
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
				System.out.println("bill_paymenttype = "+bill_paymenttype);
				
				//合約備註
				String ele_con_note = req.getParameter("ele_con_note");
				
				Ele_ContractVO eleConVO = new Ele_ContractVO();
				
				eleConVO.setEle_con_id(ele_con_id);
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
				
				req.setAttribute("eleConVO", eleConVO); 
				if (!errorMsgs.isEmpty()) {
					// 含有輸入格式錯誤的eleConVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/ele_contract/update_ele_contract.jsp");
					failureView.forward(req, res);
					return;
				}
				/*******2開始新增資料**************************************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				eleConSvc.updateEC(eleConVO);
				req.setAttribute("lastPage", true);
				
				/*******3新增成功，準備轉交****************************/
				String url = "/front/ele_contract/lan_listAll_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				// 新增成功後轉交lan_listAll_ele_contract.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/update_ele_contract.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//TODO 房東的單一查詢
		if("getOne_front".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1接收請求參數************************/
				String ele_con_id = req.getParameter("ele_con_id");
				
				if(ele_con_id == null || ele_con_id.trim().length() == 0) {
					errorMsgs.add("請輸入電子合約編號(ECON + 6個數字)");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/ele_contract/lan_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/****2開始查詢**********************/
				MemVO mVO = (MemVO)session.getAttribute("mVO");
				LanService lanSvc = new LanService();
				String lan_id = lanSvc.getOneLanByMemId(mVO.getMem_id()).getLan_id();
				
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
				
				if(eleConVO == null) {
					errorMsgs.add("查無資料");
				}else if(!eleConVO.getLan_id().equals(lan_id)) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/ele_contract/lan_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*****3查詢完成準備轉交********************************/
				req.setAttribute("eleConVO", eleConVO);
				String url = "/front/ele_contract/lan_listOne_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("查無資料" );
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/lan_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//TODO 房東輸入房客姓名取得該房東的所有電子合約列表
		if("lanGetMemEle_ContractByName".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*******1接收請求參數****************/
				String mem_id = null;
				//取出名字
				String mem_name = req.getParameter("mem_name");
				//檢查房東有沒有輸入資料
				System.out.println("mem_name" + mem_name);
				if(mem_name == null || mem_name.trim().length() == 0 ) {
					errorMsgs.add("姓名請勿空白");
				}
				MemService mSvc = new MemService();
				//利用service的getAll方法取出所有會員的資料
				for(MemVO mVO : mSvc.getAll()) {
					//把每筆所取出的資料跟房東輸入的名字做比對
					if(mem_name.equals(mVO.getMem_name())) {
						//符合資料庫裡有的名字才取出會員ID
						mem_id = mVO.getMem_id();
					}
				}
				//檢查取出的會員id是否為空值
				if(mem_id == null){
					errorMsgs.add("姓名輸入錯誤或此人不是本網站會員，請重新輸入");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/ele_contract/lan_select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/*******2開始查詢資料***********************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				List<Ele_ContractVO> list = new ArrayList();
				
				//從session取出已登入的該房東會員名稱
				MemVO lanMemVO = (MemVO)session.getAttribute("mVO");
				LanService lanSvc = new LanService();
				String lan_id = lanSvc.getOneLanByMemId(lanMemVO.getMem_id()).getLan_id();
				
				String ele_con_id = null;
				//找出電子合約資料庫裡所有含mem_id和lan_id的資料
				for(Ele_ContractVO eleConVO : eleConSvc.getAll()) {
					if( lan_id.equals(eleConVO.getLan_id()) && mem_id.equals(eleConVO.getMem_id())) {
						ele_con_id = eleConVO.getEle_con_id();
						list.add((Ele_ContractVO) eleConSvc.getOneEC(ele_con_id));
					}
				}
				
				if(list.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/ele_contract/lan_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/******3查詢完成準備轉交**************/
				req.setAttribute("lastPage", true);
				req.setAttribute("list", list);
				String url = "/front/ele_contract/lan_listSomeMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("查無資料");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ele_contract/lan_select_page.jsp");
				failureView.forward(req, res);
			}
		
		
		}
		//TODO 房東查資料
		if("getOne_For_look".equals(action)) {
			
			/*****1接收請求參數*************************/
			String ele_con_id = req.getParameter("ele_con_id");
			
			/*****2開始查資料*********************/
			Ele_ContractService eleConSvc = new Ele_ContractService();
			Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
			
			/*****3查詢成功準備轉交資料************************/
			
			req.setAttribute("eleConVO", eleConVO);
			String url = "/front/ele_contract/lan_listOne_ele_contract.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
	}
	
	
	//TODO 寄出e-mail通知
	public class MailService {
		
		// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
				
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";//寄件者自己的帳號
		     final String myGmail_password = "BBB45678";//寄件者自己的密碼
		     javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
	}
}
