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

public class CheckAdminFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private KmMemberService service = KmMemberService.instance;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	
    	// 로그인 여부 확인
        logger.debug("doFilter...");
       
        HttpSession session = httpRequest.getSession();
        KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");

        if(sessUser != null  && (sessUser.getType() == 2||sessUser.getType() == 9)) {
            logger.debug("here1...");
            chain.doFilter(request, response);
        }else {
            // 다음 필터 호출, 필터 없으면 최종 자원 요청
            logger.debug("here2...");
            ((HttpServletResponse) response).sendRedirect("/JSP/member/login.do?success=102");
        }
    }
}