package com.goodhouse.appointment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.appointment.model.*;

public class AppointServlet extends HttpServlet {

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
				String str = req.getParameter("appoint_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("�п�J�w����{��s��");
				}
				// Send the use back to the form, if there were errors //git上傳註解用無意義
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String appoint_id = null;
				try {
					appoint_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�w����{��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AppointService appointSvc = new AppointService();
				AppointVO appointVO = appointSvc.getOneAppoint(appoint_id);
				if (appointVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("appointVO", appointVO); // ��Ʈw���X��AppointVO����,�s�Jreq
				String url = "/back/appoint/listOneAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRentMess.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAppoint.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String appoint_id = new String(req.getParameter("appoint_id"));
				/***************************2.�}�l�d�߸��****************************************/
				AppointService appointSvc = new AppointService();
				AppointVO appointVO = appointSvc.getOneAppoint(appoint_id);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("appointVO", appointVO);         // ��Ʈw���X��appointVO����,�s�Jreq
				String url = "/back/appoint/update_appoint_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_appoint_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/listAllAppoint.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_appoint_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String appoint_id = new String(req.getParameter("appoint_id").trim());
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!mem_id.trim().matches(mem_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("�ЪF�s��: �ФŪť�");
				} else if(!lan_id.trim().matches(lan_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ЪF�s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�Ыνs��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ыνs��: �u��O�^��r��HOU�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				
				
				String hou_app_time = req.getParameter("hou_app_time");
				String hou_app_timeReg = "^[A][0-9]{1}$";
				if (hou_app_time == null || hou_app_time.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!hou_app_time.trim().matches(hou_app_timeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��A�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				
				java.sql.Date hou_app_date = null;
				try {
					hou_app_date = java.sql.Date.valueOf(req.getParameter("hou_app_date").trim());
				} catch (IllegalArgumentException e) {
					hou_app_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String app_status = req.getParameter("app_status");
				String app_statusReg = "^[A][0-9]{1}$";
				if (app_status == null || app_status.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!app_status.trim().matches(app_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��A�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				
				String app_remind = req.getParameter("app_remind");
				String app_remindReg = "^[A][0-9]{1}$";
				if (app_remind == null || app_remind.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!app_remind.trim().matches(app_remindReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��A�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				

				AppointVO appointVO = new AppointVO();
				appointVO.setAppoint_id(appoint_id);
				appointVO.setMem_id(mem_id);
				appointVO.setLan_id(lan_id);
				appointVO.setHou_id(hou_id);
				appointVO.setHou_app_time(hou_app_time);
				appointVO.setHou_app_date(hou_app_date);
				appointVO.setApp_status(app_status);
				appointVO.setApp_remind(app_remind);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointVO", appointVO); // �t����J�榡���~��appointVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/update_appoint_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AppointService appointSvc = new AppointService();
				appointVO = appointSvc.updateAppoint(appoint_id, mem_id, lan_id, hou_id, hou_app_time, hou_app_date, app_status, app_remind);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("appointVO", appointVO); // ��Ʈwupdate���\��,���T����appointVO����,�s�Jreq
				String url = "/back/appoint/listOneAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneAppoint.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/update_appoint_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addAppoint.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!mem_id.trim().matches(mem_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("�ЪF�s��: �ФŪť�");
				} else if(!lan_id.trim().matches(lan_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ЪF�s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }

				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�Ыνs��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ыνs��: �u��O�^��r��HOU�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String hou_app_time = req.getParameter("hou_app_time");
				String hou_app_timeReg = "^[A][0-9]{1}$";
				if (hou_app_time == null || hou_app_time.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!hou_app_time.trim().matches(hou_app_timeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��A�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				
				java.sql.Date hou_app_date = null;
				try {
					hou_app_date = java.sql.Date.valueOf(req.getParameter("hou_app_date").trim());
				} catch (IllegalArgumentException e) {
					hou_app_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�w�����!");
				}
				
				String app_status = req.getParameter("app_status");
				String app_statusReg = "^[A][0-9]{1}$";
				if (app_status == null || app_status.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!app_status.trim().matches(app_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��A�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				
				String app_remind = req.getParameter("app_remind");
				String app_remindReg = "^[A][0-9]{1}$";
				if (app_remind == null || app_remind.trim().length() == 0) {
					errorMsgs.add("�w���ɬq: �ФŪť�");
				} else if(!app_remind.trim().matches(app_remindReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�w���ɬq: �u��O�^��r��A�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				
				AppointVO appointVO = new AppointVO();
				appointVO.setMem_id(mem_id);
				appointVO.setLan_id(lan_id);
				appointVO.setHou_id(hou_id);
				appointVO.setHou_app_time(hou_app_time);
				appointVO.setHou_app_date(hou_app_date);
				appointVO.setApp_status(app_status);
				appointVO.setApp_remind(app_remind);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointVO", appointVO); // �t����J�榡���~��AppointVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/appoint/addAppoint.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				AppointService appointSvc = new AppointService();
				appointVO = appointSvc.addAppoint(mem_id, lan_id, hou_id, hou_app_time, hou_app_date, app_status, app_remind);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back/appoint/listAllAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllAppoint.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/addAppoint.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllAppoint.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String appoint_id = new String(req.getParameter("appoint_id"));
				
				/***************************2.�}�l�R�����***************************************/
				AppointService appointSvc = new AppointService();
				appointSvc.deleteAppoint(appoint_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back/appoint/listAllAppoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/appoint/listAllAppoint.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
