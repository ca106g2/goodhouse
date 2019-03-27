package android.com.goodhouse.house_track.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.goodhouse.house.model.HouseDAO_interface;
import android.com.goodhouse.house.model.HouseJDBCDAO;
import android.com.goodhouse.house.model.HouseVO;
import android.com.goodhouse.house_track.model.House_TrackDAO_interface;
import android.com.goodhouse.house_track.model.House_TrackJDBCDAO;
import android.com.goodhouse.house_track.model.House_TrackVO;
import android.com.goodhouse.house_track.model.LoveHouseVO;

public class AndroidHouse_trackServlet extends HttpServlet {
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

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		House_TrackDAO_interface track1 = new House_TrackJDBCDAO();
		String action = jsonObject.get("action").getAsString();

		if (action.equals("isHouseIdExist")) {
			String hou_id = jsonObject.get("hou_id").getAsString();
			String mem_id = jsonObject.get("mem_id").getAsString();
			writeText(res, String.valueOf(track1.isHouseIdExist(hou_id, mem_id)));

		} else if (action.equals("add")) {
			House_TrackVO trackVO = gson.fromJson(jsonObject.get("trackVO").getAsString(), House_TrackVO.class);
			writeText(res, String.valueOf(track1.add(trackVO)));

		} else if (action.equals("delete2")) {
			String hou_id = jsonObject.get("hou_id").getAsString();
			String mem_id = jsonObject.get("mem_id").getAsString();
			writeText(res, String.valueOf(track1.delete2(hou_id,mem_id)));

		} else if (action.equals("findByid")) {
			String mem_id = jsonObject.get("mem_id").getAsString();
			List<House_TrackVO> houseList = track1.findByid(mem_id);
			if (houseList != null) {
				HouseDAO_interface houseDAO_in = new HouseJDBCDAO();
				List<HouseVO> loveHouseList = new ArrayList<>();
				for (House_TrackVO trackVO : houseList) {
					String hou_id = trackVO.getHou_id();
					HouseVO houseVO = houseDAO_in.findByPrimaryKey(hou_id);
					loveHouseList.add(houseVO);

				}
				writeText(res,gson.toJson(loveHouseList));

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
