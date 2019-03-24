package com.goodhouse.pointgoods.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.websocket.Session;

import org.json.JSONException;
import org.json.JSONObject;

import com.goodhouse.good_ord.model.*;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;
import com.goodhouse.pointgoods.model.*;

@MultipartConfig
public class PointgoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PointgoodsServlet() {
        super();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    	req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String good_id = req.getParameter("good_id").trim();
			PointgoodsService pointgoodsSvc = new PointgoodsService();
			PointgoodsVO pointgoodsVO = pointgoodsSvc.getOnePointgoods(good_id);
			byte[] pic = pointgoodsVO.getGood_pic();
			out.write(pic);
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/back/pointgoods/images/test1.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		} 
    }
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_nam = req.getParameter("good_nam").trim();
				String good_namReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(good_nam == null || good_nam.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} else if(!good_nam.trim().matches(good_namReg)) {
					errorMsgs.add("商品名稱只能是英文字母、數字和_,長度必須在2到10之間中文字可輸入6個");
				}
				
				String good_dsc = req.getParameter("good_dsc").trim();
//				String good_dscReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,66}$";
				if(good_dsc == null || good_dsc.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				} 
//				else if(!good_dsc.trim().matches(good_dscReg)) {
//					errorMsgs.add("商品描述只能是英文字母、數字和_,長度必須在2到200之間中文字可輸入66個");
//				}
				
				String good_amoStr = req.getParameter("good_amo").trim();
				String good_amoReg = "^[0-9]{1,10}$";
				Integer good_amo = null;
				if(good_amoStr == null || good_amoStr.trim().length() == 0 ) {
					errorMsgs.add("存量不可為空白");
					good_amo = 0;
				} else if(!good_amoStr.matches(good_amoReg)) {
					errorMsgs.add("存量格式錯誤，請重新輸入");
					good_amo = 0;
				} else {
					good_amo = Integer.parseInt(good_amoStr);
				}
				
				String good_priStr = req.getParameter("good_pri").trim();
				String good_priReg = "^[0-9]{1,10}$";
				Integer good_pri = null;
				if(good_priStr == null || good_priStr.trim().length() == 0) {
					errorMsgs.add("價格不可為空白");
					good_pri = 0;
				} else if(!good_priStr.matches(good_priReg)) {
					errorMsgs.add("價格格式輸入錯誤，請重新輸入");
					good_pri = 0;
				} else {
					good_pri = Integer.parseInt(good_priStr);
				}
				
				String good_sta = req.getParameter("good_sta").trim();
				
				Part part = req.getPart("good_pic");
				BufferedInputStream buf = new BufferedInputStream(part.getInputStream());
//				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				int i;
				byte good_pic[] = new byte[buf.available()];
				buf.read(good_pic);
