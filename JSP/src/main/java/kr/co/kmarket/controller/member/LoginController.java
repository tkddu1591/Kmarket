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
		
		/*Cookie[] cookies = req.getCookies(); // getCookies라 배열로 나옴
		
		if(cookies != null) {
		
			for(Cookie cookie : cookies) {
				
				if(cookie.getName().equals("cid")) {
					
					String uid = cookie.getValue();
					
					KmMemberDTO user = service.selectMemberByUid(uid); // cid 이름의 cookie의 value인 uid값으로 쿼리실행
					
					HttpSession session = req.getSession();
					session.setAttribute("sessUser", user);
				}
			}// for문 끝 , 밖에서 session 참조 못함
		}
		*/
		 
		
		/*// 로그인 여부 할 필요가 있나?? 이렇게 확인하는 거 맞나??
		HttpSession session = req.getSession();
		KmMemberDTO user = (KmMemberDTO) session.getAttribute("sessUser");
		
		if(user != null) {
			resp.sendRedirect("/JSP");
			return;
		}
		*/
		
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
		logger.debug("here1 : " + auto);
		
		KmMemberDTO user = service.selectMember(uid, pass);
		
		if(user != null) {
			
			logger.debug("here2");
			
			if(auto != null) {
				
				logger.debug("here3");
				
				Cookie autoCookie = new Cookie("cid", uid);
				autoCookie.setMaxAge(60*3); 
				
				logger.debug("here4 : " + autoCookie.getName());				
				
				resp.addCookie(autoCookie); //response객체에 쿠키전송??
			}
			
			HttpSession session = req.getSession();
			
			session.setAttribute("sessUser", user); 
			
			logger.debug("here5");
			
			// 컨텍스트 경로 전역변수를 이용한 리다이렉트
			resp.sendRedirect(ctxPath); // session은 브라우저 켜져 있는 한 유지되므로 index.jsp에서 sessUser속성을 표현언어로 참조할 수 있는 것 / 위에서 ctxPath 생성해주고 이렇게 쓸수도 있네
			
		}else {
			logger.debug("here6");
			resp.sendRedirect(ctxPath+"/member/login.do?success=100");
		}
	}
}
