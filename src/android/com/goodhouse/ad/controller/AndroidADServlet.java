package android.com.goodhouse.ad.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.goodhouse.ad.model.AdDAO;
import android.com.goodhouse.ad.model.AdDAO_interface;
import android.com.goodhouse.ad.model.AdVO;
import android.com.goodhouse.house.model.HouseDAO_interface;
import android.com.goodhouse.house.model.HouseJDBCDAO;
import android.com.goodhouse.house.model.HouseVO;



public class AndroidADServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		AdDAO_interface dao = new AdDAO();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}

		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		System.out.println(action);
		
		
		if("findByAD".equals(action)) {
			String lan_id = jsonObject.get("lan_id").getAsString();
			String ad_status = jsonObject.get("ad_status").getAsString();
			
			List<AdVO> adList = dao.findByAD(lan_id, ad_status);
			writeText(res,gson.toJson(adList));
		
		}
		
		
		
		
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AdDAO_interface dao = new AdDAO();
		List<AdVO> adList = dao.getAll();
		writeText(res, new Gson().toJson(adList));

	}
	
	
	
	
	
	
}
