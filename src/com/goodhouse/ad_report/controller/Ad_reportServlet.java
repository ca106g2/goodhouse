package com.goodhouse.ad_report.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.ad.model.AdService;
import com.goodhouse.ad.model.AdVO;
import com.goodhouse.ad_report.controller.Ad_reportStatus.Ad_Status;
import com.goodhouse.ad_report.model.Ad_reportService;
import com.goodhouse.ad_report.model.Ad_reportVO;
import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.house.model.HouseService;
import com.goodhouse.house.model.HouseVO;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

public class Ad_reportServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		MemService memSvc = new MemService();
//***********************getOne_Ad_report
		if ("getOne_Ad_report".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// ----接受請求參數並做輸入格式錯誤處理

				String str = req.getParameter("ad_rep_id");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("廣告檢舉編號錯誤");
				}

				if (!errorMsgs.isEmpty()) {// 如果有錯誤就做
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/select_page.jsp");
					// RequestDispatcher 將請求轉交給其它servlet或者jsp頁面
					failureView.forward(req, res);
					// forward 重導致res的參數
					return;// end
				}

				String ad_rep_id = null;
				try {
					ad_rep_id = str;
					System.out.println(ad_rep_id + "1");
				} catch (Exception e) {
					errorMsgs.add("輸入格式錯誤");
				}

				if (!errorMsgs.isEmpty()) {// 如果有錯誤就做
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/select_page.jsp");
					// RequestDispatcher 將請求轉交給其它servlet或者jsp頁面
					failureView.forward(req, res);
					// forward 重導致res的參數
					return;// end
				}

				// -----------------------------開始查詢資料
				Ad_reportService ad_repSvc = new Ad_reportService();
				Ad_reportVO ad_repVO = ad_repSvc.getOneAd_report(ad_rep_id);
				System.out.println(ad_repSvc.getOneAd_report(ad_rep_id));
				if (ad_repVO == null) {
					errorMsgs.add("查無此資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// -----------------------------查詢完成,準備轉交
				req.setAttribute("ad_repVO", ad_repVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/ad_report/listOneAd_rep.jsp");
				successView.forward(req, res);
				// -----------------------------其他可能的錯誤處理
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無取得此資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String ad_rep_id = new String(req.getParameter("ad_rep_id").trim());

				System.out.println(ad_rep_id + "1.是否取得ID");// --------1

				Ad_reportService ad_repSvc = new Ad_reportService();
				Ad_reportVO ad_repVO = ad_repSvc.getOneAd_report(ad_rep_id);
				System.out.println(ad_repVO + "2.是否傳入ID至VO");

				req.setAttribute("ad_repVO", ad_repVO);// --------2

				String url = "/back/ad_report/update_ad_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法修改資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/listAllAd_report.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			

			try {
				String ad_rep_id = new String(req.getParameter("ad_rep_id").trim());

				String ad_idReg = "^[a-zA-Z0-9] {10,10}$";
				String ad_id = new String(req.getParameter("ad_id").trim());
				System.out.println(ad_id);
				if (ad_id == null || ad_id.trim().length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				} else if (ad_id.trim().matches(ad_idReg)) {
					errorMsgs.add("輸入格式錯誤");
				}
				// ------------------會員姓名檢測轉換會員ID--------------------------//
				String mem_id = null;
				String mem_name_test = null;
				String mem_name = new String(req.getParameter("mem_name"));
				// String nameReg ="^[(\u4e00-\u9fa5)(a-zA-Z)]$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} // if(!mem_name.trim().matches(nameReg))
				for (MemVO mvo : memSvc.getAll()) {
					if (mvo.getMem_name().equals(mem_name))
						mem_id = mvo.getMem_id();
					mem_name_test = mem_id;
				}
				if (mem_name_test != mem_id) {
					errorMsgs.add("查無此資料");
				}

				// ---------------------END-----------------------//

				// -----------------員工姓名檢測轉換員工ID---------------------------//

//			String emp_id =null;
//			String emp_name = new String (req.getParameter("emp_name"));
//			String emp_name_test = null;
//			if(emp_name == null || emp_name.trim().length() == 0) {
//				errorMsgs.add("員工姓名格式錯誤");		
//			}
//			for(EmpVO empvo : empSvc.getAll()) {
//				if(empvo.getEmp_name().equals(emp_name)) {
//					emp_id =empvo.getEmp_id();
//					emp_name_test= emp_id;
//				}
//			}				
//			if (emp_name_test!=emp_id) {
//				errorMsgs.add("查無此資料");
//			}

				// ---------------------END-----------------------//
				String ad_rep_reason = new String(req.getParameter("ad_rep_reason"));
				String ad_rep_status = new String(req.getParameter("ad_rep_status"));
				System.out.println(ad_rep_status);
				java.sql.Date ad_rep_date = null;
				try {
					ad_rep_date = java.sql.Date.valueOf(req.getParameter("ad_rep_date"));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					ad_rep_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				System.out.println("檢查1");
				Ad_reportVO ad_repVO = new Ad_reportVO();
				ad_repVO.getAd_rep_id();
				ad_repVO.getAd_id();
				ad_repVO.getMem_id();
//			    ad_repVO.getEmp_id();
				ad_repVO.getAd_rep_status();
				ad_repVO.getAd_rep_reason();
				ad_repVO.getAd_rep_date();
				System.out.println("檢查2");
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ad_repVO", ad_repVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/ad_report/update_ad_report_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				System.out.println("檢查3");
					AdService adSvc = new AdService();
					AdVO adVO = adSvc.getOneAD(ad_id);
					
				System.out.println("檢查4");	
					List<Ad_Status> list = new ArrayList<Ad_Status>();
					
				if ("3檢舉成功".equals(ad_rep_status)) {
					String hou_id = null;
//				if (list.get(2).getRep_status_all().equals(ad_rep_status)) {
					adVO.setAd_id(adVO.getAd_id());
					adVO.setLan_id(adVO.getLan_id());
					adVO.setHou_id(adVO.getHou_id());				
					adVO.setAd_date(adVO.getAd_date());
					adVO.setAd_sort_id(adVO.getAd_sort_id());
					adVO.setAd_status("下架");
					adVO.setAd_forfree(adVO.getAd_forfree());
					adVO.setAd_statue(adVO.getAd_statue());
					adVO.setAd_paymethod(adVO.getAd_paymethod());
					adSvc.updateAd(adVO);
//------------------------房屋狀態改變start
					hou_id=adVO.getHou_id();
					HouseService houSvc = new HouseService();
					HouseVO houVO = houSvc.getOneHouse(hou_id);
					houVO.setHou_id(hou_id);
					houVO.setHou_name(houVO.getHou_name());
					houVO.setHou_type(houVO.getHou_type());
					houVO.setHou_size(houVO.getHou_size());
					houVO.setHou_property(houVO.getHou_property());
					houVO.setHou_parkspace("審核中");
					houVO.setHou_cook(houVO.getHou_cook());
					houVO.setHou_managefee(houVO.getHou_managefee());
					houVO.setHou_address(houVO.getHou_address());
					houVO.setHou_rent(houVO.getHou_rent());
					houVO.setHou_note(houVO.getHou_note());
					houVO.setHou_f_picture(houVO.getHou_f_picture());
					houVO.setHou_s_picture(houVO.getHou_s_picture());
					houVO.setHou_t_picture(houVO.getHou_t_picture());
					houSvc.update(houVO);
//------------------------房屋狀態改變end
					Ad_reportService ad_repSvc = new Ad_reportService();
					ad_repVO = ad_repSvc.updateAd_report(ad_rep_id, ad_id, mem_id, ad_rep_status, ad_rep_reason,
							ad_rep_date);
					req.setAttribute("ad_repVO", ad_repVO);
					System.out.println("廣告檢舉成功");
					RequestDispatcher successView = req.getRequestDispatcher("/back/ad_report/listOneAd_rep.jsp");
					successView.forward(req, res);
					
				} else {
					Ad_reportService ad_repSvc = new Ad_reportService();
					ad_repVO = ad_repSvc.updateAd_report(ad_rep_id, ad_id, mem_id, ad_rep_status, ad_rep_reason,
							ad_rep_date);
					req.setAttribute("ad_repVO", ad_repVO);
					RequestDispatcher successView = req.getRequestDispatcher("/back/ad_report/listOneAd_rep.jsp");
					successView.forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("更新失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/update_ad_report_input.jsp");
				failureView.forward(req, res);
			}
		}

		// ----------------

		if ("front_insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			System.out.println("check");
			try {

				String ad_rep_id = null;
				String ad_id = new String(req.getParameter("ad_id").trim());
				System.out.println(ad_id);
				// ------------------會員姓名檢測轉換會員ID--------------------------//
				String mem_id = new String(req.getParameter("mem_id"));
				System.out.println(mem_id);
//				String mem_name_test= null;
//				String mem_name= new String(req.getParameter("mem_name"));
//				//String nameReg ="^[(\u4e00-\u9fa5)(a-zA-Z)]$";
//				if(mem_name == null || mem_name.trim().length()==0) {
//					errorMsgs.add("請勿空白");
//				}// if(!mem_name.trim().matches(nameReg)) 
//				for(MemVO mvo:memSvc.getAll()) {
//					if(mvo.getMem_name().equals(mem_name)) 
//						mem_id=mvo.getMem_id();
//					mem_name_test = mem_id;
//				}
//				if ( mem_name_test!=mem_id) {
//					errorMsgs.add("查無此資料");
//				}

				// ---------------------END-----------------------//

				// -----------------員工姓名檢測轉換員工ID---------------------------//

//				String emp_id =null;
//				String emp_name = new String (req.getParameter("emp_name"));
//				String emp_name_test = null;
//				if(emp_name == null || emp_name.trim().length() == 0) {
//					errorMsgs.add("員工姓名格式錯誤");		
//				}
//				for(EmpVO empvo : empSvc.getAll()) {
//					if(empvo.getEmp_name().equals(emp_name)) {
//						emp_id =empvo.getEmp_id();
//						emp_name_test= emp_id;
//					}
//				}				
//				if (emp_name_test!=emp_id) {
//					errorMsgs.add("查無此資料");
//				}

				// ---------------------END-----------------------//

				String ad_rep_status = new String(req.getParameter("ad_rep_status"));
				System.out.println(ad_rep_status);
				String ad_rep_reason = new String(req.getParameter("ad_rep_reason").trim());
				if (ad_rep_reason == null || ad_rep_reason.trim().length() == 0) {
					errorMsgs.add("事由請勿空白");
				}
				System.out.println(ad_rep_reason);
				java.sql.Date ad_rep_date = null;
				try {
					ad_rep_date = java.sql.Date.valueOf(req.getParameter("ad_rep_date"));
				} catch (Exception e) {
					ad_rep_date = new java.sql.Date(System.currentTimeMillis());
				}
				System.out.println(ad_rep_date);
				Ad_reportVO ad_repVO = new Ad_reportVO();
				ad_repVO.setAd_id(ad_id);
				ad_repVO.setMem_id(mem_id);
				ad_repVO.setAd_rep_status(ad_rep_status);
				ad_repVO.setAd_rep_reason(ad_rep_reason);
				ad_repVO.setAd_rep_date(ad_rep_date);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ad_repVO", ad_repVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front/lin/houseBrowse.jsp");
					failureView.forward(req, res);
					return;
				}

				Ad_reportService ad_repSvc = new Ad_reportService();
				ad_repVO = ad_repSvc.addAd_report(ad_id, mem_id, ad_rep_status, ad_rep_reason, ad_rep_date);
				System.out.println("check2");
//				RequestDispatcher successView = req.getRequestDispatcher("/front/ad_report/select_page.jsp");
//				successView.forward(req, res);
				PrintWriter out = res.getWriter();
				out.println("{}");
				out.close();
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/lin/houseBrowse.jsp");
				failureView.forward(req, res);
			}
		}

//		//-----------------
//		if("insert".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				
//				String ad_rep_id = null;
//				String ad_idReg= "^[a-zA-Z0-9] {10,10}$";
//				String ad_id = new String (req.getParameter("ad_id").trim());
//				if(ad_id==null|| ad_id.trim().length()==0) {
//					errorMsgs.add("請輸入廣告編號");	
//				}else if(ad_id.trim().matches(ad_idReg)) {
//					errorMsgs.add("輸入格式錯誤");
//				}
//								
//				//------------------會員姓名檢測轉換會員ID--------------------------//
//				String mem_id=null;
//				String mem_name_test= null;
//				String mem_name= new String(req.getParameter("mem_name"));
//				//String nameReg ="^[(\u4e00-\u9fa5)(a-zA-Z)]$";
//				if(mem_name == null || mem_name.trim().length()==0) {
//					errorMsgs.add("請勿空白");
//				}// if(!mem_name.trim().matches(nameReg)) 
//				for(MemVO mvo:memSvc.getAll()) {
//					if(mvo.getMem_name().equals(mem_name)) 
//						mem_id=mvo.getMem_id();
//					mem_name_test = mem_id;
//				}
//				if ( mem_name_test!=mem_id) {
//					errorMsgs.add("查無此資料");
//				}
//				
//				//---------------------END-----------------------//
//				
//				//-----------------員工姓名檢測轉換員工ID---------------------------//
//				
////				String emp_id =null;
////				String emp_name = new String (req.getParameter("emp_name"));
////				String emp_name_test = null;
////				if(emp_name == null || emp_name.trim().length() == 0) {
////					errorMsgs.add("員工姓名格式錯誤");		
////				}
////				for(EmpVO empvo : empSvc.getAll()) {
////					if(empvo.getEmp_name().equals(emp_name)) {
////						emp_id =empvo.getEmp_id();
////						emp_name_test= emp_id;
////					}
////				}				
////				if (emp_name_test!=emp_id) {
////					errorMsgs.add("查無此資料");
////				}
//
//				//---------------------END-----------------------//
//				
//				
//				String ad_rep_reason = new String(req.getParameter("ad_rep_reason").trim());
//				if(ad_rep_reason==null||ad_rep_reason.trim().length()==0) {
//					errorMsgs.add("事由請勿空白");
//				}				
//				String ad_rep_status = new String(req.getParameter("ad_rep_status"));
//				
//				java.sql.Date ad_rep_date=null;
//				try {
//					ad_rep_date = java.sql.Date.valueOf(req.getParameter("ad_rep_date"));
//				}catch (Exception e) {
//					ad_rep_date = new java.sql.Date(System.currentTimeMillis());
//				}
//				
//				Ad_reportVO ad_repVO = new Ad_reportVO();
//				ad_repVO.setAd_id(ad_id);
//				ad_repVO.setMem_id(mem_id);
////				ad_repVO.setEmp_id(emp_id);
//				ad_repVO.setAd_rep_status(ad_rep_status);
//				ad_repVO.setAd_rep_reason(ad_rep_reason);
//				ad_repVO.setAd_rep_date(ad_rep_date);
//				
//				if(!errorMsgs.isEmpty()) {
//					req.setAttribute("ad_repVO", ad_repVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/addAd_report.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				Ad_reportService ad_repSvc = new Ad_reportService();
//				ad_repVO = ad_repSvc.addAd_report(ad_id, mem_id, ad_rep_status, ad_rep_reason, ad_rep_date);
//				RequestDispatcher successView = req.getRequestDispatcher("/back/ad_report/listAllAd_report.jsp");
//				successView.forward(req, res);
//			}catch(Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back/ad_report/addAd_report.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
