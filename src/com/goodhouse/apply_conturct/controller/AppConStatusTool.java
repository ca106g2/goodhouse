package com.goodhouse.apply_conturct.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="/AppConStatusTool", loadOnStartup = 1 , urlPatterns="/AppConStatusTool")
public class AppConStatusTool extends HttpServlet{
	
	public class AppConStatus {
		
		private String status_no;//狀態號碼
		private String status_name;//狀態名稱
		private String status_no_name;//狀態馬+名稱
		
		private AppConStatus(String status_no, String status_name) {
			this.status_no = status_no;
			this.status_name = status_name;
			this.status_no_name = status_no + status_name;
		}

		public String getStatus_no() {
			return status_no;
		}

		public String getStatus_name() {
			return status_name;
		}

		public String getStatus_no_name() {
			return status_no_name;
		}
		
	}
	
	public void init() throws ServletException{
		
		Map<String,AppConStatus> map = new HashMap<String,AppConStatus>();
		
		map.put("s1", new AppConStatus("s1","待處理"));
		map.put("s2", new AppConStatus("s2","已處理"));
		
		getServletContext().setAttribute("Apply_ConturctStatusMap", map);
	}
	
}
