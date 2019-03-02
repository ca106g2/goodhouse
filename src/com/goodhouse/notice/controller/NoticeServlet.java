package com.goodhouse.notice.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.notice.model.*;

public class NoticeServlet extends HttpServlet {

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
				String str = req.getParameter("notice_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("�п�J�q���ƶ��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String notice_id = null;
				try {
					notice_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�q���ƶ��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				NoticeService  noticeSvc = new NoticeService();
				NoticeVO noticeVO = noticeSvc.getOneNotice(notice_id);
				if (noticeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("noticeVO", noticeVO); // ��Ʈw���X��NoticeVO����,�s�Jreq
				String url = "/back/notice/listOneNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRentMess.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllNotice.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String notice_id = new String(req.getParameter("notice_id"));
				/***************************2.�}�l�d�߸��****************************************/
				NoticeService noticeSvc = new NoticeService();
				NoticeVO noticeVO = noticeSvc.getOneNotice(notice_id);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("noticeVO", noticeVO);         // ��Ʈw���X��noticeVO����,�s�Jreq
				String url = "/back/notice/update_notice_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_notice_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/listAllNotice.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_notice_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String notice_id = new String(req.getParameter("notice_id").trim());
				
				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[M][0-9]{9}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!mem_id.trim().matches(mem_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O�^��r��L�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				String notice_message = req.getParameter("notice_message");
				if (notice_message == null || notice_message.trim().length() == 0) {
					errorMsgs.add("�q�����e: �ФŪť�");
				}
				
				String notice_status = req.getParameter("notice_status");
				String notice_statusReg = "^[N][0-9]{1}$";
				if (notice_status == null || notice_status.trim().length() == 0) {
					errorMsgs.add("�q�����A: �ФŪť�");
				}else if(!notice_status.trim().matches(notice_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�����A: �u��O�^��r��N�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }

				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setNotice_id(notice_id);
				noticeVO.setMem_id(mem_id);
				noticeVO.setNotice_message(notice_message);
				noticeVO.setNotice_status(notice_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noticeVO", noticeVO); // �t����J�榡���~��noticeVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/update_notice_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				NoticeService noticeSvc = new NoticeService();
				noticeVO = noticeSvc.updateNotice(notice_id, mem_id, notice_message, notice_status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("noticeVO", noticeVO); // ��Ʈwupdate���\��,���T����noticeVO����,�s�Jreq
				String url = "/back/notice/listOneNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneNotice.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/update_notice_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addNotice.jsp���ШD  
			
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
				
				
				String notice_message = req.getParameter("notice_message");
				if (notice_message == null || notice_message.trim().length() == 0) {
					errorMsgs.add("�q�����e: �ФŪť�");
				}
				
				String notice_status = req.getParameter("notice_status");
				String notice_statusReg = "^[N][0-9]{1}$";
				if (notice_status == null || notice_status.trim().length() == 0) {
					errorMsgs.add("�q�����A: �ФŪť�");
				}else if(!notice_status.trim().matches(notice_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�����A: �u��O�^��r��N�}�Y�M�Ʀr , �B�`���ץ��ݬO2");
	            }
				
				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setMem_id(mem_id);
				noticeVO.setNotice_message(notice_message);
				noticeVO.setNotice_status(notice_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noticeVO", noticeVO); // �t����J�榡���~��NoticeVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/notice/addNotice.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				NoticeService noticeSvc = new NoticeService();
				noticeVO = noticeSvc.addNotice(mem_id, notice_message, notice_status);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back/notice/listAllNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllNotice.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/addNotice.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllNotice.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String notice_id = new String(req.getParameter("notice_id"));
				
				/***************************2.�}�l�R�����***************************************/
				NoticeService noticeSvc = new NoticeService();
				noticeSvc.deleteNotice(notice_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back/notice/listAllNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/notice/listAllNotice.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
//git上傳註解用無意義