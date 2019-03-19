package com.goodhouse.ad.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.goodhouse.ad.model.AdService;
import com.goodhouse.ad.model.AdVO;


public class AdServlet extends HttpServlet{
	
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if("back_ad_search".equals(action)) {
			
			List<String> errorMsgs  = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		

			try {
			String ad_id = req.getParameter("ad_id");
			if(ad_id==null||ad_id.trim().length()==0) {
				errorMsgs.add("廣告收尋請勿空白");
			}
			
			
			AdService adSvc = new AdService();
			AdVO adVO = adSvc.getOneAD(ad_id);
			if(ad_id == null) {
				errorMsgs.add("查無此資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("adVO", adVO);
			RequestDispatcher successView = req.getRequestDispatcher("/back/ad/listOneAd.jsp");
			successView.forward(req,res);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("我是48行的exception");
				errorMsgs.add("無法得資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***********************1.接收請求參數*************************/
				String ad_id=req.getParameter("ad_id");
				/***********************2.開始查詢資料*************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAD(ad_id);
				/***********************3.查詢完成,準備轉交*************************/
				req.setAttribute("adVO", adVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/ad/update_ad_input.jsp");
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("資料修改取得失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);

			try {
				String ad_id=req.getParameter("ad_id").trim();
				String lan_id=req.getParameter("lan_id").trim();
				String hou_id=req.getParameter("hou_id").trim();
				String ad_sort_id = req.getParameter("ad_sort_id").trim();
				String ad_forfree = req.getParameter("ad_forfree").trim();
				if(ad_forfree==null || ad_forfree.trim().length()==0) {
					errorMsgs.add("廣告備註請勿空白");
				}
				String ad_statue= req.getParameter("ad_statue").trim();
				String ad_paymethod = req.getParameter("ad_paymethod").trim();
				String ad_status = req.getParameter("ad_status").trim();
				java.sql.Date ad_date = null;
				try {
					ad_date = java.sql.Date.valueOf(req.getParameter("ad_date").trim());
				}catch(IllegalArgumentException e) {
					ad_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}
				
				/**********************4存放位子*********************/
				AdVO adVO = new AdVO();
				adVO.setAd_id(ad_id);
				adVO.setLan_id(lan_id);
				adVO.setHou_id(hou_id);
				adVO.setAd_date(ad_date);
				adVO.setAd_sort_id(ad_sort_id);
				adVO.setAd_status(ad_status);
				adVO.setAd_forfree(ad_forfree);
				adVO.setAd_statue(ad_statue);
				adVO.setAd_paymethod(ad_paymethod);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ad/update_ad_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				AdService adSvc = new AdService();
				adVO = adSvc.updateAd(ad_id, lan_id, hou_id, ad_date, ad_sort_id, ad_status, ad_forfree, ad_statue, ad_paymethod);
				
				
				if("上架".equals(ad_status)) {
					
					String mem_id = new String(req.getParameter("mem_id").trim());
					com.goodhouse.member.model.MemService memSvc = new com.goodhouse.member.model.MemService();
					com.goodhouse.member.model.MemVO memVO = memSvc.getOneMem(mem_id);
					Integer good_total = memVO.getGood_total();
					good_total = good_total + 10000;
					memSvc.updatePointTot(mem_id, good_total);
					
					
					
				}else {
					
					req.setAttribute("adVO", adVO);
					RequestDispatcher successView = req.getRequestDispatcher("/back/ad/listOneAd.jsp");
					successView.forward(req, res);
				}
				
				
				
				req.setAttribute("adVO", adVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/ad/listOneAd.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("更新失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad/update_ad_input.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String lan_id =  req.getParameter("lan_id").trim();
				String lan_idReg ="^[(a-zA-Z0-9_)]{9,10}$";
				if(lan_id==null||lan_id.trim().length()==0) {
					errorMsgs.add("請輸入房東姓名");
				} else if(!lan_id.trim().matches(lan_idReg)) {
					errorMsgs.add("查無此房東");
				}
				String hou_id = req.getParameter("hou_id").trim();
				String hou_idReg ="^[(a-zA-Z0-9_)]{9,10}$";
				if(hou_id==null||hou_id.trim().length()==0) {
					errorMsgs.add("請輸入房屋名稱");
				} else if(!hou_id.trim().matches(hou_idReg)) {
					errorMsgs.add("                       ");
				}
				String ad_sort_id = req.getParameter("ad_sort_id").trim();
				String ad_sort_idReg ="^[(a-zA-Z0-9_)]{9,10}$";
				if(ad_sort_id==null||ad_sort_id.trim().length()==0) {
					errorMsgs.add("請輸入房東姓名");
				} else if(!ad_sort_id.trim().matches(ad_sort_idReg)) {
					errorMsgs.add("查無此房東");
				}
				String ad_status = req.getParameter("ad_status").trim();
				String ad_statue = req.getParameter("ad_statue").trim();
				String ad_paymethod = req.getParameter("ad_paymethod").trim();
				String ad_forfree = req.getParameter("ad_forfree").trim();
				if(ad_forfree == null || ad_forfree.trim().length()==0) {
					errorMsgs.add("房屋備註不可空白");
				}
				java.sql.Date ad_date = null;
				try {
					ad_date = java.sql.Date.valueOf(req.getParameter("ad_date").trim());
				}catch(IllegalArgumentException e) {
					ad_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("輸入日期");
				}
				AdVO adVO = new AdVO();
				adVO.setLan_id(lan_id);
				adVO.setHou_id(hou_id);
				adVO.setAd_date(ad_date);
				adVO.setAd_sort_id(ad_sort_id);
				adVO.setAd_status(ad_status);
				adVO.setAd_forfree(ad_forfree);
				adVO.setAd_statue(ad_statue);
				adVO.setAd_paymethod(ad_paymethod);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ad/addAd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				AdService adSvc = new AdService();
				adVO =adSvc.addAd(lan_id, hou_id, ad_date, ad_sort_id, ad_status, ad_forfree, ad_statue, ad_paymethod);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/ad/listAllAd.jsp");
				successView.forward(req, res);
				
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("203:新增失敗"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/back/ad/addAd.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		//*********複合式查詢

		if("listAd_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			HttpSession session = req.getSession();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				Map<String, String[]> map = req.getParameterMap();
				System.out.println("220"+map);
				AdService adSvc = new AdService();

				List<AdVO> list = adSvc.getAll(map);
				System.out.println("224"+list);


				session.setAttribute("list", list);
				
				RequestDispatcher successView =
						req.getRequestDispatcher("/back/ad/listAd_ByCompositeQuery.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/back/ad/select_page.jsp");
				failureView.forward(req, res);	
				
			}
		}
		
	//*******fornt新增
		if("front_insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String lan_id =  req.getParameter("lan_id").trim();
				String lan_idReg ="^[(a-zA-Z0-9_)]{9,10}$";
				if(lan_id==null||lan_id.trim().length()==0) {
					errorMsgs.add("請輸入房東姓名");
				} else if(!lan_id.trim().matches(lan_idReg)) {
					errorMsgs.add("查無此房東");
				}
				String hou_id = req.getParameter("hou_id").trim();
				String hou_idReg ="^[(a-zA-Z0-9_)]{9,10}$";
				if(hou_id==null||hou_id.trim().length()==0) {
					errorMsgs.add("請輸入房屋名稱");
				} else if(!hou_id.trim().matches(hou_idReg)) {
					errorMsgs.add("                       ");
				}
				String ad_sort_id = req.getParameter("ad_sort_id").trim();
				String ad_sort_idReg ="^[(a-zA-Z0-9_)]{9,10}$";
				if(ad_sort_id==null||ad_sort_id.trim().length()==0) {
					errorMsgs.add("請輸入房東姓名");
				} else if(!ad_sort_id.trim().matches(ad_sort_idReg)) {
					errorMsgs.add("查無此房東");
				}
				String ad_status = req.getParameter("ad_status").trim();
				String ad_statue = req.getParameter("ad_statue").trim();
				String ad_paymethod = req.getParameter("ad_paymethod").trim();
				String ad_forfree = req.getParameter("ad_forfree").trim();
				if(ad_forfree == null || ad_forfree.trim().length()==0) {
					errorMsgs.add("房屋備註不可空白");
				}
				java.sql.Date ad_date = null;
				try {
					ad_date = java.sql.Date.valueOf(req.getParameter("ad_date").trim());
				}catch(IllegalArgumentException e) {
					ad_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("輸入日期");
				}
				AdVO adVO = new AdVO();
				adVO.setLan_id(lan_id);
				adVO.setHou_id(hou_id);
				adVO.setAd_date(ad_date);
				adVO.setAd_sort_id(ad_sort_id);
				adVO.setAd_status(ad_status);
				adVO.setAd_forfree(ad_forfree);
				adVO.setAd_statue(ad_statue);
				adVO.setAd_paymethod(ad_paymethod);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front/ad/addAd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				AdService adSvc = new AdService();
				adVO =adSvc.addAd(lan_id, hou_id, ad_date, ad_sort_id, ad_status, ad_forfree, ad_statue, ad_paymethod);
				
				RequestDispatcher successView = req.getRequestDispatcher("/front/ad/select_page.jsp");
				successView.forward(req, res);
				
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("203:新增失敗"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/front/ad/addAd.jsp");
				failureView.forward(req, res);
			}
			
		}
					
	}
}
