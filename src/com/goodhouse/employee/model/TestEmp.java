package com.goodhouse.employee.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestEmp")
public class TestEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpService es = new EmpService();
		EmpVO ev = new EmpVO();
		
//		ev = es.addEmp("幹", 987528666 , "ABC87", "123456", "2");
//		System.out.println(ev.getEmp_name());
//		System.out.println(ev.getEmp_phone());
//		System.out.println(ev.getEmp_account());
//		System.out.println(ev.getEmp_password());
//		System.out.println(ev.getEmp_status());
////	
//		System.out.println("===================================================================");
//		

		
		
//		ev = es.updateEmp("E000000006", "幹幹", 987528666 , "ABC887", "123456", "2");
//		System.out.println(ev.getEmp_id());
//		System.out.println(ev.getEmp_name());
//		System.out.println(ev.getEmp_phone());
//		System.out.println(ev.getEmp_account());
//		System.out.println(ev.getEmp_password());
//		System.out.println(ev.getEmp_status());
//		
//		System.out.println("===================================================================");
////		
//		es.deleteEmp("E000000011");
//		System.out.println(ev.getEmp_id());
//	
//		System.out.println("===================================================================");
//		
//		ev = es.getOneEmp("E000000005");
//		System.out.println(ev.getEmp_id());
//		System.out.println(ev.getEmp_name());
//		System.out.println(ev.getEmp_phone());
//		System.out.println(ev.getEmp_account());
//		System.out.println(ev.getEmp_password());
//		System.out.println(ev.getEmp_status());
//		
//		
		List <EmpVO>list = new ArrayList<EmpVO>();
		list = es.getAll();
		for(EmpVO a:list) {
			System.out.println(a.getEmp_id());
			System.out.println(a.getEmp_name());
			System.out.println(a.getEmp_phone());
			System.out.println(a.getEmp_account());
			System.out.println(a.getEmp_password());
			System.out.println(a.getEmp_status());
//			
	}
//		
//		
	}
}
