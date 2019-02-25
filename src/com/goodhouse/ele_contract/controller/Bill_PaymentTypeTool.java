package com.goodhouse.ele_contract.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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
		
		List<Bill_PaymentType> list = new ArrayList<Bill_PaymentType>();
		
		list.add(new Bill_PaymentType("P1","月繳"));
		list.add(new Bill_PaymentType("P2","季繳"));
		list.add(new Bill_PaymentType("P3","半年繳"));
		list.add(new Bill_PaymentType("P4","年繳"));
		
		getServletContext().setAttribute("Bill_PaymentTypeList", list);
	}
	
}
