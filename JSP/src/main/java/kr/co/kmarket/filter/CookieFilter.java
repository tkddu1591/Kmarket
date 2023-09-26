package kr.co.kmarket.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmMemberService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CookieFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 자동 로그인 체크여부에 따라 로그인 처리
		

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie[] cookies = httpRequest.getCookies(); // 쿠키 불러올때 httpRequest로 해야함(requestX)
		
		if(cookies != null) {
			

			for(Cookie cookie : cookies) {
			
				

				if(cookie.getName().equals("cid")) {
					
					String uid = cookie.getValue();
					

					KmMemberDTO user = service.selectMemberByUid(uid); // cid 이름의 cookie의 value인 uid값으로 쿼리실행
					

					HttpSession session = httpRequest.getSession();
					session.setAttribute("sessUser", user);
				}
			}
		}
		
		chain.doFilter(request, response);
		
		/*
		KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");
		Cookie[] cookies = httpRequest.getCookies(); // getCookies라 배열로 나옴

		if (sessUser == null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cid")) {
					String uid = cookie.getValue();
					KmMemberDTO user = service.selectMemberByUid(uid); // cid 이름의 cookie의 value인 uid값으로 쿼리실행
					session.setAttribute("sessUser", user);
				}
			}
		}
		*/

		

	}
}