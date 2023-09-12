package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmMemberService;

@WebServlet("/member/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = -2365079189659779648L;
	
	// 컨텍스트 경로 전역변수
	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 컨텍스트 경로 구하기(최초 1번, 모든 컨트롤러에 정의)
		ctxPath = config.getServletContext().getContextPath();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		logger.debug(success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		logger.debug(uid);
		logger.debug(pass);
		
		KmMemberDTO user = service.selectMember(uid, pass);
		
		if(user != null) {
			
			HttpSession session = req.getSession();
			
			session.setAttribute("sessUser", user);
			
			// 컨텍스트 경로 전역변수를 이용한 리다이렉트
			resp.sendRedirect(ctxPath);
		}else {
			resp.sendRedirect(ctxPath+"/member/login.do?success=100");
		}
	}
}
