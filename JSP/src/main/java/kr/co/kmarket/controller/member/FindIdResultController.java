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

@WebServlet("/member/findIdResult.do")
public class FindIdResultController extends HttpServlet{

	private static final long serialVersionUID = 2043821522751893366L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		logger.debug("name : " + name);
		logger.debug("email : " + email);
		
		KmMemberDTO dto = new KmMemberDTO();
		
		dto = service.selectMemberByNameAndEmail(name, email);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/findIdResult.jsp");
		dispatcher.forward(req, resp);
		
	}

}
