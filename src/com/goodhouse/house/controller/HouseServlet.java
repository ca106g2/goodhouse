package com.goodhouse.house.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.websocket.Session;
import org.json.JSONException;
import org.json.JSONObject;
import com.goodhouse.house.model.*;
import com.goodhouse.landlord.model.LanService;
import com.goodhouse.member.model.MemVO;

@MultipartConfig
public class HouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HouseServlet() { 
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//*****************************常慶複合查詢轉到doPost用*********/
		if(req.getParameter("action") != null && req.getParameter("action").equals("listHou_ByCompositeQueryForIndex")) {
			doPost(req, res);
			return;
		}else {

		//*****************************結束*********************************/
			
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();

		try {
			String hou_id = req.getParameter("hou_id");
			// 抓取jsp的hou_id請求
			HouseService houSvc = new HouseService();
			// 宣告HouseService
			HouseVO houVO = houSvc.getOneHouse(hou_id);
			// 宣告HouseVO並將HouseService裡面getOneHouse方法(抓取jsp的hou_id請求)丟入HOUVO取得此ID的所有欄位記憶體位子
			byte[] photo = null;
			if (Integer.parseInt(req.getParameter("photo")) == 1) {
				// 判斷JSP(listAllHouse.jsp)src後面的photo是放甚麼參數
				photo = houVO.getHou_f_picture();
			} else if (Integer.parseInt(req.getParameter("photo")) == 2) {
				photo = houVO.getHou_s_picture();
			} else if (Integer.parseInt(req.getParameter("photo")) == 3) {
				photo = houVO.getHou_t_picture();
			}
			out.write(photo);
			// 寫出對應圖片
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/back/house/image/test.jpg");
			byte[] pt = new byte[4 * 1024];
			int i;
			while ((i = in.read(pt)) != -1) {
				out.write(pt, 0, i);
			}
			in.close();
		}
		}//常慶複合查詢用esle括號
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//**********************insert	TODO	
				if ("frontinsert".equals(action)) {

					Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try {
						String hou_name = req.getParameter("hou_name").trim();
						if (hou_name == null || hou_name.trim().length() == 0) {
							errorMsgs.put("hou_name", "房屋名稱不可空白");
						}
						String hou_type = req.getParameter("hou_type").trim();
						if (hou_type == null || hou_type.trim().length() == 0) {
							errorMsgs.put("hou_type", "房屋型別不可空白");
						}

						String hou_size = req.getParameter("hou_size").trim();
						String hou_sizeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
						if (hou_size == null || hou_size.trim().length() == 0) {
							hou_size = "無填寫";
						} else if (!hou_size.trim().matches(hou_sizeReg)) {
							errorMsgs.put("hou_size", "請勿用逗點或其他符號");
						}

						String hou_property = req.getParameter("hou_property").trim();
						if (hou_property == null || hou_property.trim().length() == 0) {
							errorMsgs.put("hou_property", "產權不可空白");
						}
						String hou_parkspace = req.getParameter("hou_parkspace").trim();
						if (hou_parkspace == null || hou_parkspace.trim().length() == 0) {
							errorMsgs.put("hou_parkspace", "車位不可空白");
						}
						String hou_cook = req.getParameter("hou_cook").trim();
						if (hou_cook == null || hou_cook.trim().length() == 0) {
							errorMsgs.put("hou_cook", "開火不可空白");
						}
						String hou_managefee = req.getParameter("hou_managefee").trim();
						String hou_managefeeReg = "^[0-9][0-9]{1,6}$";
						if (hou_managefee == null || hou_managefee.trim().length() == 0) {
							errorMsgs.put("hou_managefee", "管理費不可空白若無請填0");
						} else if (!hou_managefee.trim().matches(hou_managefeeReg)) {
							errorMsgs.put("hou_managefee", "請填正整數2~6碼");
						}

						String twCityName = req.getParameter("twCityName");
						String CityAreaName = req.getParameter("CityAreaName");
						String AreaRoadName = req.getParameter("AreaRoadName");
						String num = req.getParameter("num");
						if(twCityName.equals("0")) {
							errorMsgs.put("twCityName", "縣市未選擇");
						} else if(CityAreaName.equals("0")) {
							errorMsgs.put("CityAreaName", "區域未選擇");
						} else if(AreaRoadName.equals("0")) {
							errorMsgs.put("CityAreaName", "路名未選擇");
						} else if(num == null || num.trim().length() == 0) {
							errorMsgs.put("num", "門牌號碼請勿空白");
						}						
						String hou_address =  twCityName + CityAreaName + AreaRoadName + num;

		//-------------------------------------------房屋金額用字串接在後端判斷完後改變型態存入資料庫				
						String hou_rent_str = req.getParameter("hou_rent_str").trim();
						String hou_rent_Reg = "^[1-9][0-9]{2,6}$";
						if (hou_rent_str == null || hou_rent_str.trim().length() == 0) {
							errorMsgs.put("hou_rent_str", "租金不可空白");
						} else if (!hou_rent_str.trim().matches(hou_rent_Reg)) {
							errorMsgs.put("hou_rent_str", "請填正整數2~6碼");
						}
						//System.out.println("字串" + hou_rent_str);//檢測用
						Integer hou_rent = null;
						try {
							hou_rent = Integer.parseInt(hou_rent_str);
						}catch(NumberFormatException e){
							
						}
						//System.out.println("11數字" + hou_rent);//檢測用
						//-------------------------------------------end_of_test
						String lan_id = new String(req.getParameter("lan_id").trim());
						String hou_note = new String(req.getParameter("hou_note").trim());
						if(hou_note==null|| hou_note.trim().length()==0) {
							hou_note=null;
						}
						/**********************************/
						// 圖片一
						Part hou_f_picture = req.getPart("hou_f_picture");
						BufferedInputStream buf = new BufferedInputStream(hou_f_picture.getInputStream());
						ByteArrayOutputStream baf = new ByteArrayOutputStream();
						int f;
						byte bf[] = new byte[8192];
						while ((f = buf.read(bf)) != -1) {
							baf.write(bf);
						}
						baf.toByteArray();
						/************** 圖片一end ********************/
						
						/**********************************/
						// 圖片二
						Part hou_s_picture = req.getPart("hou_s_picture");
						BufferedInputStream bus = new BufferedInputStream(hou_s_picture.getInputStream());
						ByteArrayOutputStream bas = new ByteArrayOutputStream();
						int s;
						byte bs[] = new byte[8192];
						while ((s = bus.read(bs)) != -1) {
							bas.write(bs);
						}
						bas.toByteArray();
						/************** 圖片二end ********************/
						
						/**********************************/
						// 圖片三
						Part hou_t_picture = req.getPart("hou_t_picture");
						BufferedInputStream but = new BufferedInputStream(hou_t_picture.getInputStream());
						ByteArrayOutputStream bat = new ByteArrayOutputStream();
						int t;
						byte bt[] = new byte[8192];
						while ((t = but.read(bt)) != -1) {
							bat.write(bt);
						}
						bat.toByteArray();

//--------------------------						
						HouseVO houVO = new HouseVO();
			//			houVO.setHou_id(hou_id);
						houVO.setHou_name(hou_name);
						houVO.setHou_type(hou_type);
						houVO.setHou_size(hou_size);
						houVO.setHou_property(hou_property);
						houVO.setHou_parkspace(hou_parkspace);
						houVO.setHou_cook(hou_cook);
						houVO.setHou_managefee(hou_managefee);
						houVO.setHou_address(hou_address);
						houVO.setLan_id(lan_id);
						houVO.setHou_rent(hou_rent);
						houVO.setHou_note(hou_note);
						houVO.setHou_f_picture(baf.toByteArray());
						houVO.setHou_s_picture(bas.toByteArray());
						houVO.setHou_t_picture(bat.toByteArray());

						/**********************************/
						System.out.println("檢查1");
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/front/house/addHouse.jsp");
							failureView.forward(req, res);
							return;
						}
						System.out.println("檢查2");
//------------------------新增改vo-----------------------------------																	
						HouseService houSvc = new HouseService();
						houSvc.addHouse(houVO);
						req.setAttribute("lastPage", true);
//------------------------------------------------------------	
						System.out.println("檢查3");
						List<HouseVO> lan_list_all = houSvc.getAllFor_Hou_Lan_id(lan_id);
						req.getSession().setAttribute("lan_list_all", lan_list_all);
						String url = "/front/house/listAllHouse.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll_ele_contract.jsp
						successView.forward(req, res);
						System.out.println("檢查4");
					} catch (Exception e) {
						errorMsgs.put("insert失敗", e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front/house/addHouse.jsp");
						failureView.forward(req, res);
					}

				}
//**********************insert		
		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String hou_name = req.getParameter("hou_name").trim();
				if (hou_name == null || hou_name.trim().length() == 0) {
					errorMsgs.put("hou_name", "房屋名稱不可空白");
				}
				String hou_type = req.getParameter("hou_type").trim();
				if (hou_type == null || hou_type.trim().length() == 0) {
					errorMsgs.put("hou_type", "房屋型別不可空白");
				}

