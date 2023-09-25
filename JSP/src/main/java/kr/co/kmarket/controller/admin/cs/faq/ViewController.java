package kr.co.kmarket.controller.admin.cs.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/admin/cs/faq/view.do")
public class ViewController extends HttpServlet{


	private static final long serialVersionUID = -199877794397725844L;
	private KmCsFaqService service = KmCsFaqService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");

		KmCsFaqDTO dto = service.selectCsFaq(no);
		if(dto.getRelatedFaq() != 0) {
			KmCsFaqDTO relatedDto = service.selectCsFaq(dto.getRelatedFaq()+"");
			req.setAttribute("relatedDto", relatedDto);
		}
//		logger.debug("notice 글 : " + dto.toString());

		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);		
		
	}
}
