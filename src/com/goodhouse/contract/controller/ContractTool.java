package com.goodhouse.contract.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//狀態工具類
@WebServlet(name="/ContractTool", loadOnStartup = 1, urlPatterns="/ContractTool")
public class ContractTool extends HttpServlet{
	
	public class Con_status{
		
		private String status_no;//狀態號碼
		private String status_name;//狀態名稱
		private String status_no_name;//狀態號碼+名稱
		
		private Con_status(String status_no, String status_name) {
			this.status_no = status_no;
			this.status_name = status_name;
			this.status_no_name = status_no + status_name;
		}
		
		public String getStatus_no_name() {
			return status_no_name;
		}

		public String getStatus_no() {
			return status_no;
		}
		public String getStatus_name() {
			return status_name;
		}
		
		
	}

	@Override
	public void init() throws ServletException {
		
		List<Con_status> list = new ArrayList<Con_status>();
		
		list.add(new Con_status("S1","啟用"));
		list.add(new Con_status("S2","停用"));
		
		getServletContext().setAttribute("Con_statusList", list);;
		
	}
	
	

}