				String hou_size = req.getParameter("hou_size").trim();
				String hou_sizeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (hou_size == null || hou_size.trim().length() == 0) {
					hou_size = "無填寫";
				} else if (!hou_size.trim().matches(hou_sizeReg)) {
					errorMsgs.put("hou_size", "請勿用逗點或其他符號");
				}

				String hou_property = req.getParameter("hou_property").trim();
				if (hou_property == null || hou_property.trim().length() == 0) {
					errorMsgs.put("hou_property", "產權不可空白");
				}
				String hou_parkspace = req.getParameter("hou_parkspace").trim();
				if (hou_parkspace == null || hou_parkspace.trim().length() == 0) {
					errorMsgs.put("hou_parkspace", "車位不可空白");
				}
				String hou_cook = req.getParameter("hou_cook").trim();
				if (hou_cook == null || hou_cook.trim().length() == 0) {
					errorMsgs.put("hou_cook", "開火不可空白");
				}
				String hou_managefee = req.getParameter("hou_managefee").trim();
				String hou_managefeeReg = "^[0-9][0-9]{1,6}$";
				if (hou_managefee == null || hou_managefee.trim().length() == 0) {
					errorMsgs.put("hou_managefee", "管理費不可空白若無請填0");
				} else if (!hou_managefee.trim().matches(hou_managefeeReg)) {
					errorMsgs.put("hou_managefee", "請填正整數2~6碼");
				}
				String hou_address = req.getParameter("hou_address").trim();
				String hou_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if (hou_address == null || hou_address.trim().length() == 0) {
					errorMsgs.put("hou_address", "地址必須填寫(不然怎麼看房)");
				} else if (!hou_address.trim().matches(hou_addressReg)) {
					errorMsgs.put("hou_address", "格式不正確");
				}
//-------------------------------------------房屋金額用字串接在後端判斷完後改變型態存入資料庫				
				String hou_rent_str = req.getParameter("hou_rent_str").trim();
				String hou_rent_Reg = "^[1-9][0-9]{2,6}$";
				if (hou_rent_str == null || hou_rent_str.trim().length() == 0) {
					errorMsgs.put("hou_rent_str", "租金不可空白");
				} else if (!hou_rent_str.trim().matches(hou_rent_Reg)) {
					errorMsgs.put("hou_rent_str", "請填正整數2~6碼");
				}
				//System.out.println("字串" + hou_rent_str);//檢測用
				Integer hou_rent = null;
				try {
					hou_rent = Integer.parseInt(hou_rent_str);
				}catch(NumberFormatException e){
					
				}
				//System.out.println("11數字" + hou_rent);//檢測用
				//-------------------------------------------end_of_test
				String lan_id = new String(req.getParameter("lan_id").trim());
				String hou_note = new String(req.getParameter("hou_note").trim());
				if(hou_note==null|| hou_note.trim().length()==0) {
					hou_note=null;
				}
				/**********************************/
				// 圖片一
				Part hou_f_picture = req.getPart("hou_f_picture");
				BufferedInputStream buf = new BufferedInputStream(hou_f_picture.getInputStream());
				ByteArrayOutputStream baf = new ByteArrayOutputStream();
				int f;
				byte bf[] = new byte[8192];
				while ((f = buf.read(bf)) != -1) {
					baf.write(bf);
				}
				baf.toByteArray();
				/************** 圖片一end ********************/
				
