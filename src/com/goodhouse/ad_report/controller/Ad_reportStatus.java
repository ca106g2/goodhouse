package com.goodhouse.ad_report.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//啟動順序
@WebServlet(name="/Ad_reportStatus", loadOnStartup = 1, urlPatterns="/Ad_reportStatus")
//狀態工具類
public class Ad_reportStatus extends HttpServlet{
	public class Ad_Status{
		
		private String rep_status_type;
		private String rep_status_number;
		private String rep_status_all;
		public Ad_Status(String rep_status_number,String rep_status_type) {
			this.rep_status_type = rep_status_type;
			this.rep_status_number = rep_status_number;
			this.rep_status_all = rep_status_number + rep_status_type ;
		}
		public String getRep_status_type() {
			return rep_status_type;
		}
		public String getRep_status_number() {
			return rep_status_number;
		}
		public String getRep_status_all() {
			return rep_status_all;
		}

	}
	
	@Override
	public void init() throws ServletException{
		
		List<Ad_Status> list = new ArrayList<Ad_Status>();
		
		list.add(new Ad_Status("1","檢舉審核中"));
		list.add(new Ad_Status("2","檢舉失敗"));
		list.add(new Ad_Status("3","檢舉成功"));
		
		getServletContext().setAttribute("Ad_statusList", list);
	}
} 
