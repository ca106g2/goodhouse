package com.goodhouse.landlord.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;

/**
 * Servlet implementation class TestLan
 */
@WebServlet("/TestLan")

public class TestLan extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LanService es = new LanService();
		LanVO ev = new LanVO();
		
		File img = new File("C:/images/401840052.jpg");
		BufferedInputStream buf = new BufferedInputStream(new FileInputStream(img));
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		int a ;
		byte b[] = new byte[8192];
		while((a = buf.read(b)) != -1) {
			bao.write(b);
		}
		
//		ev = es.addLan ("M000000008", "2", "8888555577779999", "1" , bao.toByteArray());
//		bao.close();
//		buf.close();
//		
//		System.out.println(ev.getMem_id());
//		System.out.println(ev.getLan_receipt());
//		System.out.println(ev.getLan_account());
//		System.out.println(ev.getLan_accountstatus());
//		System.out.println(ev.getLan_ciziten());
//		
//	
		
		
		
		
		
		
//		System.out.println("===================================================================");
////		
//
//		ev = es.updateLan("M000000007", "2", "8888555577779899", "1" , bao.toByteArray() ,"L000000007");
//		
//		System.out.println(ev.getMem_id());
//		System.out.println(ev.getLan_receipt());
//		System.out.println(ev.getLan_account());
//		System.out.println(ev.getLan_accountstatus());
//		System.out.println(ev.getLan_ciziten());
//		System.out.println(ev.getLan_id());
//		
//		
////		
////		System.out.println("===================================================================");
////		
//		es.deleteLan("L000000007");
//		System.out.println(ev.getLan_id());
//	
////		System.out.println("===================================================================");
////		
//		ev = es.getOneLan("L000000006");
//		
//		System.out.println(ev.getLan_id());
//		System.out.println(ev.getMem_id());
//		System.out.println(ev.getLan_receipt());
//		System.out.println(ev.getLan_account());
//		System.out.println(ev.getLan_accountstatus());
//		System.out.println(ev.getLan_ciziten());
//		
//
//		
		List <LanVO>list = new ArrayList<LanVO>();
		list = es.getAll();
		
		for(LanVO aa:list) {
			System.out.println(aa.getLan_id());
			System.out.println(aa.getMem_id());
			System.out.println(aa.getLan_receipt());
			System.out.println(aa.getLan_account());
			System.out.println(aa.getLan_accountstatus());
			System.out.println(aa.getLan_ciziten());
		}
	
	}
}