				/**********************************/
				// 圖片二
				Part hou_s_picture = req.getPart("hou_s_picture");
				BufferedInputStream bus = new BufferedInputStream(hou_s_picture.getInputStream());
				ByteArrayOutputStream bas = new ByteArrayOutputStream();
				int s;
				byte bs[] = new byte[8192];
				while ((s = bus.read(bs)) != -1) {
					bas.write(bs);
				}
				bas.toByteArray();
				/************** 圖片二end ********************/
				
				/**********************************/
				// 圖片三
				Part hou_t_picture = req.getPart("hou_t_picture");
				BufferedInputStream but = new BufferedInputStream(hou_t_picture.getInputStream());
				ByteArrayOutputStream bat = new ByteArrayOutputStream();
				int t;
				byte bt[] = new byte[8192];
				while ((t = but.read(bt)) != -1) {
					bat.write(bt);
				}
				bat.toByteArray();
				/**********************************/
				


				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/house/addHouse.jsp");
					failureView.forward(req, res);
					return;
				}

				HouseService houSvc = new HouseService();
				houSvc.addHouse(hou_name, hou_type, hou_size, hou_property, hou_parkspace, hou_cook, hou_managefee,
						hou_address, lan_id, hou_rent, baf.toByteArray(), bas.toByteArray(), bat.toByteArray(),
						hou_note);

				String url = "/back/house/listAllHouse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("insert失敗", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/addHouse.jsp");
				failureView.forward(req, res);
			}

		}
		// **********************insert end

