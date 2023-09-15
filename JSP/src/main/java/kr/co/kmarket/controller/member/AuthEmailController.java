package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.KmMemberService;

@WebServlet("/member/authEmail.do")
public class AuthEmailController extends HttpServlet{

	private static final long serialVersionUID = 3612483702041445448L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		int result = 0;
		int status = 0;
		
		if(type.equals("FIND_ID")) {
			
			
			
		}
	}

}
