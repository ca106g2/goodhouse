package android.com.goodhouse.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.goodhouse.member.model.MemDAO;
import android.com.goodhouse.member.model.MemDAO_interface;
import android.com.goodhouse.member.model.MemVO;





public class AndroidMemberServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("dd-MMM-yy").create();
		//Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MemDAO_interface memDAOI = new MemDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		
		if (action.equals("isMember")) {
			String mem_email = jsonObject.get("mem_email").getAsString();
			String mem_password = jsonObject.get("mem_password").getAsString();
			
			writeText(res,	String.valueOf(memDAOI.isMember(mem_email, mem_password)));
		}else if (action.equals("isUserIdExist")) {
			String mem_email= jsonObject.get("mem_email").getAsString();
			writeText(res, String.valueOf(memDAOI.isUserIdExist(mem_email)));
		} else if (action.equals("insert")) {
			MemVO memVO = gson.fromJson(jsonObject.get("member").getAsString(), MemVO.class);
			writeText(res, String.valueOf( memDAOI.insert(memVO)));
		}else if (action.equals("findByEmail")) {
			String mem_email = jsonObject.get("mem_email").getAsString();
			MemVO memVO = memDAOI.findByEmail(mem_email);
			writeText(res, memVO == null ? "" : gson.toJson(memVO));
		}else if (action.equals("update")) {
			MemVO memVO = gson.fromJson(jsonObject.get("memVO").getAsString(), MemVO.class);
			writeText(res, String.valueOf(memDAOI.update(memVO)));
		}else if (action.equals("findByEmail")) {
			String mem_email = jsonObject.get("mem_email").getAsString();
			MemVO memVO = memDAOI.findByEmail(mem_email);
			writeText(res, memVO == null? "" : gson.toJson(memVO));
			
			
			
//			String mem_email = jsonObject.get("mem_email").getAsString();
//			MemVO memVO = memDAOI.findByPrimaryKey("mem_email");
//			writeText(res, mem_email == null ? "" : gson.toJson(memVO));
		};
	
	
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}


	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
	

}