//		// **********************delete
//		if ("delete".equals(action)) {// 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				String hou_id = new String(req.getParameter("hou_id"));
//				/*************************** 2.開始刪除資料 ***************************************/
//				HouseService houSvc = new HouseService();
//				houSvc.deleteHouse(hou_id);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/back/house/listAllHouse.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/listAllHouse.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		// **********************delete end

		// **********************getOne_For_Display
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("hou_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房屋編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/house/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String hou_id = null;
				try {
					hou_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("房屋編號格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/house/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				HouseService houSvc = new HouseService();
				HouseVO houVO = houSvc.getOneHouse(hou_id);
				if (houVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/house/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("houVO", houVO);
				String url = "/back/house/listOneHouse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("尋找資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		// **********************getOne_For_Display end

		// **********************getOne_For_Update
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String hou_id = new String(req.getParameter("hou_id"));

				HouseService houSvc = new HouseService();
				HouseVO houVO = houSvc.getOneHouse(hou_id);

				req.setAttribute("houVO", houVO);
				String url = "/back/house/update_hou_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得修改的資料....." + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/listAllHouse.jsp");
				failureView.forward(req, res);
			}
		}
		// **********************getOne_For_Update end

		// **********************update
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String hou_id = new String(req.getParameter("hou_id").trim());

				String hou_name = req.getParameter("hou_name").trim();
				if (hou_name == null || hou_name.trim().length() == 0) {
					errorMsgs.add("房屋名稱不可空白");
				}
				String hou_type = req.getParameter("hou_type").trim();
				if (hou_type == null || hou_type.trim().length() == 0) {
					errorMsgs.add("房屋型別不可空白");
				}

				String hou_size = req.getParameter("hou_size").trim();
				if (hou_size == null || hou_size.trim().length() == 0) {
					hou_size = "無填寫";
				}

				String hou_property = req.getParameter("hou_property").trim();
				if (hou_property == null || hou_property.trim().length() == 0) {
					errorMsgs.add("產權不可空白");
				}
				String hou_parkspace = req.getParameter("hou_parkspace").trim();
				if (hou_parkspace == null || hou_parkspace.trim().length() == 0) {
					errorMsgs.add("車位不可空白");
				}
				String hou_cook = req.getParameter("hou_cook").trim();
				if (hou_cook == null || hou_cook.trim().length() == 0) {
					errorMsgs.add("開火不可空白");
				}
				String hou_managefee = req.getParameter("hou_managefee").trim();
				String hou_managefeeReg = "^[0-9][0-9]{0,6}$";
				if (hou_managefee == null || hou_managefee.trim().length() == 0) {
					errorMsgs.add("管理費不可空白若無請填0");
				} else if (!hou_managefee.trim().matches(hou_managefeeReg)) {
					errorMsgs.add("請填管理費請輸入數字");
				}
				String hou_address = req.getParameter("hou_address").trim();
				String hou_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if (hou_address == null || hou_address.trim().length() == 0) {
					errorMsgs.add("地址必須填寫(不然怎麼看房)");
				} else if (!hou_address.trim().matches(hou_addressReg)) {
					errorMsgs.add("格式不正確");
				}


				
				// -------------------------------------------房屋金額用字串接在後端判斷完後改變型態存入資料庫
				Integer hou_rent = new Integer(req.getParameter("hou_rent"));
				
				String hou_rent_str = String.valueOf(hou_rent);
				String hou_rent_Reg = "^[1-9][0-9]{2,6}$";
				if (hou_rent_str == null || hou_rent_str.trim().length() == 0) {
					errorMsgs.add("租金不可空白");
				} else if (!hou_rent_str.trim().matches(hou_rent_Reg)) {
					errorMsgs.add("請填正整數2~6碼");
				}
				System.out.println("字串" + hou_rent_str);
				hou_rent = Integer.parseInt(hou_rent_str);
				System.out.println("11數字" + hou_rent);
				
				// -------------------------------------------end_of_test

				String hou_note = new String(req.getParameter("hou_note").trim());
				if(hou_note==null|| hou_note.trim().length()==0) {
					hou_note=null;
				}
				/**********************************/
				// 圖片一
				Part hou_f_picture = req.getPart("hou_f_picture");
				byte[] pictf;
				if (hou_f_picture.getSize() == 0) {
					HouseService houSvc = new HouseService();
					HouseVO houVO = houSvc.getOneHouse(hou_id);
					pictf = houVO.getHou_f_picture();
				} else {

					BufferedInputStream buf = new BufferedInputStream(hou_f_picture.getInputStream());
					ByteArrayOutputStream baf = new ByteArrayOutputStream();
					int f;
					byte bf[] = new byte[8192];
					while ((f = buf.read(bf)) != -1) {
						baf.write(bf);
					}
					pictf = baf.toByteArray();
				}
				/************** 圖片一end ********************/
				/**********************************/
				// 圖片二
				Part hou_s_picture = req.getPart("hou_s_picture");
				byte[] picts;
				if (hou_s_picture.getSize() == 0) {
					HouseService houSvc = new HouseService();
					HouseVO houVO = houSvc.getOneHouse(hou_id);
					picts = houVO.getHou_s_picture();
				} else {
					BufferedInputStream bus = new BufferedInputStream(hou_s_picture.getInputStream());
					ByteArrayOutputStream bas = new ByteArrayOutputStream();
					int s;
					byte bs[] = new byte[8192];
					while ((s = bus.read(bs)) != -1) {
						bas.write(bs);
					}
					picts = bas.toByteArray();
				}
				/************** 圖片二end ********************/
				/**********************************/
				// 圖片三
				Part hou_t_picture = req.getPart("hou_t_picture");
				byte[] pictt;
				if (hou_t_picture.getSize() == 0) {
					HouseService houSvc = new HouseService();
					HouseVO houVO = houSvc.getOneHouse(hou_id);
					pictt = houVO.getHou_t_picture();
				} else {
					BufferedInputStream but = new BufferedInputStream(hou_t_picture.getInputStream());
					ByteArrayOutputStream bat = new ByteArrayOutputStream();
					int t;
					byte bt[] = new byte[8192];
					while ((t = but.read(bt)) != -1) {
						bat.write(bt);
					}
					pictt = bat.toByteArray();
				}
				/*************** 圖片三 end *******************/

				HouseVO houVO = new HouseVO();
				houVO.setHou_id(hou_id);
				houVO.setHou_name(hou_name);
				houVO.setHou_type(hou_type);
				houVO.setHou_size(hou_size);
				houVO.setHou_property(hou_property);
				houVO.setHou_parkspace(hou_parkspace);
				houVO.setHou_cook(hou_cook);
				houVO.setHou_managefee(hou_managefee);
				houVO.setHou_address(hou_address);
				houVO.setHou_rent(hou_rent);
				houVO.setHou_note(hou_note);
				houVO.setHou_f_picture(pictf);
				houVO.setHou_s_picture(picts);
				houVO.setHou_t_picture(pictt);
				req.setAttribute("houVO", houVO);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/house/update_hou_input.jsp");
					failureView.forward(req, res);
					return;
				}
				HouseService housSvc = new HouseService();
				houVO = housSvc.updateHouse(hou_id, hou_name, hou_type, hou_size, hou_property, hou_parkspace, hou_cook,
						hou_managefee, hou_address, hou_rent, hou_note, pictf, picts, pictt);

				if("已審核".equals(hou_parkspace)) {
					//----------------------------------------------------------積分++
						//HttpSession session = req.getSession();		
						String mem_id = new String(req.getParameter("mem_id").trim());
						com.goodhouse.member.model.MemService memSvc = new com.goodhouse.member.model.MemService();
						com.goodhouse.member.model.MemVO memVO = memSvc.getOneMem(mem_id);
						Integer good_total = memVO.getGood_total();
						good_total = good_total + 1000000;
						memSvc.updatePointTot(mem_id, good_total);
						
						com.goodhouse.good_record.model.Good_recordService gdSvc = new com.goodhouse.good_record.model.Good_recordService();
						gdSvc.addGood_record(mem_id, "房屋新增", 1000000, new Timestamp(System.currentTimeMillis()));						
						memVO = memSvc.getOneMem(mem_id);

						/********新增完畢電子合約，寄e-mail通知房客**********************/
						
						String to = memSvc.getOneMem(mem_id).getMem_email() ;
					      
					    String subject = "電子合約通知";
					    LanService lanSvc = new LanService();
//					    String passRandom = "慈慈測試";
					    String messageText = "Hello! " + memSvc.getOneMem(mem_id).getMem_name() + "，" + 
					    		memSvc.getOneMem(mem_id).getMem_name() +
					    		"恭喜您的房屋審核通過囉~趕快去申請廣告吧!!!" + "\n" + "\n" + "http://" + req.getServerName() + ":" + 
					    		req.getServerPort() + req.getContextPath() + "/front/frontLogin.jsp";
					    
					    MailService mailService = new MailService();
					    mailService.sendMail(to, subject, messageText);	
						
						
						
						
					
//********************webSocket功能啟動*****************
						Set<Session> allSessions = (Set<Session>)getServletContext().getAttribute("HouwebSocketSession");
					
						JSONObject housesock = new JSONObject();
					
						try {
							housesock.put("houMsgs","恭喜"+memVO.getMem_name()+
									"新屋上架喔，還在找房的人快來看看是不是您的需求，GOODHOUSE祝福您順心!"+"恭喜獲得"+1000000+"積分");
						} catch (JSONException e) {
						e.printStackTrace();
						}
					
						String doneMsg = housesock.toString();
						System.out.println(doneMsg);
							for(Session webSession : allSessions) {
								if (webSession.isOpen()) {
									webSession.getAsyncRemote().sendText(doneMsg);
								}
							}	
						}else {
							req.setAttribute("houVO", houVO);
							RequestDispatcher successView = req.getRequestDispatcher("/back/house/listOneHouse.jsp");
							successView.forward(req, res);
							}
					req.setAttribute("houVO", houVO);
	//********************WebSuck end*************************
				RequestDispatcher successView = req.getRequestDispatcher("/back/house/listOneHouse.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("update失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/update_hou_input.jsp");
				failureView.forward(req, res);
			}
		}
		// **********************update end
		
		
		//***********************複合式查詢
				
		if("listHou_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			HttpSession session = req.getSession();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Map<String, String[]> map = req.getParameterMap();
				
				HouseService houSvc = new HouseService();
				List<HouseVO> list = houSvc.getAll(map);
				
				session.setAttribute("list", list);
				RequestDispatcher successView = 
						req.getRequestDispatcher("/back/house/listHou_ByCompositeQuery.jsp");
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/back/house/select_page.jsp");
				failureView.forward(req, res);		
			}
		}
		// **********************getOne_For_Display end  <---常慶的
		if ("front_getOne_For_Display".equals(action)) {
		// **********************TIM寫的為了積分為了部落(以下)
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
			if(memVO!=null) {
			String mem_id = memVO.getMem_id();
			com.goodhouse.member.model.MemService memSvc = new com.goodhouse.member.model.MemService();
			com.goodhouse.member.model.MemVO memVO2 = memSvc.getOneMem(mem_id);				
			session.removeAttribute("memVO");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
			session.setAttribute("memVO", memVO2); 
			}
		// **********************TIM寫的為了積分為了部落(以上)	
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("hou_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房屋編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/index.jsp");
					failureView.forward(req, res);
					return;
				}
				String hou_id = null;
				try {
					hou_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("房屋編號格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/index.jsp");
					failureView.forward(req, res);
					return;
				}
				HouseService houSvc = new HouseService();
				HouseVO houVO = houSvc.getOneHouse(hou_id);
				if (houVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/index.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("houVO", houVO);
				String url = "/front/lin/listHouseDetail_reserveLink.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("尋找資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/index.jsp");
				failureView.forward(req, res);
			}

		}
				// **********************getOne_For_Display end
		
		if("listHou_ByCompositeQueryForIndex".equals(action)) {
			
			System.out.println("Test-1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("Test-2");
			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				System.out.println("Test-3");
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				System.out.println("Test-4");
				if (req.getParameter("whichPage") == null){
					System.out.println("Test-5");
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					System.out.println(map1);
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				System.out.println("Test-6");
				HouseService houSvc = new HouseService();
				List<HouseVO> list  = houSvc.getAllForIndex(map);
				System.out.println("Test-7");
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				System.out.println("Test-8");
				req.setAttribute("listHou_ByCompositeQueryForIndex", list); // 資料庫取出的list物件,存入request
				System.out.println("Test-10");
				RequestDispatcher successView = req.getRequestDispatcher("/front/house/listHou_ByCompositeQueryForIndex.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				System.out.println("Test-11");
				successView.forward(req, res);
				System.out.println("Test-12");
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test-9");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/index.jsp");
				failureView.forward(req, res);
			}
		}
	}
	public class MailService {
		
		// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
				
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";//寄件者自己的帳號
		     final String myGmail_password = "BBB45678";//寄件者自己的密碼
		     javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
	}
}
