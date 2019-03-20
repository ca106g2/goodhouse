package com.goodhouse.pointgoods.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.goodhouse.pointgoods.model.PointgoodsVO;
import com.google.gson.Gson;

@WebServlet("/Json2Read")
public class Json2Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public Json2Read() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset=UTF-8");
		HttpSession session = req.getSession();
		List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
		
		Map<String, Map<String, List<String>>> areaMap = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream("/front/pointgoods/streetname.txt"),"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null)
			sb.append(str);
				
		try {
			String twCityName = req.getParameter("twCityName");
			String twAreaName = req.getParameter("CityAreaName");
			
			JSONArray jArray = new JSONArray(sb.toString());
			for (int i = 0; i < jArray.length(); i++) {
				Map<String, List<String>> map = new HashMap<>();
				JSONObject data = jArray.getJSONObject(i);
				String cityName = data.getString("CityName");
				//System.out.println(cityName); //all city names
				
				JSONArray area = data.getJSONArray("AreaList");
				for (int j = 0; j < area.length(); j++) {
					List<String> list = new ArrayList<>();
					JSONObject jobj = area.getJSONObject(j);
					String areaName = jobj.getString("AreaName");
										
					JSONArray area2 = jobj.getJSONArray("RoadList");
					for (int n = 0; n < area2.length(); n++) {
						JSONObject road = area2.getJSONObject(n);
						String roadName = road.getString("RoadName");
						list.add(roadName);
					}
					map.put(areaName, list);
				}
				areaMap.put(cityName, map);
//				System.out.println(areaMap.size());   �@��24�ӿ���
			}

			br.close();			
			
			if("twCityName".equals(action)){
				
				Gson gsonArea = new Gson(); 
				Map<String, List<String>> testArea = areaMap.get(twCityName);
				out.println(gsonArea.toJson(testArea.keySet()));
				out.close();
			}
			
			if("CityAreaName".equals(action)) {
				Gson gsonArea = new Gson(); 
				Map<String, List<String>> test = areaMap.get(twCityName);
				for (String s : test.keySet()) {
        			List<String> list = test.get(twAreaName);
//					for (String r : l) {
//						System.out.println(list);
						out.println(gsonArea.toJson(list));
						out.close();
//					}
				}
			}
		} catch (JSONException je) {
			je.printStackTrace();
		}
		 		
	}
}