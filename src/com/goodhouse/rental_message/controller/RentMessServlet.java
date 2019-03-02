package com.goodhouse.rental_message.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.rental_message.model.*;

public class RentMessServlet extends HttpServlet {

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
				String str = req.getParameter("ren_mes_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("�п�J��ѰT���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String ren_mes_id = null;
				try {
					ren_mes_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("��ѰT���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RentMessService rentMessSvc = new RentMessService();
				RentMessVO rentMessVO = rentMessSvc.getOneRentMess(ren_mes_id);
				if (rentMessVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentMessVO", rentMessVO); // ��Ʈw���X��rentMessVO����,�s�Jreq
				String url = "/back/rentMess/listOneRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRentMess.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllRentMess.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String ren_mes_id = new String(req.getParameter("ren_mes_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				RentMessService rentMessSvc = new RentMessService();
				RentMessVO rentMessVO = rentMessSvc.getOneRentMess(ren_mes_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("rentMessVO", rentMessVO);         // ��Ʈw���X��rentMessVO����,�s�Jreq
				String url = "/back/rentMess/update_rentMess_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_rentMess_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/listAllRentMess.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_rentMess_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String ren_mes_id = new String(req.getParameter("ren_mes_id").trim());
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�Ыνs��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ыνs��: �u��O�^��r��HOU�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!mem_id.trim().matches(mem_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O�^��r��M�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("�ЪF�s��: �ФŪť�");
				} else if(!lan_id.trim().matches(lan_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ЪF�s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				java.sql.Date ren_mes_time = null;
				try {
					ren_mes_time = java.sql.Date.valueOf(req.getParameter("ren_mes_time").trim());
				} catch (IllegalArgumentException e) {
					ren_mes_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String ren_mes_request = req.getParameter("ren_mes_request").trim();
				if (ren_mes_request == null || ren_mes_request.trim().length() == 0) {
					errorMsgs.add("���ݰT���ФŪť�");
				}
				
				String ren_mes_response = req.getParameter("ren_mes_response").trim();
				if (ren_mes_response == null || ren_mes_response.trim().length() == 0) {
					errorMsgs.add("�^�аT���ФŪť�");
				}

				RentMessVO rentMessVO = new RentMessVO();
				rentMessVO.setRen_mes_id(ren_mes_id);
				rentMessVO.setHou_id(hou_id);
				rentMessVO.setMem_id(mem_id);
				rentMessVO.setLan_id(lan_id);
				rentMessVO.setRen_mes_time(ren_mes_time);
				rentMessVO.setRen_mes_request(ren_mes_request);
				rentMessVO.setRen_mes_response(ren_mes_response);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentMessVO", rentMessVO); // �t����J�榡���~��rentMessVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/update_rentMess_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				RentMessService rentMessSvc = new RentMessService();
				rentMessVO = rentMessSvc.updateRentMess(ren_mes_id, hou_id, mem_id, lan_id, ren_mes_time, ren_mes_request, ren_mes_response);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentMessVO", rentMessVO); // ��Ʈwupdate���\��,���T����rentMessVO����,�s�Jreq
				String url = "/back/rentMess/listOneRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneRentMess.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/update_rentMess_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addRentMess.jsp���ШD  
			
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
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!mem_id.trim().matches(mem_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O�^��r��M�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String lan_id = req.getParameter("lan_id");
				String lan_idReg = "^[L][0-9]{9}$";
				if (lan_id == null || lan_id.trim().length() == 0) {
					errorMsgs.add("�ЪF�s��: �ФŪť�");
				} else if(!lan_id.trim().matches(lan_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ЪF�s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				java.sql.Date ren_mes_time = null;
				try {
					ren_mes_time = java.sql.Date.valueOf(req.getParameter("ren_mes_time").trim());
				} catch (IllegalArgumentException e) {
					ren_mes_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String ren_mes_request = req.getParameter("ren_mes_request").trim();
				if (ren_mes_request == null || ren_mes_request.trim().length() == 0) {
					errorMsgs.add("���ݰT���ФŪť�");
				}
				
				String ren_mes_response = req.getParameter("ren_mes_response").trim();
				if (ren_mes_response == null || ren_mes_response.trim().length() == 0) {
					errorMsgs.add("�^�аT���ФŪť�");
				}
			
				
//				String deptno = new String(req.getParameter("deptno").trim());

				RentMessVO rentMessVO = new RentMessVO();
				rentMessVO.setHou_id(hou_id);
				rentMessVO.setMem_id(mem_id);
				rentMessVO.setLan_id(lan_id);
				rentMessVO.setRen_mes_time(ren_mes_time);
				rentMessVO.setRen_mes_request(ren_mes_request);
				rentMessVO.setRen_mes_response(ren_mes_response);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentMessVO", rentMessVO); // �t����J�榡���~��rentMessVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rentMess/addRentMess.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				RentMessService rentMessSvc = new RentMessService();
				rentMessVO = rentMessSvc.addRentMess(hou_id, mem_id, lan_id, ren_mes_time, ren_mes_request, ren_mes_response);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back/rentMess/listAllRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllRentMess.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/addRentMess.jsp");
				failureView.forward(req, res);
			}
		}
//		
//		
		if ("delete".equals(action)) { // �Ӧ�listAllRentMess.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String ren_mes_id = new String(req.getParameter("ren_mes_id"));
				
				/***************************2.�}�l�R�����***************************************/
				RentMessService rentMessSvc = new RentMessService();
				rentMessSvc.deleteRentMess(ren_mes_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back/rentMess/listAllRentMess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rentMess/listAllRentMess.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
//git上傳註解用無意義