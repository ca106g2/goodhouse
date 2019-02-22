package com.goodhouse.permission.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;


@WebServlet("/TestPer")
public class TestPer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		PerService es = new PerService();
		PerVO ev = new PerVO();
		
//		ev = es.addPer("林長慶");
//		System.out.println(ev.getPer_name());
	
	
//		ev = es.updatePer("幹幹幹","P000000007");
//		
//		System.out.println(ev.getPer_name());
//		System.out.println(ev.getPer_id());
		
		
//		es.deletePer("P000000011");
//		System.out.println(ev.getPer_id());
		
//		ev = es.getOneMem("P000000009");
//		
//		System.out.println(ev.getPer_id());
//		System.out.println(ev.getPer_name());
//		
		List <PerVO>list = new ArrayList<PerVO>();
		list = es.getAll();
		
		for(PerVO aa:list) {
			System.out.println(aa.getPer_id());
			System.out.println(aa.getPer_name());
		}
	}
}
