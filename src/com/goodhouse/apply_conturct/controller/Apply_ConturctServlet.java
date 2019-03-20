package com.goodhouse.apply_conturct.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.house.model.*;
import com.goodhouse.member.model.*;
import com.goodhouse.ele_contract.controller.Ele_ContractStatusTool.Ele_con_status;
import com.goodhouse.ele_contract.model.*;
import com.goodhouse.landlord.model.*;
import com.goodhouse.apply_conturct.controller.AppConStatusTool.AppConStatus;
import com.goodhouse.apply_conturct.model.*;

public class Apply_ConturctServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
//		
//		//房東查看全部的合約處理列表
//		if("lanApply_conturctListAll".equals(action)) {
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***1接收請求參數***********/
//				MemVO lanMemVO = (MemVO)session.getAttribute("mVO");
//				
//				String lanMem_id = lanMemVO.getMem_id();
//				
//				/****2開始查詢資料****************/
//				HouseService houSvc = new HouseService();
//				LanService lanSvc = new LanService();
//				MemService mSvc = new MemService();
//				//先取出房東編號
//				LanVO lanVO = lanSvc.getOneLanByMemId(lanMem_id);
//				String lan_id = lanVO.getLan_id();
//				
//				//找到該房東所屬的房屋編號
//				List<Apply_ConturctVO> applyConturctList = new ArrayList<Apply_ConturctVO>();
//				Apply_ConturctService applyConturctSvc = new Apply_ConturctService();
//				String hou_id = null;
//				for(HouseVO houVO : houSvc.getAll()) {
//					if(houVO.getLan_id().equals(lan_id)) {
//						hou_id = houVO.getHou_id();
//					}
//				}
//				
//				//找到該房屋所屬的申請合約處理編號
//				applyConturctList = applyConturctSvc.getApplyListByHou_id(hou_id);
//				
//				if(applyConturctList.isEmpty()) {
//					errorMsgs.add("無資料");
//				}
//				
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failure = req.getRequestDispatcher("lan_select_page.jsp");
//					failure.forward(req, res);
//					return;
//				}
//				
//				/****3查詢完成準備轉交****************/
//				req.getSession().setAttribute("applyConturctList", applyConturctList);
//				RequestDispatcher success = req.getRequestDispatcher("/front/ele_contract/lanListAll_Apply_conturct.jsp");
//				success.forward(req, res);
//				
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料" + e.getMessage());
//				RequestDispatcher failure = req.getRequestDispatcher("lan_select_page.jsp");
//				failure.forward(req, res);
//			}
//			
//		}
		
		//房客申請解約/續約
		if("apply_conturct".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***1接收請求參數******************/
				String ele_con_id = req.getParameter("ele_con_id");
				String appConChoose = req.getParameter("appConChoose");
				
				/***2查詢*************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
				/***3查詢完成準備轉交****************/
				req.getSession().setAttribute("eleConVO", eleConVO);
				req.getSession().setAttribute("appConChoose", appConChoose);
				RequestDispatcher success = req.getRequestDispatcher("/front/ele_contract/apply_conturct.jsp");
				success.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("mem_select_page.jsp");
				failure.forward(req, res);
			}
		}
		
		//新增申請合約處理
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
				
			try {
				/***1取得請求參數***********/
				String ele_con_id = req.getParameter("ele_con_id");
				
				String mem_id = req.getParameter("mem_id");
				
				String hou_id = req.getParameter("hou_id");
				
				String app_con_content = req.getParameter("app_con_content");
				
				String app_con_status = req.getParameter("app_con_status");
				
				String app_con_other = req.getParameter("app_con_other");
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/ele_contract/apply_conturct.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				Apply_ConturctVO appConVO = new Apply_ConturctVO();
				
				appConVO.setEle_con_id(ele_con_id);
				appConVO.setMem_id(mem_id);
				appConVO.setHou_id(hou_id);
				appConVO.setApp_con_content(app_con_content);
				appConVO.setApp_con_status(app_con_status);
				appConVO.setApp_con_other(app_con_other);
				
				/***2準備新增**************/
				Apply_ConturctService appConSvc = new Apply_ConturctService();
				appConSvc.addAppC(appConVO);
				
				//將電子合約狀態改變
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
				
				eleConVO.setEle_con_id(ele_con_id);
				eleConVO.setCon_id(eleConVO.getCon_id());
				eleConVO.setMem_id(mem_id);
				eleConVO.setMem_idnumber(eleConVO.getMem_idnumber());
				eleConVO.setLan_id(eleConVO.getLan_id());
				eleConVO.setLan_idnumber(eleConVO.getLan_idnumber());
				eleConVO.setHou_id(eleConVO.getHou_id());
				eleConVO.setEle_rent_money(eleConVO.getEle_rent_money());
				eleConVO.setEle_deposit_money(eleConVO.getEle_deposit_money());
				eleConVO.setEle_rent_time(eleConVO.getEle_rent_time());
				eleConVO.setEle_rent_f_day(eleConVO.getEle_rent_f_day());
				eleConVO.setEle_rent_l_day(eleConVO.getEle_rent_l_day());
				eleConVO.setEle_singdate(eleConVO.getEle_singdate());
				//解約
				if(app_con_content.equals("a1")) {
					List<Ele_con_status> list = (List<Ele_con_status>) getServletContext().getAttribute("Ele_con_statusList");
					eleConVO.setEle_con_status(list.get(2).getStatus_no());
					//續約
				}else if(app_con_content.equals("a2")) {
					List<Ele_con_status> list = (List<Ele_con_status>) getServletContext().getAttribute("Ele_con_statusList");
					eleConVO.setEle_con_status(list.get(5).getStatus_no());
				}
				eleConVO.setBill_paymenttype(eleConVO.getBill_paymenttype());
				eleConVO.setEle_con_note(eleConVO.getEle_con_note());
				
				eleConSvc.updateEC(eleConVO);
				
				List<Ele_ContractVO> ele_contractForMemList = eleConSvc.getAllForEle_ConByMem_id(mem_id);
				
				/***3申請完成準備轉交***************/
				req.setAttribute("lastPage", true);
				req.setAttribute("ele_contractForMemList", ele_contractForMemList);
				String url = "/front/ele_contract/mem_listAll_ele_contract.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/front/ele_contract/apply_conturct.jsp");
				failure.forward(req, res);
			}
			
		}
		
		//房東處理合約申請
		if("checkOK".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***1接收參數**************/
				String app_con_id = req.getParameter("app_con_id");
				
				/***2改變合約處理狀態*******************/
				Apply_ConturctService appConSvc = new Apply_ConturctService();
				Apply_ConturctVO appConVO = appConSvc.getOneAppC(app_con_id);
				
				appConVO.setApp_con_id(app_con_id);
				appConVO.setEle_con_id(appConVO.getEle_con_id());
				appConVO.setMem_id(appConVO.getMem_id());
				appConVO.setHou_id(appConVO.getHou_id());
				
				if(appConVO.getApp_con_status().equals("s1")) {
					Map<String,AppConStatus> map = (Map<String,AppConStatus>)getServletContext().getAttribute("Apply_ConturctStatusMap");
					appConVO.setApp_con_status(map.get("s2").getStatus_no());
				}
				appConVO.setApp_con_other("已處理");
				appConSvc.updateAppC(appConVO);
				
				//將電子合約狀態改變
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(appConVO.getEle_con_id());
				
				eleConVO.setEle_con_id(eleConVO.getEle_con_id());
				eleConVO.setCon_id(eleConVO.getCon_id());
				eleConVO.setMem_id(eleConVO.getMem_id());
				eleConVO.setMem_idnumber(eleConVO.getMem_idnumber());
				eleConVO.setLan_id(eleConVO.getLan_id());
				eleConVO.setLan_idnumber(eleConVO.getLan_idnumber());
				eleConVO.setHou_id(eleConVO.getHou_id());
				eleConVO.setEle_rent_money(eleConVO.getEle_rent_money());
				eleConVO.setEle_deposit_money(eleConVO.getEle_deposit_money());
				eleConVO.setEle_rent_time(eleConVO.getEle_rent_time());
				eleConVO.setEle_rent_f_day(eleConVO.getEle_rent_f_day());
				eleConVO.setEle_rent_l_day(eleConVO.getEle_rent_l_day());
				eleConVO.setEle_singdate(eleConVO.getEle_singdate());
//				//解約
//				if(app_con_content.equals("a1")) {
//					List<Ele_con_status> list = (List<Ele_con_status>) getServletContext().getAttribute("Ele_con_statusList");
//					eleConVO.setEle_con_status(list.get(2).getStatus_no());
//					//續約
//				}else if(app_con_content.equals("a2")) {
//					List<Ele_con_status> list = (List<Ele_con_status>) getServletContext().getAttribute("Ele_con_statusList");
//					eleConVO.setEle_con_status(list.get(5).getStatus_no());
//				}
//				
				//解約
				
				if(appConVO.getApp_con_content().equals("a1") && appConVO.getApp_con_status().equals("s2")) {
					List<Ele_con_status> list = (List<Ele_con_status>) getServletContext().getAttribute("Ele_con_statusList");
					eleConVO.setEle_con_status(list.get(3).getStatus_no());
												//續約
				}else if(appConVO.getApp_con_content().equals("a2") && appConVO.getApp_con_status().equals("s2")) {
					List<Ele_con_status> list = (List<Ele_con_status>) getServletContext().getAttribute("Ele_con_statusList");
					eleConVO.setEle_con_status(list.get(6).getStatus_no());
				}
				eleConVO.setBill_paymenttype(eleConVO.getBill_paymenttype());
				eleConVO.setEle_con_note(eleConVO.getEle_con_note());
				
				eleConSvc.updateEC(eleConVO);
				
				/***3改變完成準備轉交**************/
				//查詢所屬房東逼號
				String mem_lan_id = null;
				MemVO mVO = (MemVO)session.getAttribute("mVO");
				LanService lanSvc = new LanService();
				for(LanVO lanVO : lanSvc.getAll()) {
					if(lanVO.getMem_id().equals(mVO.getMem_id())) {
						mem_lan_id = lanVO.getLan_id();
					}
				}
				
				HouseService houSvc = new HouseService();
				String hou_id = null;
				for(HouseVO houVO : houSvc.getAll()) {
					if(houVO.getLan_id().equals(mem_lan_id)) {
						hou_id = houVO.getHou_id();
					}
				}
				
				List<Apply_ConturctVO> applyConturctList = appConSvc.getApplyListByHou_id(hou_id);
				req.getSession().setAttribute("applyConturctList", applyConturctList);
				RequestDispatcher success = req.getRequestDispatcher("/front/ele_contract/lanListAll_Apply_conturct.jsp");
				success.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/front/ele_contract/lanListAll_Apply_conturct.jsp");
				failure.forward(req, res);
			}
		}
		
		//TODO 房客確認合約
		if("eleConCheck".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/****1接收請求參數************/
				String ele_con_id = req.getParameter("ele_con_id");
				
				/****2查詢資料***************/
				Ele_ContractService eleConSvc = new Ele_ContractService();
				Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
				/****3準備轉交************/
				req.getSession().setAttribute("eleConVO", eleConVO);
				String url = "/front/bill/creatFirstBill.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failure = req.getRequestDispatcher("/front/ele_contract/memListAll_Apply_conturct.jsp");
				failure.forward(req, res);
				
			}
			
			
		}
		
		// TODO 新增 action 房客取消訂單處理
		if("eleConCancle".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1接收請求參數************/
			String ele_con_id = req.getParameter("ele_con_id");
			/****2修改合約狀態***************/
			Ele_ContractService eleConSvc = new Ele_ContractService();
			Ele_ContractVO eleConVO = eleConSvc.getOneEC(ele_con_id);
			
			eleConVO.setEle_con_id(ele_con_id);
			eleConVO.setCon_id(eleConVO.getCon_id());
			eleConVO.setMem_id(eleConVO.getMem_id());
			eleConVO.setMem_idnumber(eleConVO.getMem_idnumber());
			eleConVO.setLan_id(eleConVO.getLan_id());
			eleConVO.setLan_idnumber(eleConVO.getLan_idnumber());
			eleConVO.setHou_id(eleConVO.getHou_id());
			eleConVO.setEle_rent_money(eleConVO.getEle_rent_money());
			eleConVO.setEle_deposit_money(eleConVO.getEle_deposit_money());
			eleConVO.setEle_rent_time(eleConVO.getEle_rent_time());
			eleConVO.setEle_rent_f_day(eleConVO.getEle_rent_f_day());
			eleConVO.setEle_rent_l_day(eleConVO.getEle_rent_l_day());
			eleConVO.setEle_singdate(eleConVO.getEle_singdate());
			
			if(eleConVO.getEle_con_status().equals("s1")) {
				eleConVO.setEle_con_status("s8");
			}
			
			eleConVO.setBill_paymenttype(eleConVO.getBill_paymenttype());
			eleConVO.setEle_con_note(eleConVO.getEle_con_note());
			
			eleConSvc.updateEC(eleConVO);
			
			/******3取消成功準備跳轉*******************/
			
			req.getSession().setAttribute("eleConVO", eleConVO);
			String url = "/front/ele_contract/mem_listAll_ele_contract.jsp";
			RequestDispatcher success = req.getRequestDispatcher(url);
			success.forward(req, res);
			
			
		}
		// TODO 再加上 資料庫修改完訂單狀態後，立即發送e-mail給房東
	}
}
