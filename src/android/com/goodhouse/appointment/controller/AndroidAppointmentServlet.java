package android.com.goodhouse.appointment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.good_record.model.Good_recordService;
import com.goodhouse.landlord.model.LanService;
import com.goodhouse.landlord.model.LanVO;
import com.goodhouse.member.model.MemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.com.goodhouse.appointment.model.AppointDAO;
import android.com.goodhouse.appointment.model.AppointDAO_interface;
import android.com.goodhouse.appointment.model.AppointVO;
import android.com.goodhouse.house.model.HouseDAO_interface;
import android.com.goodhouse.house.model.HouseJDBCDAO;
import android.com.goodhouse.house.model.HouseVO;
import android.com.goodhouse.landlord.model.LanDAO;
import android.com.goodhouse.landlord.model.LanDAO_interface;
import android.com.goodhouse.member.model.MemDAO;
import android.com.goodhouse.member.model.MemDAO_interface;
import com.goodhouse.member.model.MemVO;

public class AndroidAppointmentServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		 Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		AppointDAO_interface appoint = new AppointDAO();
		String action = jsonObject.get("action").getAsString();
		
		
		
		if(action.equals("insert2")) {
			String lan_id = jsonObject.get("lan_id").getAsString();
			AppointVO app = gson.fromJson(jsonObject.get("app").getAsString(), AppointVO.class);
			writeText(res, String.valueOf( appoint.insert2(app)));
		}
		
		if(action.equals("isUserIdExist2")) {
			String hou_id = jsonObject.get("hou_id").getAsString();
			String mem_id= jsonObject.get("mem_id").getAsString();
			Calendar calender = Calendar.getInstance();
			
	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String hou_app_date =sdf.format(calender.getTime()).toString();
			System.out.println(hou_app_date);
//			Date hou_app_date = Date.valueOf(date);
	
			
			writeText(res, String.valueOf(appoint.isUserIdExist2(mem_id,hou_id,hou_app_date)));
		}
		
		
		if(action.equals("isUserIdExist")) {
			String lan_id = jsonObject.get("lan_id").getAsString();
			String mem_id= jsonObject.get("mem_id").getAsString();
			Calendar calender = Calendar.getInstance();
			
	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String hou_app_date =sdf.format(calender.getTime()).toString();
			System.out.println(hou_app_date);
//			Date hou_app_date = Date.valueOf(date);
			String app_status = "A0";
			
			writeText(res, String.valueOf(appoint.isUserIdExist(mem_id,lan_id,hou_app_date,app_status)));
			
		}if(action.equals("updatestatud")) {
			String lan_id = jsonObject.get("lan_id").getAsString();
			String mem_id= jsonObject.get("mem_id").getAsString();
			String app_status= jsonObject.get("app_status").getAsString();
			LanService lanSvc = new LanService();
			LanVO lanVo = lanSvc.getOneLan(lan_id);
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(lanVo.getMem_id());
			Integer aa = memVO.getGood_total()+100000;
			
			memSvc.updatePointTot(memVO.getMem_id(), aa);
			
			Good_recordService grSvc = new Good_recordService();
			grSvc.addGood_record(memVO.getMem_id(), "看房成功", 100000, new Timestamp(System.currentTimeMillis()));
			Calendar calender = Calendar.getInstance();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String hou_app_date =sdf.format(calender.getTime()).toString();
			
			writeText(res, String.valueOf(appoint.updatestatud(mem_id,app_status,lan_id,hou_app_date)));
		}
		
		
		
		if ("findByMemid".equals(action)) {
			String mem_id = jsonObject.get("mem_id").getAsString();
//			String start = gson.toJson(jsonObject.get("start").getAsString());
//			String end = gson.toJson(jsonObject.get("end").getAsString());
			String start2 = jsonObject.get("start").getAsString();
			String end2 = jsonObject.get("end").getAsString();
		String start = start2.replace("\"","");
		String end = end2.replace("\"", "");
			System.out.println(mem_id);
			System.out.println(start);
			System.out.println(end);
			
			List<AppointVO> applist = appoint.findByMemid(mem_id, start, end);
			
			
			if (applist != null && !applist.isEmpty()) {

				HouseDAO_interface houseDAO_in = new HouseJDBCDAO();
				MemDAO_interface memDAO = new MemDAO();
				LanDAO_interface lanDAO = new LanDAO();
				JsonArray array = new JsonArray();
				
				
				
				for (AppointVO app : applist) {

					JsonObject obj = new JsonObject();
					HouseVO houseVO = houseDAO_in.findByPrimaryKey(app.getHou_id());
					android.com.goodhouse.landlord.model.LanVO lanVO = lanDAO.findByPrimaryKey(app.getLan_id());
					android.com.goodhouse.member.model.MemVO memVO = memDAO.findByPrimaryKey(lanVO.getMem_id());

					obj.addProperty("houseName", houseVO.getHou_name());
					obj.addProperty("appDate", app.getHou_app_date().toString());
					obj.addProperty("houseId", app.getHou_id());
					obj.addProperty("appTime", app.getHou_app_time());
					
					obj.addProperty("memName", memVO.getMem_name());
					array.add(obj);
				}
				
				
				writeText(res, array.toString());

			}
		}
		if ("findByLanid".equals(action)) {
			String lan_id = jsonObject.get("lan_id").getAsString();
//			String start = gson.toJson(jsonObject.get("start").getAsString());
//			String end = gson.toJson(jsonObject.get("end").getAsString());
			String start2 = jsonObject.get("start").getAsString();
			String end2 = jsonObject.get("end").getAsString();
		String start = start2.replace("\"","");
		String end = end2.replace("\"", "");
			System.out.println(lan_id);
			System.out.println(start);
			System.out.println(end);
			
			List<AppointVO> applist = appoint.findByLanid(lan_id, start, end);
			
			
			if (applist != null && !applist.isEmpty()) {

				HouseDAO_interface houseDAO_in = new HouseJDBCDAO();
				MemDAO_interface memDAO = new MemDAO();
				
				JsonArray array = new JsonArray();
				
				
				
				for (AppointVO app : applist) {
//					String hou_id = app.getHou_id();
//					HouseVO houseVO = houseDAO_in.findByPrimaryKey(hou_id);
//					houseList.add(houseVO);
//					app.setHouseList(houseList);
					JsonObject obj = new JsonObject();
					android.com.goodhouse.member.model.MemVO memVO = memDAO.findByPrimaryKey(app.getMem_id());
					HouseVO houseVO = houseDAO_in.findByPrimaryKey(app.getHou_id());
					obj.addProperty("houseName", houseVO.getHou_name());
					obj.addProperty("memName", memVO.getMem_name());
					obj.addProperty("appDate", app.getHou_app_date().toString());
					obj.addProperty("houseId", app.getHou_id());
					obj.addProperty("appTime", app.getHou_app_time());
					array.add(obj);
				}
				
				
				writeText(res, array.toString());

			}
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
