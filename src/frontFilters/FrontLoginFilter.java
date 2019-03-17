package frontFilters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.goodhouse.landlord.model.LanService;
import com.goodhouse.landlord.model.LanVO;
import com.goodhouse.member.model.MemVO;

public class FrontLoginFilter implements Filter {
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		if (memVO == null  ) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front/member/frontLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
		

//		String mem_id = ((MemVO)session.getAttribute("memVO")).getMem_id();
//		
//		LanService lanSvc = new LanService();
//		LanVO lanVO = lanSvc.getOneLanByMemId(mem_id);
//		
//		if (lanVO.getLan_accountstatus() == "2") {
//			session.setAttribute("location", req.getRequestURI());
//			res.sendRedirect(req.getContextPath() + "/front/member/select_page.jsp");
//			return;
//		} else {
//			chain.doFilter(request, response);
//		}
	}
}