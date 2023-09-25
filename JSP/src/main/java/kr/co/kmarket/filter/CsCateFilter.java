package kr.co.kmarket.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.KmCsCateService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CsCateFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        // 현재 세션 가져오기
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        
        if (session.getAttribute("sessCsCates") == null) {
	
            KmCsCateService service = KmCsCateService.INSTANCE;
    		Map<String, Object> map = service.getCsCates();

            session.setAttribute("sessCsCates", map);

        }
        chain.doFilter(req, resp);
    }
}