//				while((i=buf.read(b)) != -1) {
//					bao.write(b, 0, i);
//				}
				
				PointgoodsVO pointgoodsVO = new PointgoodsVO();
				pointgoodsVO.setGood_nam(good_nam);
				pointgoodsVO.setGood_dsc(good_dsc);
				pointgoodsVO.setGood_amo(good_amo);
				pointgoodsVO.setGood_pri(good_pri);
				pointgoodsVO.setGood_sta(good_sta);
				pointgoodsVO.setGood_pic(good_pic);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("pointgoodsVO", pointgoodsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/addPointgoods.jsp");
					failureView.forward(req, res);
					return;
				}
				// 開始新增資料
				PointgoodsService pgSvc = new PointgoodsService();
				pointgoodsVO = pgSvc.addPointgoods(good_nam, good_dsc, good_amo, good_pri, good_sta, good_pic);
				
				Set<Session> allSessions = (Set<Session>)getServletContext().getAttribute("PoiWebSocketSession");
				JSONObject pointsock = new JSONObject();
				
				try {
					pointsock.put("poiMsgs", "新商品上架囉");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				String doneMsgs = pointsock.toString();
//				System.out.println(doneMsgs);
				for(Session webSession : allSessions) {
					if(webSession.isOpen()) {
						webSession.getAsyncRemote().sendText(doneMsgs);
					}
				}
				
				// 新增完成，準備轉交
				String url = "/back/pointgoods/listAllPointgoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/addPointgoods.jsp");
				failureView.forward(req, res);						
			}
		}
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_id = req.getParameter("good_id");
				String good_idReg = "^P\\d{9}$";
				if(good_id == null || (good_id.trim()).length() == 0) {
					errorMsgs.add("請輸入積分商品編號");
				} else if(!good_id.trim().matches(good_idReg)) {
					errorMsgs.add("輸入格式錯誤，請輸入如(P000000001)");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				PointgoodsService pointgoodsSvc = new PointgoodsService();
				PointgoodsVO pointgoodsVO =  pointgoodsSvc.getOnePointgoods(good_id);
				
				if(pointgoodsVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("pointgoodsVO", pointgoodsVO);
				String url = "/back/pointgoods/listOnePointgoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				String good_id = req.getParameter("good_id");
				
				PointgoodsService pgSvc = new PointgoodsService();
				PointgoodsVO pointgoodsVO = pgSvc.getOnePointgoods(good_id);
				
				req.setAttribute("pointgoodsVO", pointgoodsVO);
				String url = "/back/pointgoods/updatePointgoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				String good_id = req.getParameter("good_id").trim();
				
				String good_nam = req.getParameter("good_nam").trim();
				String good_namReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(good_nam == null || good_nam.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} else if(!good_nam.trim().matches(good_namReg)) {
					errorMsgs.add("商品名稱只能是英文字母、數字和_,長度必須在2到10之間中文字可輸入6個");
				}
				
				String good_dsc = req.getParameter("good_dsc").trim();
//				String good_dscReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(。;%、)]{2,66}$";
				if(good_dsc == null || good_dsc.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				} 
//				else if(!good_dsc.trim().matches(good_dscReg)) {
//					errorMsgs.add("商品描述只能是英文字母、數字和_,長度必須在2到200之間中文字可輸入66個");
//				}
				
				String good_amoStr = req.getParameter("good_amo").trim();
				String good_amoReg = "^[0-9]{1,10}$";
				Integer good_amo = null;
				if(good_amoStr == null || good_amoStr.trim().length() == 0 ) {
					errorMsgs.add("存量不可為空白");
					good_amo = 0;
				} else if(!good_amoStr.matches(good_amoReg)) {
					errorMsgs.add("存量格式錯誤，請重新輸入");
					good_amo = 0;
				} else {
					good_amo = Integer.parseInt(good_amoStr);
				}
				
				String good_priStr = req.getParameter("good_pri").trim();
				String good_priReg = "^[0-9]{1,10}$";
				Integer good_pri = null;
				if(good_priStr == null || good_priStr.trim().length() == 0) {
					errorMsgs.add("價格不可為空白");
					good_pri = 0;
				} else if(!good_priStr.matches(good_priReg)) {
					errorMsgs.add("價格格式輸入錯誤，請重新輸入");
					good_pri = 0;
				} else {
					good_pri = Integer.parseInt(good_priStr);
				}
				
				String good_sta = req.getParameter("good_sta").trim();
				
				Part good_pic = req.getPart("good_pic");
				byte[] pic;
				if(good_pic.getSize() == 0) {
					PointgoodsService pgSvc = new PointgoodsService();
					PointgoodsVO pointgoodsVO = pgSvc.getOnePointgoods(good_id);
					pic = pointgoodsVO.getGood_pic();
				} else {
					BufferedInputStream buf = new BufferedInputStream(good_pic.getInputStream());
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					int i;
					byte b[] = new byte[8192];
					while((i=buf.read(b)) != -1) {
						bao.write(b, 0, i);
					}
					pic = bao.toByteArray();
//					System.out.println(bao.toByteArray());
				}
				
				PointgoodsVO pointgoodsVO = new PointgoodsVO();
				pointgoodsVO.setGood_id(good_id);
				pointgoodsVO.setGood_nam(good_nam);
				pointgoodsVO.setGood_dsc(good_dsc);
				pointgoodsVO.setGood_amo(good_amo);
				pointgoodsVO.setGood_pri(good_pri);
				pointgoodsVO.setGood_sta(good_sta);
				pointgoodsVO.setGood_pic(pic);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("pointgoodsVO", pointgoodsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/updatePointgoods.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 開始新增資料
				PointgoodsService pgSvc = new PointgoodsService();
				pointgoodsVO = pgSvc.updatePointgoods(good_id, good_nam, good_dsc, good_amo, good_pri, good_sta, pic);
				
				// 新增完成，準備轉交
				req.setAttribute("pointgoodsVO", pointgoodsVO);
//				String url = "/back/pointgoods/listOnePointgoods.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/updatePointgoods.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 接收請求參數
				String good_id = req.getParameter("good_id");
				// 開始刪除資料
				PointgoodsService pgSvc = new PointgoodsService();
				pgSvc.deletePointgoods(good_id);
				// 刪除完成準備轉交
				String url = "/back/pointgoods/listAllPointgoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/listAllPointgoods.jsp");
				failureView.forward(req, res);
			}
		}
		if("addCart".equals(action)) {
			boolean match = false;
			String good_id = req.getParameter("good_id");
			String amount = req.getParameter("amount");
			System.out.println(amount);
			Integer need = Integer.parseInt(amount);
//			String page = req.getParameter("whichPage");

//			System.out.println(need);
			PointgoodsService pointgoodsSvc = new PointgoodsService();
			PointgoodsVO pointgoodsVO = pointgoodsSvc.getOnePointgoods(good_id);
			pointgoodsVO.setGood_nee(need);
			HashMap checkMap = new HashMap();
			if(buylist == null) {
				buylist = new ArrayList<PointgoodsVO>();
				buylist.add(pointgoodsVO);
//				System.out.println("===============1");
				if(pointgoodsVO.getGood_amo() == pointgoodsVO.getGood_nee()) {
					checkMap.put("zero", "true");
				} else if(pointgoodsVO.getGood_nee() < pointgoodsVO.getGood_amo()) {
					checkMap.put("max", pointgoodsVO.getGood_amo() - pointgoodsVO.getGood_nee());
				}
			} else {
				for(int i = 0; i < buylist.size(); i ++) {
					PointgoodsVO pointgoodsVO2 = buylist.get(i);
					if(pointgoodsVO2.getGood_id().equals(pointgoodsVO.getGood_id())) {
						
						if((pointgoodsVO2.getGood_nee() + pointgoodsVO.getGood_nee()) > pointgoodsVO.getGood_amo()) {
							checkMap.put("full", "true");
							match = true;
						} else {
							// 重複下訂將數量相加
							pointgoodsVO2.setGood_nee(pointgoodsVO2.getGood_nee() + pointgoodsVO.getGood_nee());
//							System.out.println("===============2");
							if(pointgoodsVO2.getGood_amo() == pointgoodsVO2.getGood_nee()) {
								 checkMap.put("zero", "true");
							} else if(pointgoodsVO.getGood_nee() < pointgoodsVO.getGood_amo()) {
								checkMap.put("max", pointgoodsVO2.getGood_amo() - pointgoodsVO2.getGood_nee());
							}
							buylist.set(i, pointgoodsVO2);
							match = true;
						}
					}
				}
				
				if(!match) {
					buylist.add(pointgoodsVO);
//					System.out.println("===============3");
					if(pointgoodsVO.getGood_amo() == pointgoodsVO.getGood_nee()) {
						 checkMap.put("zero", "true");
					} else if(pointgoodsVO.getGood_nee() < pointgoodsVO.getGood_amo()) {
						checkMap.put("max", pointgoodsVO.getGood_amo() - pointgoodsVO.getGood_nee());
					}
				}
			}
			
			session.setAttribute("shoppingcart", buylist);
			JSONObject responseJSONObject = new JSONObject(checkMap);
			PrintWriter out = res.getWriter();
			out.println(responseJSONObject);
			out.close();
//			String url = "/front/pointgoods/listAllPointgoods.jsp?whichPage=" + page;
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}
		if("deletecart".equals(action)) {
			String del = req.getParameter("del");
			String removegood_id = req.getParameter("removegood_id");
			HashMap checkMap = new HashMap();
			
			System.out.println("購物車刪除索引直 :" + del);
			for(int i = 0; i < buylist.size(); i ++) {
				PointgoodsVO pointgoodsVO = buylist.get(i);
				if(pointgoodsVO.getGood_id().equals(del)) {
					checkMap.put("amount", pointgoodsVO.getGood_nee());
					buylist.remove(i);
				}
			}
			
			
			if(buylist.size() == 0) {
				checkMap.put("carZero", "true");
			}
			PointgoodsService pointgoodsSvc = new PointgoodsService();
			PointgoodsVO pointgoodsVO = pointgoodsSvc.getOnePointgoods(removegood_id);
			checkMap.put("removegood_id", removegood_id);
			checkMap.put("removeMax", pointgoodsVO.getGood_amo());
			session.setAttribute("shoppingcart", buylist);
			JSONObject responseJSONObject = new JSONObject(checkMap);
			PrintWriter out = res.getWriter();
			out.println(responseJSONObject);
			out.close();
//			String url = "/front/pointgoods/cart.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}
		if("checkout".equals(action)) {
			int total = 0;
			for(int i = 0; i <buylist.size(); i ++) {
				PointgoodsVO pointgoodsVO = buylist.get(i);
				int price = pointgoodsVO.getGood_pri();
				int quantity = pointgoodsVO.getGood_nee();
				total += (price * quantity);
			}
			
			String amount = String.valueOf(total);
//			System.out.println(amount);
			session.setAttribute("amount", amount);
			String url = "checkout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("ordercheck".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String amos = req.getParameter("amo");
				String tots = req.getParameter("tot");
				Integer amo = Integer.parseInt(amos);
				Integer tot = Integer.parseInt(tots);
				
				//判斷積分是否足夠
				if(amo > tot) {
					errorMsgs.put("overage", "積分餘額不足");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/pointgoods/checkout.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String url = "/front/pointgoods/order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("front/pointgoods/checkout.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}

}
