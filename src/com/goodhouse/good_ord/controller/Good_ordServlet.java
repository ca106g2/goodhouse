package com.goodhouse.good_ord.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.goodhouse.good_ord.model.*;
import com.goodhouse.good_record.model.Good_recordService;
import com.goodhouse.member.model.MemService;
import com.goodhouse.member.model.MemVO;
import com.goodhouse.pointgoods.model.PointgoodsService;
import com.goodhouse.pointgoods.model.PointgoodsVO;

public class Good_ordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Good_ordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("carCheck".equals(action)) {
			
			try {
				boolean openModal = true;
				req.setAttribute("openModal", openModal);
				
				RequestDispatcher successView = req.getRequestDispatcher("/front/pointgoods/listAllPointgoods.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		List<PointgoodsVO> buylist = (List<PointgoodsVO>) session.getAttribute("shoppingcart");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mem_id = req.getParameter("mem_id");
				Timestamp good_ord_dat = new Timestamp(System.currentTimeMillis());
				String good_ord_sta = req.getParameter("good_ord_sta");
				String good_ord_nam = req.getParameter("good_ord_nam");
				String good_ord_namReg = "^[(\u4e00-\u9fa5)]{2,4}$";
				if(good_ord_nam == null || good_ord_nam.trim().length() == 0) {
					errorMsgs.add("收件人名稱不可空白");
				} else if(!good_ord_nam.trim().matches(good_ord_namReg)) {
					errorMsgs.add("姓名需填入3個中文字");
				}
				
				String good_ord_add = req.getParameter("good_ord_add");
				if(good_ord_add == null || good_ord_add.trim().length() == 0) {
					errorMsgs.add("收件地址請勿空白");
				}
				// 地址在處裡
//				String good_ord_addReg = "^[(\u4e00-\u9fa5){}]$";
				
				Good_ordVO good_ordVO = new Good_ordVO();
				good_ordVO.setMem_id(mem_id);
				good_ordVO.setGood_ord_dat(good_ord_dat);
				good_ordVO.setGood_ord_sta(good_ord_sta);
				good_ordVO.setGood_ord_nam(good_ord_nam);
				good_ordVO.setGood_ord_add(good_ord_add);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("Good_ordVO", good_ordVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/addGood_ord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 開始新增資料
				Good_ordService good_ordService = new Good_ordService();
				good_ordVO = good_ordService.addGood_ord(mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_add);
				
				//新增完成，準備轉交
				String url = "/back/good_ord/listAllGood_ord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/addGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				String good_ord_idReg = "^O\\d{9}$";
				if(good_ord_id == null || (good_ord_id.trim().length() == 0)) {
					errorMsgs.add("請輸入積分商品訂單編號");
				} else if(!good_ord_id.trim().matches(good_ord_idReg)) {
					errorMsgs.add("格式輸入錯誤，請輸入如(O000000001)");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Good_ordService good_ordSvc = new Good_ordService();
				Good_ordVO good_ordVO = good_ordSvc.getOneGood_ord(good_ord_id);
				
				if(good_ordVO == null) {
					errorMsgs.add("查無此資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("good_ordVO", good_ordVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord/listOneGood_ord.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				String mem_id = req.getParameter("mem_id");
				Timestamp good_ord_dat = Timestamp.valueOf(req.getParameter("good_ord_dat").trim());
				String good_ord_sta = req.getParameter("good_ord_sta");
				String good_ord_nam = req.getParameter("good_ord_nam");
				String good_ord_tots = req.getParameter("good_ord_tot");
				Integer good_ord_tot = Integer.parseInt(good_ord_tots);
				String good_ord_namReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(good_ord_nam == null || good_ord_nam.trim().length() == 0) {
					errorMsgs.add("收件人名稱不可空白");
				} else if(!good_ord_nam.trim().matches(good_ord_namReg)) {
					errorMsgs.add("姓名需填入3個中文字或是10個字內的英文字");
				}
				// 地址再處理
				String good_ord_add = req.getParameter("good_ord_add");
				if(good_ord_add == null || good_ord_add.trim().length() == 0) {
					errorMsgs.add("收件地址請勿空白");
				}
				
				Good_ordVO good_ordVO = new Good_ordVO();
				good_ordVO.setGood_ord_id(good_ord_id);
				good_ordVO.setMem_id(mem_id);
				good_ordVO.setGood_ord_dat(good_ord_dat);
				good_ordVO.setGood_ord_sta(good_ord_sta);
				good_ordVO.setGood_ord_nam(good_ord_nam);
				good_ordVO.setGood_ord_tot(good_ord_tot);
				good_ordVO.setGood_ord_add(good_ord_add);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("good_ordVO", good_ordVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/updateGood_ord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 開始新增資料
				Good_ordService good_ordSvc = new Good_ordService();
				good_ordVO = good_ordSvc.updateGood_ord(good_ord_id, mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_tot, good_ord_add);
				
				// 新增完成開始轉交
				req.setAttribute("good_ordVO", good_ordVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back/pointgoods/good_ordManage.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料修改失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/pointgoods/updateGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ordService good_ordSvc = new Good_ordService();
				Good_ordVO good_ordVO = good_ordSvc.getOneGood_ord(good_ord_id);
				
				req.setAttribute("good_ordVO", good_ordVO);
				String url = "/back/pointgoods/updateGood_ord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/listAllGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String good_ord_id = req.getParameter("good_ord_id");
				
				Good_ordService good_ordSvc = new Good_ordService();
				good_ordSvc.deleteGood_ord(good_ord_id);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/good_ord/listAllGood_ord.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/good_ord/listAllGood_ord.jsp");
				failureView.forward(req, res);
			}
		}
		if("order".equals(action)) {
			
//			List<String> errorMsgs = new LinkedList<String>();
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 要先轉成String型別，再轉成Ineteger，不然會有錯
				String tot = (String)session.getAttribute("amount");
				Integer good_ord_tot = Integer.parseInt(tot);
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_id = memVO.getMem_id();
				
				Timestamp good_ord_dat = new Timestamp(System.currentTimeMillis());
				String good_ord_sta = "GO001";
				String good_ord_nam = req.getParameter("name");
				String good_ord_namReg = "^[(\u4e00-\u9fa5)]{2,4}$";
				if(good_ord_nam == null || good_ord_nam.trim().length() == 0) {
					errorMsgs.put("good_ord_nam", "收件人名稱不可空白");
				} else if(!good_ord_nam.trim().matches(good_ord_namReg)) {
					errorMsgs.put("good_ord_nam", "姓名需填入3個中文字");
				}
				String phone = req.getParameter("phone");
				if(phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone", "手機不可為空值");
				}
				String email = req.getParameter("email");
				if(email == null || email.trim().length() == 0) {
					errorMsgs.put("email", "信箱不可為空值");
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
				String good_ord_add = twCityName + CityAreaName + AreaRoadName + num;
//				if(good_ord_add == null || good_ord_add.trim().length() == 0) {
//					errorMsgs.put("good_ord_add", "收件地址請勿空白");
//				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/pointgoods/order.jsp");
					failureView.forward(req, res);
					return;
				}
				// 開始新增訂單
				Good_ordService good_ordSvc = new Good_ordService();
				good_ordSvc.addGood_ord2(mem_id, good_ord_dat, good_ord_sta, good_ord_nam, good_ord_tot, good_ord_add, buylist);
				
				// 修改會員積分總額
				Integer good_total = memVO.getGood_total();
				String amounts = (String)session.getAttribute("amount");
				Integer amount = Integer.parseInt(amounts);
				Integer poi_overage = good_total - amount;
				MemService memSvc = new MemService();
				memSvc.updatePointTot(mem_id, poi_overage);
				
				// 新增會員積分明細扣除積分值
				Good_recordService good_recSvc = new Good_recordService();
				good_recSvc.addGood_record(mem_id, "商城購物", amount, good_ord_dat);
				
				// 扣除商品存量
				for(int i = 0; i < buylist.size(); i++) {
					PointgoodsVO pointgoodsVO = buylist.get(i);
					Integer good_nee = pointgoodsVO.getGood_nee();
					Integer good_amo =  pointgoodsVO.getGood_amo();
//					System.out.println("需求量:" + good_nee);
//					System.out.println("存量:" + good_amo);
					Integer good_overage = good_amo - good_nee;
//					System.out.println("餘值" + good_overage);
					String good_id = pointgoodsVO.getGood_id();
					PointgoodsService pointgoodsSvc = new PointgoodsService();
					pointgoodsSvc.updateamo(good_id, good_overage);
//					System.out.println("========111111");
//					System.out.println(pointgoodsVO.getGood_amo());
					PointgoodsVO pointgoodsVO2 = pointgoodsSvc.getOnePointgoods(good_id);
//					int amocheck = pointgoodsVO2.getGood_amo();
//					System.out.println(amocheck);
					if(pointgoodsVO2.getGood_amo() == 0) {
//						System.out.println("=============123");
						pointgoodsSvc.updatesta(good_id, "P002");
//						System.out.println("=============456");
					}
				}
				
//				 String to = "ash75312468@gmail.com";
			      
		         String subject = "訂單成立";
		         StringBuilder sb = new StringBuilder();
		         for(PointgoodsVO pointgoodsVO : buylist) {
		        	 sb.append("<tr>");
		        	 sb.append("<td>");
		        	 sb.append(pointgoodsVO.getGood_nam());
		        	 sb.append("</td>");
		        	 sb.append("<td>");
		        	 sb.append(pointgoodsVO.getGood_dsc());
		        	 sb.append("</td>");
		        	 sb.append("<td>");
		        	 sb.append(pointgoodsVO.getGood_nee());
		        	 sb.append("</td>");
		        	 sb.append("<td>");
		        	 sb.append(pointgoodsVO.getGood_pri());
		        	 sb.append("</td>");
		        	 sb.append("</tr>");
        		 }
		         String messageText = ""
		        		 +"<h4>親愛的 " + good_ord_nam +"，您好 :<h4><br>" +" 您的訂單我們已經著手處裡了!感謝您的兌換!<br>"
         				+ "以下是您的兌換商品清單寄貨資料，請您查看，感謝您!<br>" +
		        		 "<h3>訂單明細</h3>"+
		         		"<table border='1'><tr><th>商品名稱</th><th>商品描述</th><th>數量</th><th>商品價格</th></tr>"
		        		+ sb.toString() + "<tr><td></td><td></td><td>總價 :</td><td><font color='red'><b>" + amount + "</b></font></td></tr>"
		         		+ "</table>";
//		         System.out.println(messageText);
		       
		         MailService mailService = new MailService();
		         mailService.sendMail(email, subject, messageText);
				
				session.removeAttribute("shoppingcart");
				session.removeAttribute("amount");
				session.removeAttribute("memVO");
				MemVO memVO2 = memSvc.getOneMem(mem_id);
				session.setAttribute("memVO", memVO2);
				String url = "/front/pointgoods/listAllPointgoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pointgoods/order.jsp");
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
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "BBB45678";
			   Session session = Session.getInstance(props, new Authenticator() {
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
//			   message.setText(messageText);
			   message.setContent(messageText,"text/html; charset=UTF-8");
			   Transport.send(message);

//			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
	}
}
