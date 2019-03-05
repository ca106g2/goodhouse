package com.goodhouse.equipment_repair.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.goodhouse.equipment_repair.model.EquRepService;
import com.goodhouse.equipment_repair.model.EquRepVO;
@MultipartConfig
public class EquRepServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String equ_rep_id = req.getParameter("equ_rep_id").trim();
			EquRepService equRepSvc = new EquRepService();
			EquRepVO equRepVO = equRepSvc.getOneEquRep(equ_rep_id);
			byte[] picture = equRepVO.getEqu_rep_picture();
			if (picture != null) {
			out.write(picture);
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);//git上傳註解用無意義
				InputStream in = getServletContext().getResourceAsStream("/back/NoData/no.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
		} catch (Exception e) {
			//System.out.println(e);
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/back/NoData/null2.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
	
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);//左邊是key 右邊是物件

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("equ_rep_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("請輸入設備報修編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/back/equRep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String equ_rep_id = null;
				try {
					equ_rep_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("設備報修編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/back/equRep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EquRepService equRepSvc = new EquRepService();
				EquRepVO equRepVO = equRepSvc.getOneEquRep(equ_rep_id);
				if (equRepVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/back/equRep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("equRepVO", equRepVO); // 資料庫取出的equRepVO物件,存入req
				String url = "/back/equRep/listOneEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEquRep.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEquRep.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String equ_rep_id = new String(req.getParameter("equ_rep_id"));
				
				/***************************2.開始查詢資料****************************************/
				EquRepService equRepSvc = new EquRepService();
				EquRepVO equRepVO = equRepSvc.getOneEquRep(equ_rep_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("equRepVO", equRepVO);         // 資料庫取出的equRepVO物件,存入req
				String url = "/back/equRep/update_equRep_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_equRep_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/listAllEquRep.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_equRep_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Part part = req.getPart("upfile");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String equ_rep_id = new String(req.getParameter("equ_rep_id").trim());
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母H開頭和數字 , 且總長度必需是10");
	            }
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母M開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				java.sql.Date equ_rep_accetime = null;
				try {
					equ_rep_accetime = java.sql.Date.valueOf(req.getParameter("equ_rep_accetime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_accetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入維修申請日期!");
				}
				
				String equ_rep_staff = req.getParameter("equ_rep_staff").trim();
				String equ_rep_staffReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_staff == null || equ_rep_staff.trim().length() == 0) {
					errorMsgs.add("維修廠商請勿空白");
				}else if(!equ_rep_staff.trim().matches(equ_rep_staffReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修廠商: 只能是中文或英文數字 , 且中文總長度不超過20或英文數字總長度不超過60");
	            }
				
				String equ_rep_staffphone = req.getParameter("equ_rep_staffphone").trim();
				String equ_rep_staffphoneReg = "^[0-9]{10}$";
				if (equ_rep_staffphone == null || equ_rep_staffphone.trim().length() == 0) {
					errorMsgs.add("維修廠商電話請勿空白");
				}else if(!equ_rep_staffphone.trim().matches(equ_rep_staffphoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修廠商電話: 電話號碼只能是數字 , 且總長度必需是10");
	            }
				
				/***********************上傳圖片處理*************************/
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = bis.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				
				EquRepService equRepSvc = new EquRepService(); 
				byte[] equ_rep_picture = baos.toByteArray();
				
				/***********************上傳圖片處理*************************/
				
				String equ_rep_event = req.getParameter("equ_rep_event").trim();
				String equ_rep_eventReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_event == null || equ_rep_event.trim().length() == 0) {
					errorMsgs.add("維修設備請勿空白");
				}else if(!equ_rep_event.trim().matches(equ_rep_eventReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修設備: 只能是中文或英文數字 , 且中文總長度不超過20或英文數字總長度不超過60");
	            }
				
				String equ_rep_descri = req.getParameter("equ_rep_descri").trim();
				
				String equ_rep_status = req.getParameter("equ_rep_status").trim();
				String equ_rep_statusReg = "^[E][1-3]{1}$";
				if (equ_rep_status == null || equ_rep_status.trim().length() == 0) {
					errorMsgs.add("維修狀態請勿空白");
				}else if(!equ_rep_status.trim().matches(equ_rep_statusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修狀態: 只能是英文字母E開頭和數字範圍1~3 , 且總長度必需是2");
	            }
				
				java.sql.Date equ_rep_expectime = null;
				try {
					equ_rep_expectime = java.sql.Date.valueOf(req.getParameter("equ_rep_expectime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_expectime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date equ_rep_finish = java.sql.Date.valueOf(req.getParameter("equ_rep_finish").trim());;
				
				
				EquRepVO equRepVO = new EquRepVO();
				equRepVO.setEqu_rep_id(equ_rep_id);
				equRepVO.setHou_id(hou_id);
				equRepVO.setMem_id(mem_id);
				equRepVO.setLan_id(lan_id);
				equRepVO.setEqu_rep_accetime(equ_rep_accetime);
				equRepVO.setEqu_rep_staff(equ_rep_staff);
				equRepVO.setEqu_rep_staffphone(equ_rep_staffphone);
				equRepVO.setEqu_rep_event(equ_rep_event);
				equRepVO.setEqu_rep_picture(equ_rep_picture);
				equRepVO.setEqu_rep_descri(equ_rep_descri);
				equRepVO.setEqu_rep_status(equ_rep_status);
				equRepVO.setEqu_rep_expectime(equ_rep_expectime);
				equRepVO.setEqu_rep_finish(equ_rep_finish);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("equRepVO", equRepVO); // 含有輸入格式錯誤的equRepVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/equRep/update_equRep_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				equRepVO = equRepSvc.updateEquRep(equ_rep_id, hou_id, mem_id, lan_id, equ_rep_accetime, equ_rep_staff, equ_rep_staffphone, equ_rep_event, equ_rep_picture, equ_rep_descri, equ_rep_status, equ_rep_expectime, equ_rep_finish);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("equRepVO", equRepVO); // 資料庫update成功後,正確的的equRepVO物件,存入req
				String url = "/back/equRep/listOneEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEquRep.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
//				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/update_equRep_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEquRep.jsp的請求 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Part part = req.getPart("upfile");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("房屋編號: 請勿空白");
				} else if(!hou_id.trim().matches(hou_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房屋編號: 只能是英文字母HOU開頭和數字 , 且總長度必需是10");
	            }
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母M開頭和數字 , 且總長度必需是10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("房東編號: 請勿空白");
				} else if(!lan_id.trim().matches(lan_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房東編號: 只能是英文字母L開頭和數字 , 且總長度必需是10");
	            }
				
				java.sql.Date equ_rep_accetime = null;
				try {
					equ_rep_accetime = java.sql.Date.valueOf(req.getParameter("equ_rep_accetime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_accetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入維修申請日期!");
				}
				
				String equ_rep_staff = req.getParameter("equ_rep_staff").trim();
				String equ_rep_staffReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_staff == null || equ_rep_staff.trim().length() == 0) {
					errorMsgs.add("維修廠商請勿空白");
				}else if(!equ_rep_staff.trim().matches(equ_rep_staffReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修廠商: 只能是中文或英文數字 , 且中文總長度不超過20或英文數字總長度不超過60");
	            }
				
				String equ_rep_staffphone = req.getParameter("equ_rep_staffphone").trim();
				String equ_rep_staffphoneReg = "^[0-9]{10}$";
				if (equ_rep_staffphone == null || equ_rep_staffphone.trim().length() == 0) {
					errorMsgs.add("維修廠商電話請勿空白");
				}else if(!equ_rep_staffphone.trim().matches(equ_rep_staffphoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修廠商電話: 電話號碼只能是數字 , 且總長度必需是10");
	            }
				
				/***********************上傳圖片處理*************************/
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = bis.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				
				EquRepService equRepSvc = new EquRepService(); 
				byte[] equ_rep_picture = baos.toByteArray();
				
				/***********************上傳圖片處理*************************/
				
				String equ_rep_event = req.getParameter("equ_rep_event").trim();
				String equ_rep_eventReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_event == null || equ_rep_event.trim().length() == 0) {
					errorMsgs.add("維修設備請勿空白");
				}else if(!equ_rep_event.trim().matches(equ_rep_eventReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修設備: 只能是中文或英文數字 , 且中文總長度不超過20或英文數字總長度不超過60");
	            }
				
				String equ_rep_descri = req.getParameter("equ_rep_descri").trim();
				
				String equ_rep_status = req.getParameter("equ_rep_status").trim();
				String equ_rep_statusReg = "^[E][1-3]{1}$";
				if (equ_rep_status == null || equ_rep_status.trim().length() == 0) {
					errorMsgs.add("維修狀態請勿空白");
				}else if(!equ_rep_status.trim().matches(equ_rep_statusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("維修狀態: 只能是英文字母E開頭和數字範圍1~3 , 且總長度必需是2");
	            }
				
				
				java.sql.Date equ_rep_expectime = null;
				try {
					equ_rep_expectime = java.sql.Date.valueOf(req.getParameter("equ_rep_expectime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_expectime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date equ_rep_finish = java.sql.Date.valueOf(req.getParameter("equ_rep_finish").trim());;
				
				EquRepVO equRepVO = new EquRepVO();
				equRepVO.setHou_id(hou_id);
				equRepVO.setMem_id(mem_id);
				equRepVO.setLan_id(lan_id);
				equRepVO.setEqu_rep_accetime(equ_rep_accetime);
				equRepVO.setEqu_rep_staff(equ_rep_staff);
				equRepVO.setEqu_rep_staffphone(equ_rep_staffphone);
				equRepVO.setEqu_rep_event(equ_rep_event);
				equRepVO.setEqu_rep_picture(equ_rep_picture);
				equRepVO.setEqu_rep_descri(equ_rep_descri);
				equRepVO.setEqu_rep_status(equ_rep_status);
				equRepVO.setEqu_rep_expectime(equ_rep_expectime);
				equRepVO.setEqu_rep_finish(equ_rep_finish);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("equRepVO", equRepVO); // 含有輸入格式錯誤的equRepVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/equRep/addEquRep.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				equRepVO = equRepSvc.addEquRep(hou_id, mem_id, lan_id, equ_rep_accetime, equ_rep_staff, equ_rep_staffphone, equ_rep_event, equ_rep_picture, equ_rep_descri, equ_rep_status, equ_rep_expectime, equ_rep_finish);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back/equRep/listAllEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEquRep.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/addEquRep.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEquRep.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String equ_rep_id = new String(req.getParameter("equ_rep_id"));
				
				/***************************2.開始刪除資料***************************************/
				EquRepService equRepSvc = new EquRepService();
				equRepSvc.deleteEquRep(equ_rep_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/equRep/listAllEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/listAllEquRep.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

