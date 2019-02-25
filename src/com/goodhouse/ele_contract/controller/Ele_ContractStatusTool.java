package com.goodhouse.ele_contract.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//電子合約狀態類別
@WebServlet(name="/Ele_ContractStatusTool", loadOnStartup = 1 , urlPatterns="/Ele_ContractStatusTool")
public class Ele_ContractStatusTool extends HttpServlet{
	
	public class Ele_con_status{
		
		private String status_no;//狀態號碼
		private String status_name;//狀態名稱
		private String status_no_name;//狀態馬+名稱
		
		private Ele_con_status(String status_no, String status_name) {
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
		
		List<Ele_con_status> list = new ArrayList<Ele_con_status>();
		
		list.add(new Ele_con_status("S1","合約準備中"));
		list.add(new Ele_con_status("S2","已簽約"));
		list.add(new Ele_con_status("S3","解約處理中"));
		list.add(new Ele_con_status("S4","已解約"));
		list.add(new Ele_con_status("S5","合約到期"));
		list.add(new Ele_con_status("S6","續約處理中"));
		list.add(new Ele_con_status("S7","已續約"));
		list.add(new Ele_con_status("S8","取消合約"));
		list.add(new Ele_con_status("S9","合約停用"));
		
		getServletContext().setAttribute("Ele_con_statusList", list);
	}
}
