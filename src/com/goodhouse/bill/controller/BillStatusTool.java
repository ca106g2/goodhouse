package com.goodhouse.bill.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//帳單繳費狀態列
@WebServlet(name="/BillStatusTool", loadOnStartup = 1 , urlPatterns="/BillStatusTool")
public class BillStatusTool extends HttpServlet{
	
	public class BillStatus{
		
		private String status_no;
		private String status_name;
		private String Status_no_name;
		
		private BillStatus(String status_no,String status_name) {
			this.status_no = status_no;
			this.status_name = status_name;
			this.Status_no_name = status_no + status_name;
		}

		public String getStatus_no() {
			return status_no;
		}

		public String getStatus_name() {
			return status_name;
		}

		public String getStatus_no_name() {
			return Status_no_name;
		}
		
	}
	
	public void init() throws ServletException{
		
		List<BillStatus> list = new ArrayList<BillStatus>();
		
		list.add(new BillStatus("s1","待確認待繳款"));
		list.add(new BillStatus("s2","待繳款"));
		list.add(new BillStatus("s3","已繳款待撥款"));
		list.add(new BillStatus("s4","已繳款已撥款"));
		list.add(new BillStatus("s5","延期待繳款"));
		list.add(new BillStatus("s6","逾期未繳款"));
		
		getServletContext().setAttribute("BillStatusList", list);
	}
}	
