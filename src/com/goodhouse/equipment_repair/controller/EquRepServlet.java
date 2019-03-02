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
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
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
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);//����Okey �k��O����

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String str = req.getParameter("equ_rep_id");
				if (str == null || (str.trim() ).length() == 0) {
					errorMsgs.add("�п�J�]�Ƴ��׽s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/back/equRep/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String equ_rep_id = null;
				try {
					equ_rep_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�]�Ƴ��׽s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/back/equRep/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				EquRepService equRepSvc = new EquRepService();
				EquRepVO equRepVO = equRepSvc.getOneEquRep(equ_rep_id);
				if (equRepVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/back/equRep/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("equRepVO", equRepVO); // ��Ʈw���X��equRepVO����,�s�Jreq
				String url = "/back/equRep/listOneEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEquRep.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEquRep.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String equ_rep_id = new String(req.getParameter("equ_rep_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				EquRepService equRepSvc = new EquRepService();
				EquRepVO equRepVO = equRepSvc.getOneEquRep(equ_rep_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("equRepVO", equRepVO);         // ��Ʈw���X��equRepVO����,�s�Jreq
				String url = "/back/equRep/update_equRep_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_equRep_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/listAllEquRep.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_equRep_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Part part = req.getPart("upfile");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String equ_rep_id = new String(req.getParameter("equ_rep_id").trim());
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�Ыνs��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ыνs��: �u��O�^��r��H�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
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
				
				java.sql.Date equ_rep_accetime = null;
				try {
					equ_rep_accetime = java.sql.Date.valueOf(req.getParameter("equ_rep_accetime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_accetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���ץӽФ��!");
				}
				
				String equ_rep_staff = req.getParameter("equ_rep_staff").trim();
				String equ_rep_staffReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_staff == null || equ_rep_staff.trim().length() == 0) {
					errorMsgs.add("���׼t�ӽФŪť�");
				}else if(!equ_rep_staff.trim().matches(equ_rep_staffReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���׼t��: �u��O����έ^��Ʀr , �B�����`���פ��W�L20�έ^��Ʀr�`���פ��W�L60");
	            }
				
				String equ_rep_staffphone = req.getParameter("equ_rep_staffphone").trim();
				String equ_rep_staffphoneReg = "^[0-9]{10}$";
				if (equ_rep_staffphone == null || equ_rep_staffphone.trim().length() == 0) {
					errorMsgs.add("���׼t�ӹq�ܽФŪť�");
				}else if(!equ_rep_staffphone.trim().matches(equ_rep_staffphoneReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���׼t�ӹq��: �q�ܸ��X�u��O�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				/***********************�W�ǹϤ��B�z*************************/
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = bis.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				
				EquRepService equRepSvc = new EquRepService(); 
				byte[] equ_rep_picture = baos.toByteArray();
				
				/***********************�W�ǹϤ��B�z*************************/
				
				String equ_rep_event = req.getParameter("equ_rep_event").trim();
				String equ_rep_eventReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_event == null || equ_rep_event.trim().length() == 0) {
					errorMsgs.add("���׳]�ƽФŪť�");
				}else if(!equ_rep_event.trim().matches(equ_rep_eventReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���׳]��: �u��O����έ^��Ʀr , �B�����`���פ��W�L20�έ^��Ʀr�`���פ��W�L60");
	            }
				
				String equ_rep_descri = req.getParameter("equ_rep_descri").trim();
				
				String equ_rep_status = req.getParameter("equ_rep_status").trim();
				String equ_rep_statusReg = "^[E][1-3]{1}$";
				if (equ_rep_status == null || equ_rep_status.trim().length() == 0) {
					errorMsgs.add("���ת��A�ФŪť�");
				}else if(!equ_rep_status.trim().matches(equ_rep_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���ת��A: �u��O�^��r��E�}�Y�M�Ʀr�d��1~3 , �B�`���ץ��ݬO2");
	            }
				
				java.sql.Date equ_rep_expectime = null;
				try {
					equ_rep_expectime = java.sql.Date.valueOf(req.getParameter("equ_rep_expectime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_expectime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("equRepVO", equRepVO); // �t����J�榡���~��equRepVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/equRep/update_equRep_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				equRepVO = equRepSvc.updateEquRep(equ_rep_id, hou_id, mem_id, lan_id, equ_rep_accetime, equ_rep_staff, equ_rep_staffphone, equ_rep_event, equ_rep_picture, equ_rep_descri, equ_rep_status, equ_rep_expectime, equ_rep_finish);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("equRepVO", equRepVO); // ��Ʈwupdate���\��,���T����equRepVO����,�s�Jreq
				String url = "/back/equRep/listOneEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEquRep.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				
//				e.printStackTrace();
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/update_equRep_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEquRep.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Part part = req.getPart("upfile");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String hou_id = req.getParameter("hou_id");
				String hou_idReg = "^(HOU){1}[0-9]{7}$";
				if (hou_id == null || hou_id.trim().length() == 0) {
					errorMsgs.add("�]�ƺ��׽s��: �ФŪť�");
				} else if(!hou_id.trim().matches(hou_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�]�ƺ��׽s��: �u��O�^��r��HOU�}�Y�M�Ʀr , �B�`���ץ��ݬO10");
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
				
				java.sql.Date equ_rep_accetime = null;
				try {
					equ_rep_accetime = java.sql.Date.valueOf(req.getParameter("equ_rep_accetime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_accetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���ץӽФ��!");
				}
				
				String equ_rep_staff = req.getParameter("equ_rep_staff").trim();
				String equ_rep_staffReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_staff == null || equ_rep_staff.trim().length() == 0) {
					errorMsgs.add("���׼t�ӽФŪť�");
				}else if(!equ_rep_staff.trim().matches(equ_rep_staffReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���׼t��: �u��O����έ^��Ʀr , �B�����`���פ��W�L20�έ^��Ʀr�`���פ��W�L60");
	            }
				
				String equ_rep_staffphone = req.getParameter("equ_rep_staffphone").trim();
				String equ_rep_staffphoneReg = "^[0-9]{10}$";
				if (equ_rep_staffphone == null || equ_rep_staffphone.trim().length() == 0) {
					errorMsgs.add("���׼t�ӹq�ܽФŪť�");
				}else if(!equ_rep_staffphone.trim().matches(equ_rep_staffphoneReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���׼t�ӹq��: �q�ܸ��X�u��O�Ʀr , �B�`���ץ��ݬO10");
	            }
				
				/***********************�W�ǹϤ��B�z*************************/
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = bis.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				
				EquRepService equRepSvc = new EquRepService(); 
				byte[] equ_rep_picture = baos.toByteArray();
				
				/***********************�W�ǹϤ��B�z*************************/
				
				String equ_rep_event = req.getParameter("equ_rep_event").trim();
				String equ_rep_eventReg = "^[\u0391-\uFFE5]{0,20}[A-Za-z0-9]{0,60}$";
				if (equ_rep_event == null || equ_rep_event.trim().length() == 0) {
					errorMsgs.add("���׳]�ƽФŪť�");
				}else if(!equ_rep_event.trim().matches(equ_rep_eventReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���׳]��: �u��O����έ^��Ʀr , �B�����`���פ��W�L20�έ^��Ʀr�`���פ��W�L60");
	            }
				
				String equ_rep_descri = req.getParameter("equ_rep_descri").trim();
				
				String equ_rep_status = req.getParameter("equ_rep_status").trim();
				String equ_rep_statusReg = "^[E][1-3]{1}$";
				if (equ_rep_status == null || equ_rep_status.trim().length() == 0) {
					errorMsgs.add("���ת��A�ФŪť�");
				}else if(!equ_rep_status.trim().matches(equ_rep_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���ת��A: �u��O�^��r��E�}�Y�M�Ʀr�d��1~3 , �B�`���ץ��ݬO2");
	            }
				
				
				java.sql.Date equ_rep_expectime = null;
				try {
					equ_rep_expectime = java.sql.Date.valueOf(req.getParameter("equ_rep_expectime").trim());
				} catch (IllegalArgumentException e) {
					equ_rep_expectime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("equRepVO", equRepVO); // �t����J�榡���~��equRepVO����,�]�s�Jreq
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
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/addEquRep.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEquRep.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String equ_rep_id = new String(req.getParameter("equ_rep_id"));
				
				/***************************2.�}�l�R�����***************************************/
				EquRepService equRepSvc = new EquRepService();
				equRepSvc.deleteEquRep(equ_rep_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back/equRep/listAllEquRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/equRep/listAllEquRep.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

