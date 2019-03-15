package frontLogout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodhouse.employee.model.EmpService;
import com.goodhouse.employee.model.EmpVO;
import com.goodhouse.member.model.MemVO;

@WebServlet("/FrontLogoutHandler")
public class FrontLogoutHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//【檢查使用者輸入是否已連線】
	//【實際上應至資料庫搜尋比對】

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
	
		HttpSession session = req.getSession();
		
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		if(memVO != null) {
			session.removeAttribute("memVO");
			try {                                                        
				res.sendRedirect(req.getContextPath()+"/front/member/select_page.jsp");            
			}catch (Exception ignored) { }
		}
		
		
		
	}

}

