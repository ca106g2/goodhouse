package com.goodhouse.house_track.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.house_track.model.House_TrackService;
import com.goodhouse.house_track.model.House_TrackVO;

public class House_TrackServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		//TODO 新增追蹤
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***1接收請求參數********/
				String mem_id = req.getParameter("mem_id");
				String hou_id = req.getParameter("hou_id");
				/****2準備新增**********/
				House_TrackService houTraSvc = new House_TrackService();
				House_TrackVO houTraVO = new House_TrackVO();
				
				houTraVO.setMem_id(mem_id);
				houTraVO.setHou_id(hou_id);
				houTraSvc.addHT(houTraVO);
				
				/****3新增完畢***********/
				PrintWriter out = res.getWriter();
				out.println("{}");
				out.close();
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/house_track/mem_house_track_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		//TODO 刪除追蹤
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****1接收請求參數*********/
				String mem_id = req.getParameter("mem_id");
				String hou_id = req.getParameter("hou_id");
				
				/*****2準備刪除****************/
				House_TrackService houTraSvc = new House_TrackService();
				House_TrackVO houTraVO = houTraSvc.findByHouIdAndMem_id(hou_id, mem_id);
				houTraSvc.deleteHT(houTraVO.getHou_tra_id());
				
				/*****3刪除完畢********/
				PrintWriter out = res.getWriter();
				out.println("{}");
				out.close();
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/house_track/mem_house_track_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
