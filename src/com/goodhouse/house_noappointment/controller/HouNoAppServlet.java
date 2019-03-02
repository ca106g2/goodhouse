package com.goodhouse.house_noappointment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.house_noappointment.model.*;

public class HouNoAppServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
	
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);//����Okey �k��O����

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("hou_noapp_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("�п�J�ЫΤ��i�w���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/houNoApp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String hou_noapp_id = null;
				try {
					hou_noapp_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�ЫΤ��i�w���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/houNoApp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				HouNoAppVO houNoAppVO = houNoAppSvc.getOneHouNoApp(hou_noapp_id);
				if (houNoAppVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/houNoApp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("houNoAppVO", houNoAppVO); // ��Ʈw���X��houNoAppVO����,�s�Jreq
				String url = "/back/houNoApp/listOneHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRentMess.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/houNoApp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllHouNoApp.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String hou_noapp_id = new String(req.getParameter("hou_noapp_id"));
				/***************************2.�}�l�d�߸��****************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				HouNoAppVO houNoAppVO = houNoAppSvc.getOneHouNoApp(hou_noapp_id);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("houNoAppVO", houNoAppVO);         // ��Ʈw���X��houNoAppVO����,�s�Jreq
				String url = "/back/houNoApp/update_houNoApp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_rentMess_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/houNoApp/listAllHouNoApp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_houNoApp_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String hou_noapp_id = new String(req.getParameter("hou_noapp_id").trim());
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�Ыνs��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ыνs��: �u��O�^��r��HOU�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("�ЪF�s��: �ФŪť�");
				} else if(!lan_id.trim().matches(lan_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ЪF�s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String hou_noapp_time = req.getParameter("hou_noapp_time");
				String hou_noapp_timeReg = "^[H][1-3]{1}$";
				if (hou_noapp_time == null || hou_noapp_time.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!hou_noapp_time.trim().matches(hou_noapp_timeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��H�}�Y�M�Ʀr�d��1~3 , �B�`���ץ��ݬO2");
	            }
				
				java.sql.Date hou_noapp_date = null;
				try {
					hou_noapp_date = java.sql.Date.valueOf(req.getParameter("hou_noapp_date").trim());
				} catch (IllegalArgumentException e) {
					hou_noapp_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				

				HouNoAppVO houNoAppVO = new HouNoAppVO();
				houNoAppVO.setHou_noapp_id(hou_noapp_id);
				houNoAppVO.setHou_id(hou_id);
				houNoAppVO.setLan_id(lan_id);
				houNoAppVO.setHou_noapp_time(hou_noapp_time);
				houNoAppVO.setHou_noapp_date(hou_noapp_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("houNoAppVO", houNoAppVO); // �t����J�榡���~��houNoAppVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/houNoApp/update_houNoApp_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				houNoAppVO = houNoAppSvc.updateHouNoApp(hou_noapp_id, hou_id, lan_id, hou_noapp_time, hou_noapp_date);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("houNoAppVO", houNoAppVO); // ��Ʈwupdate���\��,���T����houNoAppVO����,�s�Jreq
				String url = "/back/houNoApp/listOneHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneHouNoApp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/houNoApp/update_houNoApp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addHouNoApp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�Ыνs��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ыνs��: �u��O�^��r��HOU�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("�ЪF�s��: �ФŪť�");
				} else if(!lan_id.trim().matches(lan_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ЪF�s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				
				String hou_noapp_time = req.getParameter("hou_noapp_time");
				String hou_noapp_timeReg = "^[H][1-3]{1}$";
				if (hou_noapp_time == null || hou_noapp_time.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!hou_noapp_time.trim().matches(hou_noapp_timeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��H�}�Y�M�Ʀr�d��1~3 , �B�`���ץ��ݬO2");
	            }
				
				java.sql.Date hou_noapp_date = null;
				try {
					hou_noapp_date = java.sql.Date.valueOf(req.getParameter("hou_noapp_date").trim());
				} catch (IllegalArgumentException e) {
					hou_noapp_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				HouNoAppVO houNoAppVO = new HouNoAppVO();
				houNoAppVO.setHou_id(hou_id);
				houNoAppVO.setLan_id(lan_id);
				houNoAppVO.setHou_noapp_time(hou_noapp_time);
				houNoAppVO.setHou_noapp_date(hou_noapp_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("houNoAppVO", houNoAppVO); // �t����J�榡���~��houNoAppVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/houNoApp/addHouNoApp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				houNoAppVO = houNoAppSvc.addHouNoApp(hou_id, lan_id, hou_noapp_time, hou_noapp_date);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back/houNoApp/listAllHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllRentMess.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/houNoApp/addHouNoApp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllHouNoApp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String hou_noapp_id = new String(req.getParameter("hou_noapp_id"));
				
				/***************************2.�}�l�R�����***************************************/
				HouNoAppService houNoAppSvc = new HouNoAppService();
				houNoAppSvc.deleteHouNoApp(hou_noapp_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back/houNoApp/listAllHouNoApp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/houNoApp/listAllHouNoApp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
