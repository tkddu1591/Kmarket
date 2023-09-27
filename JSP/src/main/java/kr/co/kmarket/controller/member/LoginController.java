package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		
		// success는 왜 수신하는거지??
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
		String auto = req.getParameter("auto");
		
		logger.debug(uid);
		logger.debug(pass);
		
		KmMemberDTO user = service.selectMember(uid, pass);
		
		// DAO에서 selectMember 할 때 DTO 선언시 null로 해줘야 여기서 user가 null이 된다!!!, 이렇게 안하면 user != null 되서 if문 통과함
		if(user != null) {
			
			if(auto != null) {
				
				Cookie autoCookie = new Cookie("cid", uid);
				autoCookie.setMaxAge(60*30); 
				autoCookie.setPath("/"); // ★★★ 이렇게 하면 URL 전범위에서 쿠키 유효, 이게 없으면 LoginController가 속한 /member 범위에서만 유효
				
				resp.addCookie(autoCookie); //response객체에 쿠키전송??
			}
			
			HttpSession session = req.getSession();
			
			session.setAttribute("sessUser", user); 
			
			
			// 컨텍스트 경로 전역변수를 이용한 리다이렉트
			resp.sendRedirect(ctxPath); // session은 브라우저 켜져 있는 한 유지되므로 index.jsp에서 sessUser속성을 표현언어로 참조할 수 있는 것 / 위에서 ctxPath 생성해주고 이렇게 쓸수도 있네
			
		}else {
			
			resp.sendRedirect(ctxPath+"/member/login.do?success=100");
		}
	}
}