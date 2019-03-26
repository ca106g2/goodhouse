package com.goodhouse.schedule_servlet_for_appointment;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.appointment.model.*;

public class ScheduleServletForAppointment extends HttpServlet{    
    Timer timer ; 
    int count = 0;        
    public void destroy(){
        timer.cancel();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    }
            
    public void init(){        
        timer = new Timer();
        Calendar cal = new GregorianCalendar(2019, Calendar.FEBRUARY, 28, 0, 0, 0);        
        TimerTask task = new TimerTask(){
                   
            public void run(){
                System.out.println("This is Task for setApp_status : "+ count);
                System.out.println("Appointment setApp_status工作排定的時間 = " + new Date(scheduledExecutionTime()));
                System.out.println("Appointment setApp_status工作執行的時間 = " + new Date() + "\n");                
                count++;
                
                AppointService svc = new AppointService();
                List<AppointVO> list = svc.getAll();
	                for(AppointVO appointVO : list) {
	                	if(appointVO.getApp_status().equals("A0")) {
	                		
	                		svc.updateAppoint(appointVO.getAppoint_id(), appointVO.getMem_id(), appointVO.getLan_id(), appointVO.getHou_id(), appointVO.getHou_app_time(), appointVO.getHou_app_date(), "A3", appointVO.getApp_remind());
	                		
	                	}
	                }
                }
        };
        timer.scheduleAtFixedRate(task, cal.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); 
    }
}
