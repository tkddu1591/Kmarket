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

import kr.co.kmarket.dto.KmMemberTermsDTO;
import kr.co.kmarket.service.KmMemberTermsService;

@WebServlet("/member/signup.do")
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = 5552943691942127442L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberTermsService service = KmMemberTermsService.instance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type"); // join.jsp에서 type 파라미터 수신
		
		KmMemberTermsDTO dto = new KmMemberTermsDTO();
		
		dto = service.selectTerms();
		logger.debug(type + "/" + dto.getPrivacy());
		
		req.setAttribute("type", type);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/signup.jsp");
		dispatcher.forward(req, resp);
	}
	
}
