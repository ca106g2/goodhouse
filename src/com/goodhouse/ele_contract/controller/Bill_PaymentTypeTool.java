	package com.goodhouse.ele_contract.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.goodhouse.apply_conturct.controller.AppConChooseTool.AppConChoose;

@WebServlet(name="/Bill_PaymentTypeTool" , loadOnStartup = 1 , urlPatterns="/Bill_PaymentTypeTool")
public class Bill_PaymentTypeTool extends HttpServlet{
	
	public class Bill_PaymentType{
		
		private String type_no;
		private String type_name;
		private String type_no_name;
		
		public String getType_no() {
			return type_no;
		}

		public String getType_name() {
			return type_name;
		}

		public String getType_no_name() {
			return type_no_name;
		}

		private Bill_PaymentType(String type_no, String type_name) {
			this.type_no = type_no;
			this.type_name = type_name;
			this.type_no_name = type_no + type_name;
		}
	}
	
	public void init() throws ServletException{
		
		Map<String,Bill_PaymentType> map = new HashMap<String,Bill_PaymentType>();
		
		map.put("p1", new Bill_PaymentType("p1","月繳"));
		map.put("p2", new Bill_PaymentType("p2","季繳"));
		map.put("p3", new Bill_PaymentType("p3","半年繳"));
		map.put("p4", new Bill_PaymentType("p4","年繳"));
		getServletContext().setAttribute("Bill_PaymentTypeMap", map);
	}
	
}
