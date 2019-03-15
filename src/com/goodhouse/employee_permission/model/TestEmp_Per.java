package com.goodhouse.employee_permission.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestEmp_Per")
public class TestEmp_Per extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Emp_PerService es = new Emp_PerService();
		Emp_PerVO ev = new Emp_PerVO();
		
//		ev = es.addEmp_Per("E000000011", "P000000011");
//		System.out.println(ev.getEmp_id());
//		System.out.println(ev.getPer_id());

////	
//		System.out.println("===================================================================");
//		

		
		
//		ev = es.updateEmp_Per("E000000011", "P000000011");
//		System.out.println(ev.getEmp_id());
//		System.out.println(ev.getPer_id());

//		
//		System.out.println("===================================================================");
////		
//		es.deleteEmp_Per("E000000011");
//		System.out.println(ev.getEmp_id());
//	
//		System.out.println("===================================================================");
//		
//		ev = es.getOneEmp_Per("E000000005");
//		System.out.println(ev.getEmp_id());
//		System.out.println(ev.getPer_id());

//		
//		
		List <Emp_PerVO>list = new ArrayList<Emp_PerVO>();
		list = es.getAll();
		for(Emp_PerVO a:list) {
			System.out.println(a.getEmp_id());
			System.out.println(a.getPer_id());

//			
	}
//		
//		
	}
}
