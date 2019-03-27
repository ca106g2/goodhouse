package android.com.goodhouse.landlord.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.goodhouse.landlord.model.LanDAO;
import android.com.goodhouse.landlord.model.LanDAO_interface;
import android.com.goodhouse.landlord.model.LanVO;


public class AndroidLanServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		LanDAO_interface lanDao = new LanDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
	
	
		if (action.equals("isLandlord")) {
			String mem_id = jsonObject.get("mem_id").getAsString();
			writeText(res,	String.valueOf(lanDao.isLandlord(mem_id)));
		}
		if(action.equals("findByMemid")) {
			String mem_id = jsonObject.get("mem_id").getAsString();
			LanVO lanVO = lanDao.findByMemid(mem_id);
			writeText(res,lanVO == null ? "": gson.toJson(lanVO));
		}
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
	
	
	

}
