package android.com.goodhouse.house_noappointment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
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

import android.com.goodhouse.house.model.HouseDAO_interface;
import android.com.goodhouse.house.model.HouseJDBCDAO;
import android.com.goodhouse.house.model.HouseVO;
import android.com.goodhouse.house_noappointment.model.HouNoAppDAO;
import android.com.goodhouse.house_noappointment.model.HouNoAppDAO_interface;
import android.com.goodhouse.house_noappointment.model.HouNoAppVO;
import android.com.goodhouse.maim.ImageUtil;

public class AndroidHouNoAppServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		HouNoAppDAO_interface dao = new HouNoAppDAO();
//		Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("dd-MMM-yy").create();
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

		
		if("findhoucanapp".equals(action)) {
			String hou_id = jsonObject.get("hou_id").getAsString();
			String lan_id = jsonObject.get("lan_id").getAsString();
			System.out.println(hou_id+lan_id);
			List<HouNoAppVO> appList =dao.findhoucanapp(hou_id, lan_id);
	
			writeText(res,gson.toJson(appList));
			
			
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
		HouseDAO_interface dao = new HouseJDBCDAO();
		List<HouseVO> houseList = dao.getAll();
		writeText(res, new Gson().toJson(houseList));

	}


}
