package com.goodhouse.member.model;

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


@WebServlet("/TestMem")
public class TestMem extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemService es = new MemService();
		MemVO ev = new MemVO();
		
		File img = new File("C:/images/401840052.jpg");
		BufferedInputStream buf = new BufferedInputStream(new FileInputStream(img));
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		int a ;
		byte b[] = new byte[8192];
		while((a = buf.read(b)) != -1) {
			bao.write(b);
		}
		
//		ev = es.addMem("炒之百", Date.valueOf("1995-08-08") , "ddd555", "台北市信義路一號", "55998",282858777,985555777,"cow18@abc.com","2",bao.toByteArray(),2000,"2");
//		bao.close();
//		buf.close();
//		System.out.println(ev.getMem_name());
//		System.out.println(ev.getMem_birthday());
//		System.out.println(ev.getMem_password());
//		System.out.println(ev.getMem_address());
//		System.out.println(ev.getMem_zipcode());
//		System.out.println(ev.getMem_telephone());
//		System.out.println(ev.getMem_phone());
//		System.out.println(ev.getMem_email());
//		System.out.println(ev.getMem_status());
//		System.out.println(ev.getMem_picture());
//		System.out.println(ev.getGood_total());
//		System.out.println(ev.getMem_sex());

		
		
		
		
		
		
//		System.out.println("===================================================================");
////		
//
//		ev = es.updateMem("幹幹幹", Date.valueOf("1998-08-08") , "ddd555", "台北市信義路一號", "55998",282858777,985555777,"cow18@abc.com","2",bao.toByteArray(),2000,"2","M000000008");
////		
//		System.out.println(ev.getMem_name());
//		System.out.println(ev.getMem_birthday());
//		System.out.println(ev.getMem_password());
//		System.out.println(ev.getMem_address());
//		System.out.println(ev.getMem_zipcode());
//		System.out.println(ev.getMem_telephone());
//		System.out.println(ev.getMem_phone());
//		System.out.println(ev.getMem_email());
//		System.out.println(ev.getMem_status());
//		System.out.println(ev.getMem_picture());
//		System.out.println(ev.getGood_total());
//		System.out.println(ev.getMem_id());
		
		
//		
//		System.out.println("===================================================================");
//		
		es.deleteMem("M000000014");
		System.out.println(ev.getMem_id());
	
//		System.out.println("===================================================================");
//		
		ev = es.getOneMem("M000000009");
		
		System.out.println(ev.getMem_name());
		System.out.println(ev.getMem_birthday());
		System.out.println(ev.getMem_password());
		System.out.println(ev.getMem_address());
		System.out.println(ev.getMem_zipcode());
		System.out.println(ev.getMem_telephone());
		System.out.println(ev.getMem_phone());
		System.out.println(ev.getMem_email());
		System.out.println(ev.getMem_status());
		System.out.println(ev.getMem_picture());
		System.out.println(ev.getGood_total());
		System.out.println(ev.getMem_id());
//		
		
		List <MemVO>list = new ArrayList<MemVO>();
		list = es.getAll();
		
		for(MemVO aa:list) {
			System.out.println(aa.getMem_id());
			System.out.println(aa.getMem_name());
			System.out.println(aa.getMem_birthday());
			System.out.println(aa.getMem_password());
			System.out.println(aa.getMem_address());
			System.out.println(aa.getMem_zipcode());
			System.out.println(aa.getMem_telephone());
			System.out.println(aa.getMem_phone());
			System.out.println(aa.getMem_email());
			System.out.println(aa.getMem_status());
			System.out.println(aa.getMem_picture());
			System.out.println(aa.getGood_total());
			System.out.println(aa.getMem_sex());
			
		}

		
		
	}
}
