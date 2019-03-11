package com.goodhouse.ad_sort.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.goodhouse.ad_sort.model.*;

public class Ad_sortServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		String action= req.getParameter("action");
		
		if("getOne_For_Ad_sort".equals(action)) {
			List<String>  errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			String ad_sort_id = req.getParameter("ad_sort_id");
			if(ad_sort_id == null || ad_sort_id.trim().length()==0) {
				errorMsgs.add("請選擇廣告類別");
			}
			Ad_sortService ad_sortSvc = new Ad_sortService();
			Ad_sortVO ad_sortVO = ad_sortSvc.getOneAd_sort(ad_sort_id);
			if (ad_sortVO == null) {
				errorMsgs.add("查無此資料");
			}

			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_sort/select_pate.jsp");
				failureView.forward(req, res);
				return;
			}
			
			req.setAttribute("ad_sortVO", ad_sortVO);
			RequestDispatcher successView = req.getRequestDispatcher("/back/ad_sort/listOneAd_sort.jsp");
			successView.forward(req, res);
			
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("查無此資料"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_sort/select_pate.jsp");
				failureView.forward(req, res);
			}

		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
			String ad_sort_id = new String(req.getParameter("ad_sort_id"));
			Ad_sortService ad_sortSvc = new Ad_sortService();
			Ad_sortVO ad_sortVO = ad_sortSvc.getOneAd_sort(ad_sort_id);

			req.setAttribute("ad_sortVO", ad_sortVO);
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back/ad_sort/update_ad_sort_update.jsp");
			successView.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料無法取得"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
//			try {
				String ad_sort_id = req.getParameter("ad_sort_id").trim();

				String ad_typeReg = "^[(\u4e00-\u9fa5)]";
				String ad_forfree = req.getParameter("ad_forfree").trim();
				if(ad_forfree == null || ad_forfree.trim().length()==0) {
					errorMsgs.add("請誤空白");
				}else if(ad_forfree.trim().matches(ad_typeReg)) {
					errorMsgs.add("請輸入中文");
				}
				String ad_chargetype = req.getParameter("ad_chargetype").trim();
				if(ad_chargetype == null || ad_chargetype.trim().length()==0) {
					errorMsgs.add("請誤空白");
				}else if(ad_chargetype.trim().matches(ad_typeReg)) {
					errorMsgs.add("請輸入中文");
				}
				Integer ad_charge = new Integer(req.getParameter("ad_charge").trim());

				System.out.println(ad_charge);

				Ad_sortVO ad_sortVO = new Ad_sortVO();
				ad_sortVO.setAd_sort_id(ad_sort_id);
				ad_sortVO.setAd_forfree(ad_forfree);
				ad_sortVO.setAd_chargetype(ad_chargetype);
				ad_sortVO.setAd_charge(ad_charge);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("ad_sortVO", ad_sortVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back/ad_sort/update_ad_sort_update.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Ad_sortService ad_sortSvc = new Ad_sortService();
				ad_sortVO = ad_sortSvc.updateAd_sort(ad_sort_id, ad_forfree, ad_chargetype, ad_charge);
				
				req.setAttribute("ad_sortVO",ad_sortVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/ad_sort/listOneAd_sort.jsp");
				successView.forward(req, res);
				
//			}catch(Exception e) {
//				e.printStackTrace();
//				errorMsgs.add("資料無法修改"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//				}
			}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				String ad_sort_id = req.getParameter("ad_sort_id").trim();

				String ad_typeReg = "^[(\u4e00-\u9fa5)]";
				String ad_forfree = req.getParameter("ad_forfree").trim();
				if(ad_forfree == null || ad_forfree.trim().length()==0) {
					errorMsgs.add("請誤空白");
				}else if(ad_forfree.trim().matches(ad_typeReg)) {
					errorMsgs.add("請輸入中文");
				}
				String ad_chargetype = req.getParameter("ad_chargetype").trim();
				if(ad_chargetype == null || ad_chargetype.trim().length()==0) {
					errorMsgs.add("請誤空白");
				}else if(ad_chargetype.trim().matches(ad_typeReg)) {
					errorMsgs.add("請輸入中文");
				}
				Integer ad_charge = new Integer(req.getParameter("ad_charge").trim());
				
				Ad_sortVO ad_sortVO = new Ad_sortVO();
				ad_sortVO.setAd_forfree(ad_forfree);
				ad_sortVO.setAd_chargetype(ad_chargetype);
				ad_sortVO.setAd_charge(ad_charge);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("ad_sortVO", ad_sortVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/back/ad_sort/addAd_sort.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Ad_sortService ad_sortSvc = new Ad_sortService();
				ad_sortVO = ad_sortSvc.addAd_sort(ad_forfree, ad_chargetype, ad_charge);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/ad_sort/listAllAd_sort.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料無法新增"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_sort/addAd_sort.jsp");
				failureView.forward(req, res);
				
			}
			
		}
			
	}
}
