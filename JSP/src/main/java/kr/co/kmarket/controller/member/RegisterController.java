package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmMemberService;

@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = -8565694525391581478L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("km_uid");
		String pass = req.getParameter("km_pass1");
		String name = req.getParameter("km_name");
		String gender = req.getParameter("km_gender");
		String hp = req.getParameter("km_hp");
		String email = req.getParameter("km_email");
		String zip = req.getParameter("km_zip");
		String addr1 = req.getParameter("km_addr1");
		String addr2 = req.getParameter("km_addr2");
		String regip = req.getRemoteAddr();
		
		// int type = 1; 이렇게 생성해서 밑에서 setType(type); 해도 되고!
		
		logger.debug(uid);
		logger.debug(pass);
		logger.debug(name);
		logger.debug(gender);
		logger.debug(hp);
		logger.debug(email);
		logger.debug(zip);
		logger.debug(addr1);
		logger.debug(addr2);
		logger.debug(regip);
		
		KmMemberDTO dto = new KmMemberDTO();
		dto.setUid(uid);
		dto.setPass(pass);
		dto.setName(name);
		dto.setGender(gender);
		dto.setHp(hp);
		dto.setEmail(email);
		dto.setType(1); // RegisterController와 RegisterSellerController와 구별되어 있기 때문에, type을 받을 필요없이 이렇게 지정만 해주면 된다. 일반회원:1, 판매회원:2
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegIp(regip);
		
		service.insertMember(dto);
		
		resp.sendRedirect("/JSP/member/login.do"); // 로그인페이지로 가기, 여기선 ctxPath 호출 안됨
	}
}
