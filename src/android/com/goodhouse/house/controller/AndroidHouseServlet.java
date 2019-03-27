package android.com.goodhouse.house.controller;

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
import com.google.gson.JsonObject;

import android.com.goodhouse.house.model.HouseDAO_interface;
import android.com.goodhouse.house.model.HouseJDBCDAO;
import android.com.goodhouse.house.model.HouseVO;
import android.com.goodhouse.maim.ImageUtil;

public class AndroidHouseServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		HouseDAO_interface dao = new HouseJDBCDAO();
		Gson gson = new Gson();

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

		if ("getAll".equals(action)) {
			List<HouseVO> houseList = dao.getAll();
			writeText(res, gson.toJson(houseList));
		} 
		if("findByLanidLannotrant".equals(action)) {
			String lan_id = jsonObject.get("lan_id").getAsString();
			String hou_property = jsonObject.get("hou_property").getAsString();
			
			List<HouseVO> houseList =dao.findByLanidLannotrant(lan_id, hou_property);
			
			writeText(res,gson.toJson(houseList));
			
			
		}if("findByPrimaryKey".equals(action)) {
			String lan_id = jsonObject.get("hou_id").getAsString();
		
			
			HouseVO houseList =dao.findByPrimaryKey(lan_id);
			
			writeText(res,gson.toJson(houseList));
			
			
		}
		if("getAllok".equals(action)) {
			String hou_property = jsonObject.get("hou_property").getAsString();
			String hou_parkspace = jsonObject.get("hou_parkspace").getAsString();
			
			List<HouseVO> houseList =dao.getAllok(hou_property, hou_parkspace);
			
			writeText(res,gson.toJson(houseList));
			
		}
		else if("getImage2".equals(action)) {
			OutputStream os = res.getOutputStream();
			String hou_id = jsonObject.get("hou_id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] hou_s_picture = dao.getImage(hou_id);
			if(hou_s_picture != null) {
				hou_s_picture = ImageUtil.shrink(hou_s_picture, imageSize);
				res.setContentType("image/jpeg");;
				res.setContentLength(hou_s_picture.length);
			}
			os.write(hou_s_picture);
		}
		
		else if("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String hou_id = jsonObject.get("hou_id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] hou_f_picture = dao.getImage(hou_id);
			if(hou_f_picture != null) {
				hou_f_picture = ImageUtil.shrink(hou_f_picture, imageSize);
				res.setContentType("image/jpeg");;
				res.setContentLength(hou_f_picture.length);
			}
			os.write(hou_f_picture);
		}else {
			writeText(res,"");}
		
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