package com.goodhouse.apply_conturct.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="/AppConChooseTool", loadOnStartup = 1 , urlPatterns="/AppConChooseTool")
public class AppConChooseTool extends HttpServlet{
	
	public class AppConChoose{
		
		private String choose_no;
		private String choose_name;
		private String choose_no_name;
		
		private AppConChoose(String choose_no,String choose_name) {
			this.choose_no = choose_no;
			this.choose_name = choose_name;
			this.choose_no_name = choose_no + choose_name;
		}

		public String getChoose_no() {
			return choose_no;
		}

		public String getChoose_name() {
			return choose_name;
		}

		public String getChoose_no_name() {
			return choose_no_name;
		}
		
		
	}
	
	public void init() throws ServletException{
		
		Map<String,AppConChoose> map = new HashMap<String,AppConChoose>();
		
		map.put("a1", new AppConChoose("a1","解約"));
		map.put("a2", new AppConChoose("a2","續約"));
		map.put("a3", new AppConChoose("a3","租金延期"));
		
		getServletContext().setAttribute("Apply_ConturctChooseMap", map);
	}
	
}
