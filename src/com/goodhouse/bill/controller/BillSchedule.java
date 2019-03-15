package com.goodhouse.bill.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhouse.bill.model.BillService;
import com.goodhouse.bill.model.BillVO;
import com.goodhouse.ele_contract.controller.Ele_ContractStatusTool.Ele_con_status;
import com.goodhouse.ele_contract.model.Ele_ContractService;
import com.goodhouse.ele_contract.model.Ele_ContractVO;

@WebServlet(name="/BillSchedule" , loadOnStartup = 1 ,urlPatterns="/BillSchedule")
public class BillSchedule extends HttpServlet{

	Timer timerSch ;
//	SimpleDateFormat sdf,df;
//	Date timeStart;//開始時間
	
	protected void doGet(HttpServletRequest req , HttpServletResponse res) {
		doGet(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
	}
	
	public void init() {
		
		timerSch = new Timer();
//		SimpleDateFormat sdf = new SimpleDateFormat();
//		long long_now =  System.currentTimeMillis();
		
		TimerTask task = new TimerTask() {
			
			public void run() {
				
				System.out.println("我要開始了>///<");
				
				Ele_ContractService eleConSvc = new Ele_ContractService();
				BillService billSvc = new BillService();
				
				
				long nowtime = System.currentTimeMillis();
				java.sql.Date now = new java.sql.Date(nowtime);
				
				//找出所有合約
				qq:
				for(Ele_ContractVO eleConVO : eleConSvc.getAll()) {
					//找出每筆合約的帳單
					List<BillVO> billList = billSvc.getListByEleContractBetweenRentTime(eleConVO.getEle_con_id());
					BillVO billVO = null;
					
						//找出最後一筆帳單
					if(!(billList.size() < 1)) {
						billVO = billList.get(billList.size()-1);
						System.out.println();
					} else {
						System.out.println("無效合約：" + eleConVO.getEle_con_id());
						System.out.println();
						continue qq;
					}
						//最後一筆帳單的繳費日期
						GregorianCalendar billCal = new GregorianCalendar();
						billCal.setTime(billVO.getBill_date());
						System.out.println("billVO.getBill_id() = "+ billVO.getBill_id());
						System.out.println("nowCal.getTime() = "+ billCal.getTime());
						
						
						int year = billCal.get(Calendar.YEAR);
						System.out.println("year = "+year);
						
						int nextMonth = billCal.get(Calendar.MONTH) + 1;
						System.out.println("nextMonth = "+nextMonth);
						
						if(nextMonth == 12) {
							nextMonth = 0;
							year += 1;
						}
						
						int date = billCal.get(Calendar.DAY_OF_MONTH);
						System.out.println("date = "+date);
						
						Calendar nextTime = new GregorianCalendar(year, nextMonth, date);
						java.sql.Date nextBill_date = new java.sql.Date(nextTime.getTime().getTime());
						System.out.println("nextBill_date = "+nextBill_date);
					//如果合約狀態是已簽約
					if(eleConVO.getEle_con_status().equals("s2")) {
						System.out.println("if1");
						//如果新帳單日期介於 合約的起訖日 跟 結束日 且 上期帳單在系統當下時間之前
						if( nextBill_date.after(eleConVO.getEle_rent_f_day())  && 
							nextBill_date.before(eleConVO.getEle_rent_l_day()) &&
							billVO.getBill_date().before(now)) {
							//產生新帳單
							System.out.println("if2");
							BillVO newBill = new BillVO();
							newBill.setEle_con_id(eleConVO.getEle_con_id());
							newBill.setBill_pay(eleConVO.getEle_rent_money());
							newBill.setBill_date(nextBill_date);
							newBill.setBill_producetime(now);
							newBill.setBill_status("s2");
							newBill.setBill_paymethod("VISACard");
							newBill.setBill_paymenttype(eleConVO.getBill_paymenttype());
							
							billSvc.addB(newBill);
						}
					}
					System.out.println();
				}
			}
		};
		Calendar cal = new GregorianCalendar(2019,Calendar.MARCH, 11,0,0,0);
		timerSch.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);//24(時)*60(分)*60(秒)*1000(毫秒)
	}
	

	@Override
	public void destroy() {
		if(timerSch != null) {
			timerSch.cancel();
		}
	}
	
}
