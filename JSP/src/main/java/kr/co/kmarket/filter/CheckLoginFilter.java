package kr.co.kmarket.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*        // 로그인 여부 확인
        logger.debug("doFilter...");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        if(sessUser != null) {
            logger.debug("here1...");
            chain.doFilter(request, response);
        }else {
            // 다음 필터 호출, 필터 없으면 최종 자원 요청
            logger.debug("here2...");
            ((HttpServletResponse)response).sendRedirect("/Farmstory2_war_exploded/user/login.do?success=101");
        }*/
    }
}