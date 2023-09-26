package kr.co.kmarket.controller.member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/logout.do")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = -559190195010520226L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.removeAttribute("sessUser");
        
        // 쿠키해제
        Cookie cookie = new Cookie("cid", null); // cid 이름의 쿠키의 value 값을 null로
        cookie.setMaxAge(0);	
        cookie.setPath("/"); // URL 전범위에서 유효하도록 설정해줘야지!!!
        resp.addCookie(cookie);
        
        resp.sendRedirect("/JSP/member/login.do?success=200");
    }
}